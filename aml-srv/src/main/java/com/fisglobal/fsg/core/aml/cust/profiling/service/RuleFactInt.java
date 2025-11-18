package com.fisglobal.fsg.core.aml.cust.profiling.service;

import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;

public interface RuleFactInt {

	
	public Integer sumOfCumulative(RuleRequestVo ruleReqVOObj);
	
	public Integer highValue(RuleRequestVo ruleReqVOObj);
	
	public Integer turnOver(RuleRequestVo ruleReqVOObj);
	
	public double avgTurnOver(RuleRequestVo ruleReqVOObj); 
	
	public Integer countDRCR(RuleRequestVo ruleReqVOObj);
	
	public Integer avgAccActivity(RuleRequestVo ruleReqVOObj);
	
}
