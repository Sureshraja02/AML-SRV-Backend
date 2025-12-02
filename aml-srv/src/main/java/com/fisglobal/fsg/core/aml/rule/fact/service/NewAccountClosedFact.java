package com.fisglobal.fsg.core.aml.rule.fact.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.ComputedFactsVO;
import com.fisglobal.fsg.core.aml.rule.service.RulesIdentifierService;


@Service("NEW_ACCOUNT_CLOSEDService")
public class NewAccountClosedFact implements FactInterface{


	private Logger LOGGER = LoggerFactory.getLogger(NewAccountClosedFact.class);
	
	@Override
	public ComputedFactsVO getFactExecutor(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfFDConversion (FD_CONVERSION) Called::::::::::",
				requVoObjParam.getReqId());
		try {

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfFDConversion : {}", e);
		} finally {

			LOGGER.info(
					"REQID : [{}]::::::::::::RulesExecutorService@ruleOfFDConversion (FD_CONVERSION) End::::::::::\n\n",
					requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

}