package com.fisglobal.fsg.core.aml.rule.intr;

import org.springframework.stereotype.Component;

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
	
	public RuleResposeDetailsVO ruleOfCountProcess(RuleRequestVo requVoObjParam);

	public RuleResposeDetailsVO ruleOfSUMProcess(RuleRequestVo requVoObjParam);
	
	public RuleResposeDetailsVO ruleOfMaxProcess(RuleRequestVo requVoObjParam);
	
	public RuleResposeDetailsVO ruleOfAVGProcess(RuleRequestVo requVoObjParam);
	
	public RuleResposeDetailsVO ruleOfCommAggregateProcess(RuleRequestVo requVoObjParam);
	
	public RuleResposeDetailsVO ruleOfPreviousForexTurnoverProcess(RuleRequestVo requVoObjParam);
	
	public RuleResposeDetailsVO ruleOfFDConversion(RuleRequestVo requVoObjParam);
	
	public RuleResposeDetailsVO ruleOfLargerDeposite(RuleRequestVo requVoObjParam);
	
	public RuleResposeDetailsVO ruleOfImmediateWithdraw(RuleRequestVo requVoObjParam);
	
	//filed:Tablename.column
	
}
