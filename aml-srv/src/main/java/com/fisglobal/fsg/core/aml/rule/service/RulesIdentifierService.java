package com.fisglobal.fsg.core.aml.rule.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.RuleResponseVo;
import com.fisglobal.fsg.core.aml.rule.process.response.RuleResposeDetailsVO;
import com.fisglobal.fsg.core.aml.utils.AMLConstants;

@Component
public class RulesIdentifierService {

	private Logger LOGGER = LoggerFactory.getLogger(RulesIdentifierService.class);

	@Autowired
	RulesAggregateService rulesExecutorService;

	@Autowired
	RulesRiskComplianceService rulesRsikComplianceService;

	public RuleResponseVo toComputeAMLData(RuleRequestVo ruleRequestVoObParam) {
		LOGGER.info("RulesIdentifierService toComputeAMLData method called......");

		RuleResponseVo ruleResponseVoObj = null;
		List<RuleResposeDetailsVO> ruleRespDtlObj = null;
		RuleResposeDetailsVO ruleResposeDetailsVO = null;
		try {
			LOGGER.info("RulesIdentifierService toComputeAMLData - ruleRequestVoObParam [{}]......", ruleRequestVoObParam);
			if (ruleRequestVoObParam != null) {
				ruleResponseVoObj = new RuleResponseVo();
				ruleRespDtlObj = new ArrayList<RuleResposeDetailsVO>();
				for (Factset fact : ruleRequestVoObParam.getFactSet()) {
					// ruleResposeDetailsVO = new RuleResposeDetailsVO();
					// BigDecimal finalValue = null;
					if (StringUtils.isNotBlank(fact.getFact())) {

						switch (fact.getFact()) {

						case AMLConstants.SUM:
							ruleResposeDetailsVO = rulesExecutorService.ruleOfSUMProcess(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.COUNT:
							ruleResposeDetailsVO = rulesExecutorService.ruleOfCountProcess(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.AVG:
							ruleResposeDetailsVO = rulesExecutorService.ruleOfAVGProcess(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.MAX:
							ruleResposeDetailsVO = rulesExecutorService.ruleOfMaxProcess(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.TOTAL:
							ruleResposeDetailsVO = rulesExecutorService.ruleOfSUMProcess(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.PREVIOUS_FOREX_TURNOVER:
							ruleResposeDetailsVO = rulesExecutorService.ruleOfPreviousForexTurnoverProcess(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.FD_CONVERSION:
							ruleResposeDetailsVO = rulesExecutorService.ruleOfFDConversion(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.LARGE_DEPOSIT:
							ruleResposeDetailsVO = rulesExecutorService.ruleOfLargerDeposite(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.IMMEDIATE_WITHDRAWAL:
							ruleResposeDetailsVO = rulesExecutorService.ruleOfImmediateWithdraw(ruleRequestVoObParam, fact);
							break;

						case AMLConstants.COUNTRY_RISK:
							ruleResposeDetailsVO = rulesRsikComplianceService.ruleOfCountryRisk(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.CUSTOMER_MATCH:
							ruleResposeDetailsVO = rulesRsikComplianceService.ruleOfCustomerMatch(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.FCRA_COMPLIANCE:
							ruleResposeDetailsVO = rulesRsikComplianceService.ruleOfFCRACompliance(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.PAN_STATUS:
							ruleResposeDetailsVO = rulesRsikComplianceService.ruleOfPanStatus(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.ACCOUNT_STATUS:
							ruleResposeDetailsVO = rulesRsikComplianceService.ruleOfAccountStatus(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.BENEFICIARY_RELATION:
							ruleResposeDetailsVO = rulesRsikComplianceService.ruleOfBeneficiaryRelation(ruleRequestVoObParam, fact);
							break;

						default:
							LOGGER.info("NO MATCH FOUND");
						}
						ruleRespDtlObj.add(ruleResposeDetailsVO);
					} else {
						LOGGER.info("RuleRequestVo object is NULL recevie");
					}
				}
			}
			ruleResponseVoObj.setRuleResponse(ruleRespDtlObj);

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesIdentifierService");
		} finally {
		}
		return ruleResponseVoObj;
	}
}
