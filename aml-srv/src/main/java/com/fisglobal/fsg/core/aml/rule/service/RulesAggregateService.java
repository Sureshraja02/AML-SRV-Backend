package com.fisglobal.fsg.core.aml.rule.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.aml.entity.CustomerDetailsEntity;
import com.fisglobal.fsg.core.aml.repo.CustomerDetailsRepoImpl;
import com.fisglobal.fsg.core.aml.repo.TransactionDetailsRepositryImpl;
import com.fisglobal.fsg.core.aml.rule.intr.RuleExecutorIntr;
import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.RuleResposeDetailsVO;

@Component
public class RulesAggregateService implements RuleExecutorIntr {

	private Logger LOGGER = LoggerFactory.getLogger(RulesAggregateService.class);

	@Autowired
	TransactionDetailsRepositryImpl transactionDetailsRepositryImpl;
	
	@Autowired
	CustomerDetailsRepoImpl customerDetailsRepoImpl;

	@Override
	public RuleResposeDetailsVO ruleOfCountProcess(RuleRequestVo requVoObjParam, Factset factSetObj) {
		RuleResposeDetailsVO ruleResposeDetailsVO = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) Called::::::::::", requVoObjParam.getReqId());
		try {
			ruleResposeDetailsVO = new RuleResposeDetailsVO();
			ruleResposeDetailsVO.setAccountHolderType("");
			ruleResposeDetailsVO.setAccountStatus("");
			ruleResposeDetailsVO.setReqId(requVoObjParam.getReqId());
			ruleResposeDetailsVO.setValue(new BigDecimal(0));
		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfCountProcess : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return ruleResposeDetailsVO;
	}

	@Override
	public RuleResposeDetailsVO ruleOfSUMProcess(RuleRequestVo requVoObjParam, Factset factSetObj) {
		RuleResposeDetailsVO ruleResposeDetailsVO = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfSUMProcess (SUM) Called::::::::::", requVoObjParam.getReqId());
		String factName = null, accNo = null,custId = null,transMode = null, transType = null, fieldName = null;
		try {
			ruleResposeDetailsVO = new RuleResposeDetailsVO();
			accNo = requVoObjParam.getAccountNo();
			custId = requVoObjParam.getCustomerId();
			transMode = requVoObjParam.getTransactionMode();
			transType = requVoObjParam.getTxnType();
			fieldName = factSetObj.getField();
			factName = factSetObj.getFact();
			Integer days = factSetObj.getDays();
			Integer finalValue = null;
			if(StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if(StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) { 
					finalValue = transactionDetailsRepositryImpl.getSumValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, days, fieldName, columnName);
				}
			}
			
			CustomerDetailsEntity customerEnityObj = customerDetailsRepoImpl.getCustomerDetailsByCustId(custId);
			
			ruleResposeDetailsVO.setAccountHolderType(customerEnityObj.getCustomerType());
			ruleResposeDetailsVO.setAccountStatus("");
			ruleResposeDetailsVO.setReqId(requVoObjParam.getReqId());
			ruleResposeDetailsVO.setValue(new BigDecimal(finalValue));
		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfSUMProcess : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfSUMProcess (SUM) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return ruleResposeDetailsVO;
	}

	@Override
	public RuleResposeDetailsVO ruleOfMaxProcess(RuleRequestVo requVoObjParam, Factset factSetObj) {
		RuleResposeDetailsVO ruleResposeDetailsVO = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfMaxProcess (MAX) Called::::::::::", requVoObjParam.getReqId());
		try {
			ruleResposeDetailsVO = new RuleResposeDetailsVO();
			ruleResposeDetailsVO.setAccountHolderType("");
			ruleResposeDetailsVO.setAccountStatus("");
			ruleResposeDetailsVO.setReqId(requVoObjParam.getReqId());
			ruleResposeDetailsVO.setValue(new BigDecimal(0));
		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfMaxProcess : {}", e);
		} finally {
			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfMaxProcess (MAX) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return ruleResposeDetailsVO;
	}

	@Override
	public RuleResposeDetailsVO ruleOfAVGProcess(RuleRequestVo requVoObjParam, Factset factSetObj) {
		RuleResposeDetailsVO ruleResposeDetailsVO = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfAVGProcess (AVG) Called::::::::::", requVoObjParam.getReqId());
		try {
			ruleResposeDetailsVO = new RuleResposeDetailsVO();
			ruleResposeDetailsVO.setAccountHolderType("");
			ruleResposeDetailsVO.setAccountStatus("");
			ruleResposeDetailsVO.setReqId(requVoObjParam.getReqId());
			ruleResposeDetailsVO.setValue(new BigDecimal(0));
		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfAVGProcess : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfAVGProcess (AVG) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return ruleResposeDetailsVO;
	}

	@Override
	public RuleResposeDetailsVO ruleOfCommAggregateProcess(RuleRequestVo requVoObjParam, Factset factSetObj) {
		RuleResposeDetailsVO ruleResposeDetailsVO = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfCommAggregateProcess (Common) Called::::::::::", requVoObjParam.getReqId());
		try {
			ruleResposeDetailsVO = new RuleResposeDetailsVO();
			ruleResposeDetailsVO.setAccountHolderType("");
			ruleResposeDetailsVO.setAccountStatus("");
			ruleResposeDetailsVO.setReqId(requVoObjParam.getReqId());
			ruleResposeDetailsVO.setValue(new BigDecimal(0));
		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfCommAggregateProcess : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfCommAggregateProcess (Common) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return ruleResposeDetailsVO;
	}

	@Override
	public RuleResposeDetailsVO ruleOfPreviousForexTurnoverProcess(RuleRequestVo requVoObjParam, Factset factSetObj) {
		RuleResposeDetailsVO ruleResposeDetailsVO = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfPreviousForexTurnoverProcess (PREVIOUS_FOREX_TURNOVER) Called::::::::::", requVoObjParam.getReqId());
		try {
			ruleResposeDetailsVO = new RuleResposeDetailsVO();
			ruleResposeDetailsVO.setAccountHolderType("");
			ruleResposeDetailsVO.setAccountStatus("");
			ruleResposeDetailsVO.setReqId(requVoObjParam.getReqId());
			ruleResposeDetailsVO.setValue(new BigDecimal(0));
		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfPreviousForexTurnoverProcess : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfPreviousForexTurnoverProcess (PREVIOUS_FOREX_TURNOVER) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return ruleResposeDetailsVO;
	}

	@Override
	public RuleResposeDetailsVO ruleOfFDConversion(RuleRequestVo requVoObjParam, Factset factSetObj) {
		RuleResposeDetailsVO ruleResposeDetailsVO = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfFDConversion (FD_CONVERSION) Called::::::::::", requVoObjParam.getReqId());
		try {
			ruleResposeDetailsVO = new RuleResposeDetailsVO();
			ruleResposeDetailsVO.setAccountHolderType("");
			ruleResposeDetailsVO.setAccountStatus("");
			ruleResposeDetailsVO.setReqId(requVoObjParam.getReqId());
			ruleResposeDetailsVO.setValue(new BigDecimal(0));
		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfFDConversion : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfFDConversion (FD_CONVERSION) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return ruleResposeDetailsVO;
	}

	@Override
	public RuleResposeDetailsVO ruleOfLargerDeposite(RuleRequestVo requVoObjParam, Factset factSetObj) {
		RuleResposeDetailsVO ruleResposeDetailsVO = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfLargerDeposite (LARGE_DEPOSIT) Called::::::::::", requVoObjParam.getReqId());
		try {
			ruleResposeDetailsVO = new RuleResposeDetailsVO();
			ruleResposeDetailsVO.setAccountHolderType("");
			ruleResposeDetailsVO.setAccountStatus("");
			ruleResposeDetailsVO.setReqId(requVoObjParam.getReqId());
			ruleResposeDetailsVO.setValue(new BigDecimal(0));
		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfLargerDeposite : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfLargerDeposite (LARGE_DEPOSIT) End:::::::::\n\n:", requVoObjParam.getReqId());
		}
		return ruleResposeDetailsVO;
	}

	@Override
	public RuleResposeDetailsVO ruleOfImmediateWithdraw(RuleRequestVo requVoObjParam, Factset factSetObj) {
		RuleResposeDetailsVO ruleResposeDetailsVO = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfImmediateWithdraw (IMMEDIATE_WITHDRAWAL) Called::::::::::", requVoObjParam.getReqId());
		try {
			ruleResposeDetailsVO = new RuleResposeDetailsVO();
			ruleResposeDetailsVO.setAccountHolderType("");
			ruleResposeDetailsVO.setAccountStatus("");
			ruleResposeDetailsVO.setReqId(requVoObjParam.getReqId());
			ruleResposeDetailsVO.setValue(new BigDecimal(0));
		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfImmediateWithdraw : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfImmediateWithdraw (IMMEDIATE_WITHDRAWAL) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return ruleResposeDetailsVO;
	}

}
