package com.fisglobal.fsg.core.aml.rule.intr;

import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.ComputedFactsVO;

/**
 * 
 * @author : E5554365 (Prabakaran.R)
 * @Project : aml-srv
 * @year : 2025
 */
@Component
public interface RuleExecutorIntr {
	
	public ComputedFactsVO ruleOfCountProcess(RuleRequestVo requVoObjParam, Factset factSetObj);

	public ComputedFactsVO ruleOfSUMProcess(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public ComputedFactsVO ruleOfMaxProcess(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public ComputedFactsVO ruleOfAVGProcess(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public ComputedFactsVO ruleOfCommAggregateProcess(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public ComputedFactsVO ruleOfPreviousForexTurnoverProcess(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public ComputedFactsVO ruleOfFDConversion(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public ComputedFactsVO ruleOfLargerDeposite(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public ComputedFactsVO ruleOfImmediateWithdraw(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	//filed:Tablename.column
	
}
