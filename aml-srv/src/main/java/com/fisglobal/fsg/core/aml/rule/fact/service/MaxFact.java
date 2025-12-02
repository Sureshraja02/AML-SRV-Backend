package com.fisglobal.fsg.core.aml.rule.fact.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisglobal.fsg.core.aml.entity.AccountStatusEntity;
import com.fisglobal.fsg.core.aml.repo.AccountDetailsService;
import com.fisglobal.fsg.core.aml.repo.TransactionDetailsDTO;
import com.fisglobal.fsg.core.aml.repo.TransactionService;
import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.Range;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.ComputedFactsVO;
import com.fisglobal.fsg.core.aml.rule.service.RulesIdentifierService;


@Service("MAXService")
public class MaxFact implements FactInterface{


	private Logger LOGGER = LoggerFactory.getLogger(MaxFact.class);
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	AccountDetailsService accountDetailsService;
	
	
	@Override
	public ComputedFactsVO getFactExecutor(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::CountFact@getFactExecutor (ENTRY) Called::::::::::",
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
			TransactionDetailsDTO dto = null;
			if (condition != null) {
				if (condition.equals("DORMANT_REACTIVATION")) {
					accNo = requVoObjParam.getAccountNo();
					custId = null;
					AccountStatusEntity acctStatus = accountDetailsService.getAccountStatusByAccNO(accNo,
							requVoObjParam.getReqId());
					if (acctStatus != null && acctStatus.getStatus() != null && acctStatus.getStatus().equals("06")) {
						String status = acctStatus.getStatus();
						computedFactsVOObj.setAccountStatus(status);
						computedFactsVOObj.setAcc_Re_date(acctStatus.getChangeDate());
						computedFactsVOObj.setStrValue("DORMANT_REACTIVATION");
						dto = transactionService.getTransactionDetails(reqId, custId, accNo, txnId, null, transMode,
								days, months, factSetObj, range);
						if (dto != null && dto.getCountAmount() != null) {

							computedFactsVOObj.setFact(factName);
							computedFactsVOObj.setValue(new BigDecimal(dto.getCountAmount()));
						}

					} else {
						computedFactsVOObj.setFact(factName);
						computedFactsVOObj.setStrValue("NO_DORMANT_REACTIVATION");
						computedFactsVOObj.setValue(new BigDecimal(0));
					}

				}
			} else {
				dto = transactionService.getTransactionDetails(reqId, custId, accNo, txnId, transType, transMode, days,
						months, factSetObj, range);
				if (dto != null && dto.getCountAmount() != null) {

					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(new BigDecimal(dto.getCountAmount()));
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in CountFact@getFactExecutor : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::CountFact@getFactExecutor (EXIT) End::::::::::\n\n",
					requVoObjParam.getReqId());
		}
		return computedFactsVOObj;

	}

}