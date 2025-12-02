package com.fisglobal.fsg.core.aml.rule.fact.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisglobal.fsg.core.aml.entity.CustomerDetailsEntity;
import com.fisglobal.fsg.core.aml.entity.FS_FIUIndHighRiskCountryEntity;
import com.fisglobal.fsg.core.aml.entity.FS_FactConditionAttributeEntity;
import com.fisglobal.fsg.core.aml.entity.FS_FactConditionEntity;
import com.fisglobal.fsg.core.aml.repo.CustomerDetailsService;
import com.fisglobal.fsg.core.aml.repo.FS_FIUIndHighRiskCountryRepoImpl;
import com.fisglobal.fsg.core.aml.repo.FS_FIUIndTerrorLocationRepoImpl;
import com.fisglobal.fsg.core.aml.repo.FS_FactConditionAttributeRepoImpl;
import com.fisglobal.fsg.core.aml.repo.FS_FactConditionRepoImpl;
import com.fisglobal.fsg.core.aml.repo.TransactionDetailsDTO;
import com.fisglobal.fsg.core.aml.repo.TransactionService;
import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.Range;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.ComputedFactsVO;
import com.fisglobal.fsg.core.aml.rule.service.RulesIdentifierService;


@Service("COUNTRY_RISKService")
public class CountryRIskFact implements FactInterface{

	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	FS_FactConditionRepoImpl fS_FactConditionRepoImpl;

	@Autowired
	FS_FactConditionAttributeRepoImpl fS_FactConditionAttributeRepoImpl;
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
    FS_FIUIndHighRiskCountryRepoImpl fS_FIUIndHighRiskCountryRepoImpl;
   
	@Autowired
    FS_FIUIndTerrorLocationRepoImpl fS_FIUIndTerrorLocationRepoImpl;


	private Logger LOGGER = LoggerFactory.getLogger(SumCashTxnFact.class);
	
	@Override
	public ComputedFactsVO getFactExecutor(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::CountryRIskFact@getFactExecutor (ENTRY) Called::::::::::",
				requVoObjParam.getReqId());
		String factName = null, accNo = null, custId = null, transMode = null, transType = null, 
				txnTime = null, txnId = null, reqId = null;
		try {
			computedFactsVOObj = new ComputedFactsVO();
			accNo = requVoObjParam.getAccountNo();
			custId = requVoObjParam.getCustomerId();
			txnId = requVoObjParam.getTxnId();
			reqId = requVoObjParam.getReqId();
			transMode = requVoObjParam.getTransactionMode();
			transType = requVoObjParam.getTxnType();			
			factName = factSetObj.getFact();
			Integer days = factSetObj.getDays();
			Integer hours = factSetObj.getHours();
			Integer months = factSetObj.getMonths();
			txnTime = requVoObjParam.getTxn_time();
			Range range = factSetObj.getRange();
			String condition = factSetObj.getCondition();
			TransactionDetailsDTO dto =null;
			
			if(condition!=null)
			{
				 dto = transactionService.getTransactionDetails(reqId, custId, accNo, txnId, transType);
			 if(dto!=null && dto.getCounterContryCode()!=null)
			{
			if(condition!=null && condition.equals("HIGH_RISK_COUNTRIES"))
			{
					FS_FIUIndHighRiskCountryEntity	countryEntity = fS_FIUIndHighRiskCountryRepoImpl.getCountryByritiria(requVoObjParam.getReqId(), dto.getCounterContryCode());
					if(countryEntity!=null)
					{
						computedFactsVOObj.setFact(factName);
						computedFactsVOObj.setStrValue("HIGH_RISK");	
					}
					else
					{
						computedFactsVOObj.setFact(factName);
						computedFactsVOObj.setStrValue("NO_HIGH_RISK");	
					}
					
				
			}
			}
			}
			else
			{
				 dto = transactionService.getTransactionDetails(reqId, custId, accNo, txnId, null,
							transMode, days, months, factSetObj, range);
				 if (dto != null && dto.getSumAmount() != null) {

						computedFactsVOObj.setFact(factName);
						computedFactsVOObj.setValue(dto.getSumAmount());
					}
			}

			

		} catch (Exception e) {
			LOGGER.error("Exception found in CountryRIskFact@getFactExecutor : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::CountryRIskFact@getFactExecutor (EXIT) End::::::::::\n\n",
					requVoObjParam.getReqId());
		}
		return computedFactsVOObj;

	}

}