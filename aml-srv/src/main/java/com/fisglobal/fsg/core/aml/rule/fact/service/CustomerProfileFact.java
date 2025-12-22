package com.fisglobal.fsg.core.aml.rule.fact.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisglobal.fsg.core.aml.entity.AccountDetailsEntity;
import com.fisglobal.fsg.core.aml.entity.CustomerDetailsEntity;
import com.fisglobal.fsg.core.aml.entity.FS_FactConditionAttributeEntity;
import com.fisglobal.fsg.core.aml.entity.FS_FactConditionEntity;
import com.fisglobal.fsg.core.aml.repo.CustomerDetailsService;
import com.fisglobal.fsg.core.aml.repo.FS_FactConditionAttributeRepoImpl;
import com.fisglobal.fsg.core.aml.repo.FS_FactConditionRepoImpl;
import com.fisglobal.fsg.core.aml.repo.TransactionDetailsDTO;
import com.fisglobal.fsg.core.aml.repo.TransactionService;
import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.Range;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.ComputedFactsVO;


@Service("CUSTOMER_PROFILEService")
public class CustomerProfileFact implements FactInterface{

	
	@Autowired
	TransactionService transactionService;
	
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	FS_FactConditionRepoImpl fS_FactConditionRepoImpl;

	@Autowired
	FS_FactConditionAttributeRepoImpl fS_FactConditionAttributeRepoImpl;

	

	private Logger LOGGER = LoggerFactory.getLogger(CustomerProfileFact.class);
	
	@Override
	public ComputedFactsVO getFactExecutor(RuleRequestVo requVoObjParam, Factset factSetObj,List<ComputedFactsVO> computedFacts ) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::CustomerProfileFact@getFactExecutor (ENTRY) Called::::::::::",
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
			computedFactsVOObj.setValue(new BigDecimal(0));
			TransactionDetailsDTO dto =null;

			if (condition != null) {
				if (condition.equals("NONPROFITENTITIES")) {

					String profile = null;

					FS_FactConditionEntity conditionEntity = fS_FactConditionRepoImpl.getFactCondititon(condition,
							requVoObjParam.getReqId());
					if (conditionEntity != null && conditionEntity.getId() != null) {

						List<FS_FactConditionAttributeEntity> conditionAttribute = fS_FactConditionAttributeRepoImpl
								.getCondititonAttributes(String.valueOf(conditionEntity.getId()),
										requVoObjParam.getReqId());
						if (conditionAttribute != null && conditionAttribute.size() > 0) {
							CustomerDetailsEntity custDetails = customerDetailsService.getCustomerDetails(requVoObjParam.getReqId(),custId);
							if (custDetails != null) {
								for (FS_FactConditionAttributeEntity gs : conditionAttribute) {
									if (gs.getAttributes().equals(custDetails.getCustomerCategory())) {
										profile = gs.getAttributes();
									}
								}
							}

						}
					}
					if (profile != null) {
						computedFactsVOObj.setFact(factName);						
						computedFactsVOObj.setStrValue(profile);
						computedFactsVOObj.setStrType("str");
					}
					else
					{
						computedFactsVOObj.setFact(factName);						
						computedFactsVOObj.setValue(new BigDecimal(0));
						computedFactsVOObj.setStrType("num");
					}

					/*if (profile != null) {
						 dto = transactionService.getTransactionDetails(reqId, custId, accNo, txnId, transType,
									transMode, days, months, factSetObj, range);
							if (dto != null && dto.getMaxAmount() != null) {

								computedFactsVOObj.setFact(factName);
								computedFactsVOObj.setValue((dto.getMaxAmount()));
								computedFactsVOObj.setStrValue(profile);
							}
							else
							{
								computedFactsVOObj.setFact(factName);
								computedFactsVOObj.setValue(new BigDecimal(0));
							}
						
					} else {

						computedFactsVOObj.setFact(factName);
						computedFactsVOObj.setValue(new BigDecimal(0));

					}
					*/
				}
				else if (condition.equals("ISPARTYTYPEAGENTORDEALER")) {

					String profile = null;

					FS_FactConditionEntity conditionEntity = fS_FactConditionRepoImpl.getFactCondititon(condition,
							requVoObjParam.getReqId());
					if (conditionEntity != null && conditionEntity.getId() != null) {

						List<FS_FactConditionAttributeEntity> conditionAttribute = fS_FactConditionAttributeRepoImpl
								.getCondititonAttributes(String.valueOf(conditionEntity.getId()),
										requVoObjParam.getReqId());
						if (conditionAttribute != null && conditionAttribute.size() > 0) {
							CustomerDetailsEntity custDetails = customerDetailsService.getCustomerDetails(requVoObjParam.getReqId(),custId);
							if (custDetails != null) {
								for (FS_FactConditionAttributeEntity gs : conditionAttribute) {
									if (gs.getAttributes().equals(custDetails.getCustomerType())) {
										profile = gs.getAttributes();
									}
								}
							}

						}
					}

					if (profile != null) {
						computedFactsVOObj.setFact(factName);						
						computedFactsVOObj.setStrValue(profile);
						computedFactsVOObj.setStrType("str");
					}
					else
					{
						computedFactsVOObj.setFact(factName);						
						computedFactsVOObj.setValue(new BigDecimal(0));
						computedFactsVOObj.setStrType("num");
					}
				}
				else if (condition.equals("LOW-CASH-PROFILE")) {

					String profile = null;

					FS_FactConditionEntity conditionEntity = fS_FactConditionRepoImpl.getFactCondititon(condition,
							requVoObjParam.getReqId());
					if (conditionEntity != null && conditionEntity.getId() != null) {

						List<FS_FactConditionAttributeEntity> conditionAttribute = fS_FactConditionAttributeRepoImpl
								.getCondititonAttributes(String.valueOf(conditionEntity.getId()),
										requVoObjParam.getReqId());
						if (conditionAttribute != null && conditionAttribute.size() > 0) {
							CustomerDetailsEntity custDetails = customerDetailsService.getCustomerDetails(requVoObjParam.getReqId(),custId);
							if (custDetails != null) {
								for (FS_FactConditionAttributeEntity gs : conditionAttribute) {
									if (gs.getAttributes().equals(custDetails.getCustomerCategory())) {
										profile = gs.getAttributes();
									}
								}
							}

						}
					}

					if (profile != null) {
						computedFactsVOObj.setFact(factName);						
						computedFactsVOObj.setStrValue(profile);
						computedFactsVOObj.setStrType("str");
					}
					else
					{
						computedFactsVOObj.setFact(factName);						
						computedFactsVOObj.setValue(new BigDecimal(0));
						computedFactsVOObj.setStrType("num");
					}
				}
				

			}
			else
			{
				
			}
			

		} catch (Exception e) {
			LOGGER.error("Exception found in CustomerProfileFact@getFactExecutor : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::CustomerProfileFact@getFactExecutor (EXIT) End::::::::::\n\n",
					requVoObjParam.getReqId());
		}
		return computedFactsVOObj;

	}

}