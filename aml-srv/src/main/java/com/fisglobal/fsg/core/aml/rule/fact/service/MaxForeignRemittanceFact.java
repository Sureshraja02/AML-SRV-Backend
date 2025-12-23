package com.fisglobal.fsg.core.aml.rule.fact.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.fisglobal.fsg.core.aml.utils.AMLConstants;


@Service("MAX_FOREIGN_REMITTANCESService")
public class MaxForeignRemittanceFact implements FactInterface{

    private final AMLConstants AMLConstants;


private Logger LOGGER = LoggerFactory.getLogger(MaxForeignRemittanceFact.class);
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	AccountDetailsService accountDetailsService;
	
	@Autowired
	FS_FactConditionRepoImpl fS_FactConditionRepoImpl;

	@Autowired
	FS_FactConditionAttributeRepoImpl fS_FactConditionAttributeRepoImpl;
	
	@Autowired
	CustomerDetailsService customerDetailsService;

	MaxForeignRemittanceFact(AMLConstants AMLConstants) {
        this.AMLConstants = AMLConstants;
    }
	
	@Override
	public ComputedFactsVO getFactExecutor(RuleRequestVo requVoObjParam, Factset factSetObj,List<ComputedFactsVO> computedFacts ) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::MaxForeignRemittanceFact@getFactExecutor (ENTRY) Called::::::::::",
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
			computedFactsVOObj.setValue(new BigDecimal(0));
			TransactionDetailsDTO dto =null;

			computedFactsVOObj.setStrType("num");
			{
			 dto = transactionService.getTransactionDetails(reqId, custId, accNo, txnId, null,null,
					transMode,true, days, months, factSetObj, range,false);
			if (dto != null && dto.getMaxAmount() != null) {

				computedFactsVOObj.setFact(factName);
				computedFactsVOObj.setValue((dto.getMaxAmount()));
			}
			else
			{
				computedFactsVOObj.setFact(factName);
				computedFactsVOObj.setValue(new BigDecimal(0));
			}
			}
		} catch (Exception e) {
			LOGGER.error("Exception found in MaxForeignRemittanceFact@getFactExecutor : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::MaxForeignRemittanceFact@getFactExecutor (EXIT) End::::::::::\n\n",
					requVoObjParam.getReqId());
		}
		return computedFactsVOObj;

	}

}