package com.fisglobal.fsg.core.aml.rule.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.aml.entity.AccountStatusEntity;
import com.fisglobal.fsg.core.aml.entity.CustomerDetailsEntity;
import com.fisglobal.fsg.core.aml.repo.AccountStatusRepositryImpl;
import com.fisglobal.fsg.core.aml.repo.CustomerDetailsRepoImpl;
import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.ComputedFactsVO;
import com.fisglobal.fsg.core.aml.rule.process.response.RuleResposeDetailsVO;
import com.fisglobal.fsg.core.aml.utils.AMLConstants;

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
	AccountStatusRepositryImpl accountStatusRepositryImpl;
	
	@Autowired
	RulesUtils rulesUtils;

	public RuleResposeDetailsVO toComputeAMLData(RuleRequestVo ruleRequestVoObParam) {
		LOGGER.info("RulesIdentifierService toComputeAMLData method called......");
		//RuleResponseVo ruleResponseVoObj = null;
		//List<RuleResposeDetailsVO> ruleRespDtlObj = null;
		//ruleResponseVoObj = new RuleResponseVo();
		//ruleRespDtlObj = new ArrayList<RuleResposeDetailsVO>();
		RuleResposeDetailsVO ruleResposeDetailsVO = null;
		List<ComputedFactsVO> computedFacts = null;
		ComputedFactsVO computedFactsVO = null;
		boolean IMMEDIATE_WITHDRAWAL = false;
		try {
			LOGGER.info("RulesIdentifierService toComputeAMLData - ruleRequestVoObParam [{}]......", ruleRequestVoObParam);
			if (ruleRequestVoObParam != null) {
				
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
							IMMEDIATE_WITHDRAWAL = true;
							computedFactsVO = rulesExecutorService.ruleOfLargerDeposite(ruleRequestVoObParam, fact);
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
						case AMLConstants.AVG_DEBIT_CREDIT:
							computedFactsVO = rulesExecutorService.ruleOfAvgCreditDebit(ruleRequestVoObParam, fact);	
							break;
						case AMLConstants.SUM_DEBIT_CREDIT:
							computedFactsVO = rulesExecutorService.ruleOfSumCreditDebitAmount(ruleRequestVoObParam, fact);
							break;	
						case AMLConstants.MIN:
							computedFactsVO = rulesExecutorService.ruleOfMinProcess(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.COUNT_DEBIT_CREDIT:
							computedFactsVO = rulesExecutorService.ruleOfCountCreditDebitAmount(ruleRequestVoObParam, fact);
							break;	
						case AMLConstants.COUNT_CASH_DEPOSITS:
							computedFactsVO = rulesExecutorService.ruleOfCountCashDeposit(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.SUM_CASH_DEPOSITS:
							computedFactsVO = rulesExecutorService.ruleOfSumCashDeposit(ruleRequestVoObParam, fact);
							break;	
						case AMLConstants.AVG_CASH_DEPOSITS:
							computedFactsVO = rulesExecutorService.ruleOfAvgCashDeposit(ruleRequestVoObParam, fact);
							break;		
						case AMLConstants.SUM_DEBIT_CREDIT_CLOSED_ACCOUNT:
							computedFactsVO = rulesExecutorService.ruleOfSumCreditDebitClosedAccount(ruleRequestVoObParam, fact);
							break;	
						
						//New	
						case AMLConstants.COUNT_CASH_WITHDRAWALS:
							computedFactsVO = rulesExecutorService.ruleOfCountCashWithdraw(ruleRequestVoObParam, fact);
							break;	
						case AMLConstants.SUM_NONCASH_TXNS:
							computedFactsVO = rulesExecutorService.ruleOfSumNonCashTxn(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.SUM_ACCOUNT_TRANSFERS:
							computedFactsVO = rulesExecutorService.ruleOfSumAccountTransfer(ruleRequestVoObParam, fact);
							break;	
						case AMLConstants.SUM_CASH_WITHDRAWALS:
							computedFactsVO = rulesExecutorService.ruleOfSumCashWithdraw(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.SUM_NONCASH_DEPOSITS:
							computedFactsVO = rulesExecutorService.ruleOfSumNonCashDeposit(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.SUM_NONCASH_WITHDRAWAL:
							computedFactsVO = rulesExecutorService.ruleOfSumNonCashWithdraw(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.COUNT_ACCOUNT_TRANSFERS:
							computedFactsVO = rulesExecutorService.ruleOfCountAccountTransfer(ruleRequestVoObParam, fact);
							break;	
						case AMLConstants.SUM_CASH_TXNS:
							computedFactsVO = rulesExecutorService.ruleOfSumCashTxn(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.COUNT_ACC_TO_ACC_TRANSFERS:
							computedFactsVO = rulesExecutorService.ruleOfSumAccountToAccountTxn(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.AVG_CASH_WITHDRAWALS:
							computedFactsVO = rulesExecutorService.ruleOfAvgCashWithdraw(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.MAX_DEPOSIT:
							computedFactsVO = rulesExecutorService.ruleOfLargerDeposite(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.MAX_CASH_TXN:
							computedFactsVO = rulesExecutorService.ruleOfMaxCashTxn(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.MAX_NON_CASH_TXN:
							computedFactsVO = rulesExecutorService.ruleOfMaxNonCashTxn(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.COUNT_SMALL_CASH_DEPOSITS:
							computedFactsVO = rulesExecutorService.ruleOfCountCashDeposit(ruleRequestVoObParam, fact);
							break;
						case AMLConstants.IMMEDIATE_WITHDRAWAL://85
							if (IMMEDIATE_WITHDRAWAL && computedFacts != null && computedFacts.size() >= 1) {
								computedFactsVO = rulesExecutorService.ruleOfImmediateWithdraw(ruleRequestVoObParam, fact, computedFacts);
							} else {
								// Rare
							}
							break;
						case AMLConstants.WITHDRAWAL_PERCENTAGE://79,39,27,26,25
							if (fact != null && fact.getCondition() != null && fact.getCondition().equalsIgnoreCase(AMLConstants.IMMEDIATE_DIFFERENT_LOCATIONS)) {
								//25
								
							} else if (fact != null && fact.getCondition() != null && fact.getCondition().equalsIgnoreCase(AMLConstants.IMMEDIATE_WITHDRAWAL)) {
								//26
							} else if (fact != null && fact.getCondition() != null && fact.getCondition().equalsIgnoreCase(AMLConstants.IMMEDIATE_WITHDRAWAL_ATM_OR_OTHER)) {
								//27
							} else { // No condition
								// 39, 79
								if (computedFacts != null && computedFacts.size() >= 1) {
									computedFactsVO = rulesExecutorService.ruleOfImmediateWithdraw(ruleRequestVoObParam, fact, computedFacts);
								}
								
							}
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
					if (customerEnityObj != null) {
						ruleResposeDetailsVO.setAccountHolderType(customerEnityObj.getCustomerType());
					}
				}
				if(StringUtils.isNotBlank(ruleRequestVoObParam.getAccountNo())) {
					AccountStatusEntity accountStatusEntityObj = accountStatusRepositryImpl.getAccountStatusByAccNO(ruleRequestVoObParam.getAccountNo(), ruleRequestVoObParam.getReqId());
					if(accountStatusEntityObj!=null) {
						ruleResposeDetailsVO.setAccountStatus(accountStatusEntityObj.getStatus());
					}
					
				}
				ruleResposeDetailsVO.setComputedFacts(computedFacts);
				ruleResposeDetailsVO.setReqId(ruleRequestVoObParam.getReqId());
				
				//ruleRespDtlObj.add(ruleResposeDetailsVO);
				//ruleResponseVoObj.setRuleResponse(ruleRespDtlObj);
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesIdentifierService");
		} finally {
			
		}
		return ruleResposeDetailsVO;
	}
}
