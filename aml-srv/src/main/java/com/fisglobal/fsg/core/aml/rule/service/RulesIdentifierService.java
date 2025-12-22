package com.fisglobal.fsg.core.aml.rule.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.aml.entity.AccountDetailsEntity;
import com.fisglobal.fsg.core.aml.entity.AccountStatusEntity;
import com.fisglobal.fsg.core.aml.entity.CustomerDetailsEntity;
import com.fisglobal.fsg.core.aml.repo.AccountDetailsService;
import com.fisglobal.fsg.core.aml.repo.AccountStatusRepositryImpl;
import com.fisglobal.fsg.core.aml.repo.CustomerDetailsRepoImpl;
import com.fisglobal.fsg.core.aml.rule.fact.service.FactInterface;
import com.fisglobal.fsg.core.aml.rule.fact.util.ClassLoaderUtil;
import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.ComputedFactsVO;
import com.fisglobal.fsg.core.aml.rule.process.response.RuleResponseVo;
import com.fisglobal.fsg.core.aml.rule.process.response.RuleResposeDetailsVO;

/**
 * 
 * @author : E5554365 (Prabakaran.R)
 * @Project : aml-srv
 * @year : 2025
 */
@Component
public class RulesIdentifierService {

	private Logger LOGGER = LoggerFactory.getLogger(RulesIdentifierService.class);

	@Autowired
	RulesAggregateService rulesExecutorService;

	@Autowired
	RulesRiskComplianceService rulesRsikComplianceService;

	@Autowired
	CustomerDetailsRepoImpl customerDetailsRepoImpl;
	
	@Autowired
	AccountDetailsService accountDetailsService;
	
	@Autowired
	AccountStatusRepositryImpl accountStatusRepositryImpl;
	
	@Autowired
	RulesUtils rulesUtils;
	
	@Autowired
	ClassLoaderUtil classLoaderUtil;

	public RuleResposeDetailsVO toComputeAMLData(RuleRequestVo ruleRequestVoObParam) {

		String classname = RulesIdentifierService.class.getSimpleName();
		String methodname = Thread.currentThread().getStackTrace()[1].getMethodName();
		LOGGER.info("RulesIdentifierService toComputeAMLData method called......[{}] [{}]", classname, methodname);

		RuleResponseVo ruleResponseVoObj = null;
		List<RuleResposeDetailsVO> ruleRespDtlObj = null;
		RuleResposeDetailsVO ruleResposeDetailsVO = null;
		List<ComputedFactsVO> computedFacts = null;
		ComputedFactsVO computedFactsVO = null;
		boolean IMMEDIATE_WITHDRAWAL = false;
		try {
			LOGGER.info("RulesIdentifierService toComputeAMLData - ruleRequestVoObParam [{}]......",
					ruleRequestVoObParam);
			LOGGER.info("RulesIdentifierService toComputeAMLData - ruleRequestVoObParam as String[{}]......",
					ruleRequestVoObParam.toString());
			if (ruleRequestVoObParam != null) {
				ruleResponseVoObj = new RuleResponseVo();

				ruleRespDtlObj = new ArrayList<RuleResposeDetailsVO>();
				computedFacts = new ArrayList<ComputedFactsVO>();
				ruleResposeDetailsVO = new RuleResposeDetailsVO();
				for (Factset fact : ruleRequestVoObParam.getFactSet()) {
					computedFactsVO = new ComputedFactsVO();
					if (StringUtils.isNotBlank(fact.getFact())) {

						FactInterface factInterface = classLoaderUtil.getBean(fact.getFact() + "Service",
								FactInterface.class);
						computedFactsVO = factInterface.getFactExecutor(ruleRequestVoObParam, fact, computedFacts);
						computedFactsVO.setFact(fact.getFact());
						computedFactsVO.setFieldTag(fact.getField());
						
						
						computedFacts.add(computedFactsVO);
					} else {
						LOGGER.info("RuleRequestVo object is NULL recevie");
					}
				}
			}

			if (StringUtils.isNotBlank(ruleRequestVoObParam.getCustomerId())) {
				CustomerDetailsEntity customerEnityObj = customerDetailsRepoImpl
						.getCustomerDetailsByCustId(ruleRequestVoObParam.getCustomerId());
				if (customerEnityObj != null) {
					ruleResposeDetailsVO.setAccountType(customerEnityObj.getCustomerType());
				}
			}
			if (StringUtils.isNotBlank(ruleRequestVoObParam.getAccountNo())) {
				AccountDetailsEntity customerEnityObj = accountDetailsService.getAccountDetails(ruleRequestVoObParam.getReqId(),null, ruleRequestVoObParam.getAccountNo());
						
				if (customerEnityObj != null) {
					ruleResposeDetailsVO.setAccountType(customerEnityObj.getAccountType());
				}
			}
			
			
			
			if (StringUtils.isNotBlank(ruleRequestVoObParam.getAccountNo())) {
				AccountStatusEntity accountStatusEntityObj = accountStatusRepositryImpl
						.getAccountStatusByAccNO(ruleRequestVoObParam.getAccountNo(), ruleRequestVoObParam.getReqId());
				if (accountStatusEntityObj != null) {
					ruleResposeDetailsVO.setAccountStatus(accountStatusEntityObj.getStatus());
				}

			}
			ruleResposeDetailsVO.setComputedFacts(computedFacts);
			ruleResposeDetailsVO.setReqId(ruleRequestVoObParam.getReqId());

			// ruleRespDtlObj.add(ruleResposeDetailsVO);
			// ruleResponseVoObj.setRuleResponse(ruleRespDtlObj);

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesIdentifierService");
		} finally {

		}
		return ruleResposeDetailsVO;
	}
}
