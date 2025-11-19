package com.fisglobal.fsg.core.aml.rule.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.aml.entity.CustomerDetailsEntity;
import com.fisglobal.fsg.core.aml.repo.CustomerDetailsRepoImpl;
import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.ComputedFactsVO;
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

	@Autowired
	CustomerDetailsRepoImpl customerDetailsRepoImpl;

	public RuleResponseVo toComputeAMLData(RuleRequestVo ruleRequestVoObParam) {
		LOGGER.info("RulesIdentifierService toComputeAMLData method called......");

		RuleResponseVo ruleResponseVoObj = null;
		List<RuleResposeDetailsVO> ruleRespDtlObj = null;
		RuleResposeDetailsVO ruleResposeDetailsVO = null;
		List<ComputedFactsVO> computedFacts = null;
		ComputedFactsVO computedFactsVO = null;
		try {
			LOGGER.info("RulesIdentifierService toComputeAMLData - ruleRequestVoObParam [{}]......", ruleRequestVoObParam);
			if (ruleRequestVoObParam != null) {
				ruleResponseVoObj = new RuleResponseVo();

				ruleRespDtlObj = new ArrayList<RuleResposeDetailsVO>();
				computedFacts = new ArrayList<ComputedFactsVO>();
				ruleResposeDetailsVO = new RuleResposeDetailsVO();
				for (Factset fact : ruleRequestVoObParam.getFactSet()) {
					computedFactsVO = new ComputedFactsVO();
					if (StringUtils.isNotBlank(fact.getFact())) {
						computedFactsVO.setFact(fact.getFact());
						switch (fact.getFact()) {

						case AMLConstants.SUM:
							computedFactsVO = rulesExecutorService.ruleOfSUMProcess(ruleRequestVoObParam, fact);
							
							break;
						case AMLConstants.COUNT:
							computedFactsVO = rulesExecutorService.ruleOfCountProcess(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.AVG:
							computedFactsVO = rulesExecutorService.ruleOfAVGProcess(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.MAX:
							computedFactsVO = rulesExecutorService.ruleOfMaxProcess(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.TOTAL:
							computedFactsVO = rulesExecutorService.ruleOfSUMProcess(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.PREVIOUS_FOREX_TURNOVER:
							computedFactsVO = rulesExecutorService.ruleOfPreviousForexTurnoverProcess(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.FD_CONVERSION:
							computedFactsVO = rulesExecutorService.ruleOfFDConversion(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.LARGE_DEPOSIT:
							computedFactsVO = rulesExecutorService.ruleOfLargerDeposite(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.IMMEDIATE_WITHDRAWAL:
							computedFactsVO = rulesExecutorService.ruleOfImmediateWithdraw(ruleRequestVoObParam, fact);
							break;

						case AMLConstants.COUNTRY_RISK:
							computedFactsVO = rulesRsikComplianceService.ruleOfCountryRisk(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.CUSTOMER_MATCH:
							computedFactsVO = rulesRsikComplianceService.ruleOfCustomerMatch(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.FCRA_COMPLIANCE:
							computedFactsVO = rulesRsikComplianceService.ruleOfFCRACompliance(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.PAN_STATUS:
							computedFactsVO = rulesRsikComplianceService.ruleOfPanStatus(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.ACCOUNT_STATUS:
							computedFactsVO = rulesRsikComplianceService.ruleOfAccountStatus(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.BENEFICIARY_RELATION:
							computedFactsVO = rulesRsikComplianceService.ruleOfBeneficiaryRelation(ruleRequestVoObParam, fact);
							break;

						default:
							LOGGER.info("NO MATCH FOUND");
						}
						computedFacts.add(computedFactsVO);
					} else {
						LOGGER.info("RuleRequestVo object is NULL recevie");
					}
				}
				if(StringUtils.isNotBlank(ruleRequestVoObParam.getCustomerId())) {
					CustomerDetailsEntity customerEnityObj = customerDetailsRepoImpl.getCustomerDetailsByCustId(ruleRequestVoObParam.getCustomerId());
					ruleResposeDetailsVO.setAccountHolderType(customerEnityObj.getCustomerType());
				}
				if(StringUtils.isNotBlank(ruleRequestVoObParam.getAccountNo())) {
					
					ruleResposeDetailsVO.setAccountStatus("");
				}
				
				ruleResposeDetailsVO.setComputedFacts(computedFacts);
				ruleResposeDetailsVO.setReqId(ruleRequestVoObParam.getReqId());
				ruleResposeDetailsVO.setValue(new BigDecimal(0));
				
				ruleRespDtlObj.add(ruleResposeDetailsVO);
				ruleResponseVoObj.setRuleResponse(ruleRespDtlObj);
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesIdentifierService");
		} finally {
		}
		return ruleResponseVoObj;
	}
}
