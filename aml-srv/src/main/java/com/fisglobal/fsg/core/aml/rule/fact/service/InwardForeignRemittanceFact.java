package com.fisglobal.fsg.core.aml.rule.fact.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisglobal.fsg.core.aml.entity.AccountDetailsEntity;
import com.fisglobal.fsg.core.aml.repo.AccountDetailsService;
import com.fisglobal.fsg.core.aml.repo.TransactionDetailsDTO;
import com.fisglobal.fsg.core.aml.repo.TransactionService;
import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.Range;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.ComputedFactsVO;
import com.fisglobal.fsg.core.aml.rule.service.RulesIdentifierService;
import com.fisglobal.fsg.core.aml.utils.AMLConstants;


@Service("INWARD_FOREIGN_REMITTANCEService")
public class InwardForeignRemittanceFact implements FactInterface{


private Logger LOGGER = LoggerFactory.getLogger(SumDebitCreditFact.class);
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	AccountDetailsService accountDetailsService;
	
	@Override
	public ComputedFactsVO getFactExecutor(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::InwardForeignRemittanceFact@getFactExecutor (ENTRY) Called::::::::::",
				requVoObjParam.getReqId());
		String factName = null, accNo = null, custId = null, transMode = null, transType = null, 
				txnTime = null, txnId = null, reqId = null;
		try {
			computedFactsVOObj = new ComputedFactsVO();
			accNo = requVoObjParam.getAccountNo();
			custId = requVoObjParam.getCustomerId();
			txnId = requVoObjParam.getTxnId();
			reqId = requVoObjParam.getReqId();
			transMode = requVoObjParam.getTransactionMode();
			transType = requVoObjParam.getTxnType();			
			factName = factSetObj.getFact();
			Integer days = factSetObj.getDays();
			Integer hours = factSetObj.getHours();
			Integer months = factSetObj.getMonths();
			txnTime = requVoObjParam.getTxn_time();
			Range range = factSetObj.getRange();
			String condition = factSetObj.getCondition();
			TransactionDetailsDTO dto =null;

			if (condition != null) {
				if (condition.equals("NEW_ACCOUNT")) {
					AccountDetailsEntity acctDetails = accountDetailsService
							.getAccountDetails(requVoObjParam.getReqId(), accNo, custId);
					if (acctDetails != null && acctDetails.getAccountOpenedDate() != null) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						LocalDate openDate = LocalDate.parse(acctDetails.getAccountOpenedDate(), formatter);
						LocalDate currentDate = LocalDate.now();
						System.out.println(openDate); // Output: 2025-05-20

						long daysBetween = ChronoUnit.DAYS.between(openDate, currentDate);
						if (days != null && days >= daysBetween) {
							computedFactsVOObj.setAcc_open_date(acctDetails.getAccountOpenedDate());
							computedFactsVOObj.setAccountStatus("NEW");
						} else if (months != null) {
							int totalDays = months * 30;
							if (totalDays >= daysBetween) {
								computedFactsVOObj.setAcc_open_date(acctDetails.getAccountOpenedDate());
								computedFactsVOObj.setAccountStatus("NEW");
							}
						} else {
							computedFactsVOObj.setAcc_open_date(acctDetails.getAccountOpenedDate());
							computedFactsVOObj.setAccountStatus("OLD");
						}
					} else {

						computedFactsVOObj.setAccountStatus("OLD");
					}

				}

			}
			else
			{
			 dto = transactionService.getTransactionDetails(reqId, custId, accNo, txnId, null,AMLConstants.WITHDRAW,
					transMode, days, months, factSetObj, range);
			if (dto != null && dto.getCountAmount() != null) {

				computedFactsVOObj.setFact(factName);
				computedFactsVOObj.setValue(new BigDecimal(dto.getCountAmount()));
			}
			}
		} catch (Exception e) {
			LOGGER.error("Exception found in InwardForeignRemittanceFact@getFactExecutor : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::InwardForeignRemittanceFact@getFactExecutor (EXIT) End::::::::::\n\n",
					requVoObjParam.getReqId());
		}
		return computedFactsVOObj;

	}

}