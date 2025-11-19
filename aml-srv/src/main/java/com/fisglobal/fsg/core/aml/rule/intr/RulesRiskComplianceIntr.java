package com.fisglobal.fsg.core.aml.rule.intr;

import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.ComputedFactsVO;

@Component
public interface RulesRiskComplianceIntr {

	public ComputedFactsVO ruleOfCountryRisk(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public ComputedFactsVO ruleOfCustomerMatch(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public ComputedFactsVO ruleOfFCRACompliance(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public ComputedFactsVO ruleOfPanStatus(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public ComputedFactsVO ruleOfAccountStatus(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public ComputedFactsVO ruleOfBeneficiaryRelation(RuleRequestVo requVoObjParam, Factset factSetObj);
	
}
