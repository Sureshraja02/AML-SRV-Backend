package com.fisglobal.fsg.core.aml.rule.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.aml.repo.CustomerDetailsRepoImpl;
import com.fisglobal.fsg.core.aml.repo.TransactionDetailsRepositryImpl2;
import com.fisglobal.fsg.core.aml.rule.intr.RulesRiskComplianceIntr;
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
public class RulesRiskComplianceService implements RulesRiskComplianceIntr {

	private Logger LOGGER = LoggerFactory.getLogger(RulesRiskComplianceService.class);
	
	@Autowired
	TransactionDetailsRepositryImpl2 transactionDetailsRepositryImpl2;
	
	@Autowired
	CustomerDetailsRepoImpl customerDetailsRepoImpl;

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
		String factName = null, accNo = null, custId = null, transMode = null, transType = null, fieldName = null, txnTime = null;
		try {
			computedFactsVOObj = new ComputedFactsVO();
			accNo = requVoObjParam.getAccountNo();
			custId = requVoObjParam.getCustomerId();
			transMode = requVoObjParam.getTransactionMode();
			transType = requVoObjParam.getTxnType();
			fieldName = factSetObj.getField();
			factName = factSetObj.getFact();
			Integer days = factSetObj.getDays();
			Integer hours = factSetObj.getHours();
			Integer months = factSetObj.getMonths();
			txnTime = requVoObjParam.getTxn_time();
			String finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("CUSTOMER")) {
					finalValue = customerDetailsRepoImpl.getPanStatus(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName,factSetObj.getRange());
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setStrValue(finalValue);
				}
			}

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
		String factName = null, accNo = null, custId = null, transMode = null, transType = null, fieldName = null, txnTime = null;
		try {
			computedFactsVOObj = new ComputedFactsVO();
			accNo = requVoObjParam.getAccountNo();
			custId = requVoObjParam.getCustomerId();
			transMode = requVoObjParam.getTransactionMode();
			transType = requVoObjParam.getTxnType();
			fieldName = factSetObj.getField();
			factName = factSetObj.getFact();
			Integer days = factSetObj.getDays();
			Integer hours = factSetObj.getHours();
			Integer months = factSetObj.getMonths();
			txnTime = requVoObjParam.getTxn_time();
			String finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("ACCOUNT")) {
					finalValue = transactionDetailsRepositryImpl2.getAccountStatus(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName,factSetObj.getRange(),factSetObj);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setStrValue(finalValue);
				}
			}

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
