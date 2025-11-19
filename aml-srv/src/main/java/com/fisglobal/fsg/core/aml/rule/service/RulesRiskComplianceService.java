package com.fisglobal.fsg.core.aml.rule.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.aml.rule.intr.RulesRiskComplianceIntr;
import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.ComputedFactsVO;

@Component
public class RulesRiskComplianceService implements RulesRiskComplianceIntr {

	private Logger LOGGER = LoggerFactory.getLogger(RulesRiskComplianceService.class);

	@Override
	public ComputedFactsVO ruleOfCountryRisk(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesRiskComplianceService@ruleOfImmediateWithdraw (IMMEDIATE_WITHDRAWAL) Called::::::::::", requVoObjParam.getReqId());
		try {
			
		} catch (Exception e) {
			LOGGER.error("Exception found in RulesRiskComplianceService@ruleOfImmediateWithdraw : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesRiskComplianceService@ruleOfImmediateWithdraw (IMMEDIATE_WITHDRAWAL) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

	@Override
	public ComputedFactsVO ruleOfCustomerMatch(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesRiskComplianceService@ruleOfCustomerMatch (CUSTOMER_MATCH) Called::::::::::", requVoObjParam.getReqId());
		try {
			
		} catch (Exception e) {
			LOGGER.error("Exception found in RulesRiskComplianceService@ruleOfCustomerMatch : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesRiskComplianceService@ruleOfCustomerMatch (CUSTOMER_MATCH) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

	@Override
	public ComputedFactsVO ruleOfFCRACompliance(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesRiskComplianceService@ruleOfFCRACompliance (FCRA_COMPLIANCE) Called::::::::::", requVoObjParam.getReqId());
		try {
			
		} catch (Exception e) {
			LOGGER.error("Exception found in RulesRiskComplianceService@ruleOfFCRACompliance : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesRiskComplianceService@ruleOfFCRACompliance (FCRA_COMPLIANCE) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

	@Override
	public ComputedFactsVO ruleOfPanStatus(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesRiskComplianceService@ruleOfPanStatus (PAN_STATUS) Called::::::::::", requVoObjParam.getReqId());
		try {
			
		} catch (Exception e) {
			LOGGER.error("Exception found in RulesRiskComplianceService@ruleOfPanStatus : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesRiskComplianceService@ruleOfPanStatus (PAN_STATUS) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

	@Override
	public ComputedFactsVO ruleOfAccountStatus(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesRiskComplianceService@ruleOfAccountStatus (ACCOUNT_STATUS) Called::::::::::", requVoObjParam.getReqId());
		try {
			
		} catch (Exception e) {
			LOGGER.error("Exception found in RulesRiskComplianceService@ruleOfAccountStatus : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesRiskComplianceService@ruleOfAccountStatus (ACCOUNT_STATUS) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

	@Override
	public ComputedFactsVO ruleOfBeneficiaryRelation(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesRiskComplianceService@ruleOfBeneficiaryRelation (BENEFICIARY_RELATION) Called::::::::::", requVoObjParam.getReqId());
		try {
			
		} catch (Exception e) {
			LOGGER.error("Exception found in RulesRiskComplianceService@ruleOfBeneficiaryRelation : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesRiskComplianceService@ruleOfBeneficiaryRelation (BENEFICIARY_RELATION) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

}
