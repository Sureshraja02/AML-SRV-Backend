package com.fisglobal.fsg.core.aml.rule.intr;

import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.RuleResposeDetailsVO;

@Component
public interface RulesRiskComplianceIntr {

	public RuleResposeDetailsVO ruleOfCountryRisk(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public RuleResposeDetailsVO ruleOfCustomerMatch(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public RuleResposeDetailsVO ruleOfFCRACompliance(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public RuleResposeDetailsVO ruleOfPanStatus(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public RuleResposeDetailsVO ruleOfAccountStatus(RuleRequestVo requVoObjParam, Factset factSetObj);
	
	public RuleResposeDetailsVO ruleOfBeneficiaryRelation(RuleRequestVo requVoObjParam, Factset factSetObj);
	
}
