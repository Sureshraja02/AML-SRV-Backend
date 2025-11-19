package com.fisglobal.fsg.core.aml.rule.intr;

import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.RuleResposeDetailsVO;

/**
 * 
 * @author : E5554365 (Prabakaran.R)
 * @Project : aml-srv
 * @year : 2025
 */
@Component
public interface RuleExecutorIntr {
	
	public RuleResposeDetailsVO ruleOfCountProcess(RuleRequestVo requVoObjParam, Factset factSetObj);

	public RuleResposeDetailsVO ruleOfSUMProcess(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public RuleResposeDetailsVO ruleOfMaxProcess(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public RuleResposeDetailsVO ruleOfAVGProcess(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public RuleResposeDetailsVO ruleOfCommAggregateProcess(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public RuleResposeDetailsVO ruleOfPreviousForexTurnoverProcess(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public RuleResposeDetailsVO ruleOfFDConversion(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public RuleResposeDetailsVO ruleOfLargerDeposite(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public RuleResposeDetailsVO ruleOfImmediateWithdraw(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	//filed:Tablename.column
	
}
