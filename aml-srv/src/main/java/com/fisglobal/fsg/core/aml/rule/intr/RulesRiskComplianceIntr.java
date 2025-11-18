package com.fisglobal.fsg.core.aml.rule.intr;

import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.RuleResposeDetailsVO;

@Component
public interface RulesRiskComplianceIntr {

	public RuleResposeDetailsVO ruleOfCountryRisk(RuleRequestVo requVoObjParam);
	
	public RuleResposeDetailsVO ruleOfCustomerMatch(RuleRequestVo requVoObjParam);
	
	public RuleResposeDetailsVO ruleOfFCRACompliance(RuleRequestVo requVoObjParam);
	
	public RuleResposeDetailsVO ruleOfPanStatus(RuleRequestVo requVoObjParam);
	
	public RuleResposeDetailsVO ruleOfAccountStatus(RuleRequestVo requVoObjParam);
	
	public RuleResposeDetailsVO ruleOfBeneficiaryRelation(RuleRequestVo requVoObjParam);
	
}
