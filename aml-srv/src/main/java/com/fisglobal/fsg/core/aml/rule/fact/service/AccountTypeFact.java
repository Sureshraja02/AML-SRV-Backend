package com.fisglobal.fsg.core.aml.rule.fact.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisglobal.fsg.core.aml.entity.AccountDetailsEntity;
import com.fisglobal.fsg.core.aml.entity.CustomerDetailsEntity;
import com.fisglobal.fsg.core.aml.entity.FS_FactConditionAttributeEntity;
import com.fisglobal.fsg.core.aml.entity.FS_FactConditionEntity;
import com.fisglobal.fsg.core.aml.repo.AccountDetailsService;
import com.fisglobal.fsg.core.aml.repo.CustomerDetailsService;
import com.fisglobal.fsg.core.aml.repo.FS_FactConditionAttributeRepoImpl;
import com.fisglobal.fsg.core.aml.repo.FS_FactConditionRepoImpl;
import com.fisglobal.fsg.core.aml.repo.TransactionDetailsDTO;
import com.fisglobal.fsg.core.aml.repo.TransactionService;
import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.Range;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.ComputedFactsVO;



@Service("ACCOUNT_TYPESService")
public class AccountTypeFact implements FactInterface{

	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	FS_FactConditionRepoImpl fS_FactConditionRepoImpl;

	@Autowired
	FS_FactConditionAttributeRepoImpl fS_FactConditionAttributeRepoImpl;
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	AccountDetailsService accountDetailsService;

	private Logger LOGGER = LoggerFactory.getLogger(AccountTypeFact.class);
	
	@Override
	public ComputedFactsVO getFactExecutor(RuleRequestVo requVoObjParam, Factset factSetObj,List<ComputedFactsVO> computedFacts ) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::AccountTypeFact@getFactExecutor (ENTRY) Called::::::::::",
				requVoObjParam.getReqId());
		String factName = null, accNo = null, custId = null, transMode = null, transType = null, txnTime = null,
				txnId = null, reqId = null;
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
			AccountDetailsEntity dto = null;
			computedFactsVOObj.setStrType("str");
			if (condition != null) {
				if (condition.equals("CA_NON_PUBLIC")) {

					dto = accountDetailsService.getAccountDetails(reqId, custId, accNo);
					
					if (dto != null && dto.getAccountType() != null) {

						computedFactsVOObj.setFact(factName);
						computedFactsVOObj.setStrValue(dto.getAccountType());
					}
					else
					{

							computedFactsVOObj.setFact(factName);
							computedFactsVOObj.setStrValue("PUBLIC");
						}

					
				}

			} else {
				dto = accountDetailsService.getAccountDetails(reqId, custId, accNo);
				if (dto != null && dto.getAccountType() != null) {

					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setStrValue(dto.getAccountType());
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in AccountTypeFact@getFactExecutor : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::AccountTypeFact@getFactExecutor (EXIT) End::::::::::\n\n",
					requVoObjParam.getReqId());
		}
		return computedFactsVOObj;

	}

}