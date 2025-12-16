package com.fisglobal.fsg.core.aml.rule.fact.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisglobal.fsg.core.aml.repo.TransactionDetailsDTO;
import com.fisglobal.fsg.core.aml.repo.TransactionService;
import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.Range;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.ComputedFactsVO;
import com.fisglobal.fsg.core.aml.utils.AMLConstants;


@Service("OUTWARD_FOREIGN_REMITTANCEService")
public class OutwardForeignRemittanceFact implements FactInterface{


	private Logger LOGGER = LoggerFactory.getLogger(OutwardForeignRemittanceFact.class);
	
	@Autowired
	TransactionService transactionService;
	
	@Override
	public ComputedFactsVO getFactExecutor(RuleRequestVo requVoObjParam, Factset factSetObj,List<ComputedFactsVO> computedFacts ) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::OutwardForeignRemittanceFact@getFactExecutor (ENTRY) Called::::::::::",
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
			TransactionDetailsDTO dto =null;
			 dto = transactionService.getTransactionDetails(reqId, custId, accNo, txnId, null,AMLConstants.WITHDRAW,
						transMode,true, days, months, factSetObj, range);
				if (dto != null && dto.getTxnAmount() != null) {

					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue((dto.getTxnAmount()));
				}

		} catch (Exception e) {
			LOGGER.error("Exception found in OutwardForeignRemittanceFact@getFactExecutor : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::OutwardForeignRemittanceFact@getFactExecutor (EXIT) End::::::::::\n\n",
					requVoObjParam.getReqId());
		}
		return computedFactsVOObj;

	}

}