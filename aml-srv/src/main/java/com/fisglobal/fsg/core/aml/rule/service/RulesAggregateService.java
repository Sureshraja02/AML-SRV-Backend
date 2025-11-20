package com.fisglobal.fsg.core.aml.rule.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.aml.repo.CustomerDetailsRepoImpl;
import com.fisglobal.fsg.core.aml.repo.TransactionDetailsRepositryImpl;
import com.fisglobal.fsg.core.aml.repo.TransactionDetailsRepositryImpl2;
import com.fisglobal.fsg.core.aml.rule.intr.RuleExecutorIntr;
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
public class RulesAggregateService implements RuleExecutorIntr {

	private Logger LOGGER = LoggerFactory.getLogger(RulesAggregateService.class);

	@Autowired
	TransactionDetailsRepositryImpl transactionDetailsRepositryImpl;

	@Autowired
	TransactionDetailsRepositryImpl2 transactionDetailsRepositryImpl2;

	@Autowired
	CustomerDetailsRepoImpl customerDetailsRepoImpl;
	
	@Autowired
	RulesUtils rulesUtils;

	@Override
	public ComputedFactsVO ruleOfCountProcess(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getCountValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfCountProcess : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

	@Override
	public ComputedFactsVO ruleOfSUMProcess(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfSUMProcess (SUM) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl.getSumValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfSUMProcess : {}", e);
		} finally {
			factName = null; accNo = null; custId = null; transMode = null; transType = null; fieldName = null;
			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfSUMProcess (SUM) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

	@Override
	public ComputedFactsVO ruleOfMaxProcess(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfMaxProcess (MAX) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getMaxValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfMaxProcess : {}", e);
		} finally {
			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfMaxProcess (MAX) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

	@Override
	public ComputedFactsVO ruleOfAVGProcess(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfAVGProcess (AVG) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl.getSumValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfAVGProcess : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfAVGProcess (AVG) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

	@Override
	public ComputedFactsVO ruleOfCommAggregateProcess(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfCommAggregateProcess (Common) Called::::::::::", requVoObjParam.getReqId());
		try {

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfCommAggregateProcess : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfCommAggregateProcess (Common) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

	@Override
	public ComputedFactsVO ruleOfPreviousForexTurnoverProcess(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfPreviousForexTurnoverProcess (PREVIOUS_FOREX_TURNOVER) Called::::::::::", requVoObjParam.getReqId());
		try {

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfPreviousForexTurnoverProcess : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfPreviousForexTurnoverProcess (PREVIOUS_FOREX_TURNOVER) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

	@Override
	public ComputedFactsVO ruleOfFDConversion(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfFDConversion (FD_CONVERSION) Called::::::::::", requVoObjParam.getReqId());
		try {

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfFDConversion : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfFDConversion (FD_CONVERSION) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

	@Override
	public ComputedFactsVO ruleOfLargerDeposite(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfLargerDeposite (LARGE_DEPOSIT) Called::::::::::", requVoObjParam.getReqId());
		try {
			String factName = null, accNo = null, custId = null, transMode = null, transType = null, fieldName = null;
			computedFactsVOObj = new ComputedFactsVO();
			fieldName = factSetObj.getField();
			factName = factSetObj.getFact();
			Integer days = factSetObj.getDays();

			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getLargerDeposite(requVoObjParam.getReqId(), accNo, custId, transMode, transType, days, fieldName, columnName);
					computedFactsVOObj.setValue((finalValue));
					computedFactsVOObj.setFact(factName);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfLargerDeposite : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfLargerDeposite (LARGE_DEPOSIT) End:::::::::\n\n:", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

	@Override
	public ComputedFactsVO ruleOfImmediateWithdraw(RuleRequestVo requVoObjParam, Factset factSetObj, ComputedFactsVO computedFactsVODeopObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfImmediateWithdraw (IMMEDIATE_WITHDRAWAL) Called::::::::::", requVoObjParam.getReqId());
		try {

			String factName = null, accNo = null, custId = null, transMode = null, transType = null, fieldName = null;
			computedFactsVOObj = new ComputedFactsVO();
			fieldName = factSetObj.getField();
			factName = factSetObj.getFact();
			Integer days = factSetObj.getDays();

			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					//finalValue = rulesUtils.toGetWithdaralFacts(requVoObjParam.getReqId(), accNo, custId, transMode, transType, days, fieldName, columnName);
					
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfImmediateWithdraw : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfImmediateWithdraw (IMMEDIATE_WITHDRAWAL) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

	@Override
	public ComputedFactsVO ruleOfAvgCreditDebit(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfAvgCreditDebit (SUM) Called::::::::::", requVoObjParam.getReqId());
		String factName = null, accNo = null, custId = null, transMode = null, transType = null, fieldName = null;
		try {
			computedFactsVOObj = new ComputedFactsVO();
			accNo = requVoObjParam.getAccountNo();
			custId = requVoObjParam.getCustomerId();
			transMode = requVoObjParam.getTransactionMode();
			transType = requVoObjParam.getTxnType();
			fieldName = factSetObj.getField();
			factName = factSetObj.getFact();
			Integer months = factSetObj.getMonths();
			Integer days = factSetObj.getDays();
			Integer finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getAvgCreditDebit(requVoObjParam.getReqId(), accNo, custId, transMode, transType, days, fieldName,months, columnName);
					if (finalValue > 0) {
						computedFactsVOObj.setValue(new BigDecimal(finalValue));
						computedFactsVOObj.setFact(factName);
					}
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfAvgCreditDebit : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfAvgCreditDebit (SUM) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

	@Override
	public ComputedFactsVO ruleOfSumCreditDebitAmount(RuleRequestVo requVoObjParam, Factset factSetObj) {
		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfSumCreditAmount (SUM) Called::::::::::", requVoObjParam.getReqId());
		String factName = null, accNo = null, custId = null, transMode = null, transType = null, fieldName = null;
		try {
			computedFactsVOObj = new ComputedFactsVO();
			accNo = requVoObjParam.getAccountNo();
			custId = requVoObjParam.getCustomerId();
			transMode = requVoObjParam.getTransactionMode();
			transType = requVoObjParam.getTxnType();
			fieldName = factSetObj.getField();
			factName = factSetObj.getFact();
			Integer days = factSetObj.getDays();
			Integer months = factSetObj.getMonths();
			Integer finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getSumCreditDebitAmount(requVoObjParam.getReqId(), accNo, custId, transMode, transType, days, fieldName,months, columnName);
					if (finalValue > 0) {
						computedFactsVOObj.setValue(new BigDecimal(finalValue));
						computedFactsVOObj.setFact(factName);
					}
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfSumCreditAmount : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfSumCreditAmount (SUM) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	}

	@Override
	public ComputedFactsVO ruleOfMinProcess(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfMinProcess (COUNT) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getMinValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfMinProcess : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfMinProcess (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	}

	@Override
	public ComputedFactsVO ruleOfCountCreditDebitAmount(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfCountCreditDebitAmount (SUM) Called::::::::::", requVoObjParam.getReqId());
		String factName = null, accNo = null, custId = null, transMode = null, transType = null, fieldName = null;
		try {
			computedFactsVOObj = new ComputedFactsVO();
			accNo = requVoObjParam.getAccountNo();
			custId = requVoObjParam.getCustomerId();
			transMode = requVoObjParam.getTransactionMode();
			transType = requVoObjParam.getTxnType();
			fieldName = factSetObj.getField();
			factName = factSetObj.getFact();
			Integer days = factSetObj.getDays();
			Integer months = factSetObj.getMonths();
			Integer finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getCountCreditDebit(requVoObjParam.getReqId(), accNo, custId, transMode, transType, days, fieldName,months, columnName);
					if (finalValue > 0) {
						computedFactsVOObj.setValue(new BigDecimal(finalValue));
						computedFactsVOObj.setFact(factName);
					}
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfCountCreditDebitAmount : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfCountCreditDebitAmount (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	}

	@Override
	public ComputedFactsVO ruleOfAvgCreditDebitAmount(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfAvgCreditDebitAmount (AVG) Called::::::::::", requVoObjParam.getReqId());
		String factName = null, accNo = null, custId = null, transMode = null, transType = null, fieldName = null;
		try {
			computedFactsVOObj = new ComputedFactsVO();
			accNo = requVoObjParam.getAccountNo();
			custId = requVoObjParam.getCustomerId();
			transMode = requVoObjParam.getTransactionMode();
			transType = requVoObjParam.getTxnType();
			fieldName = factSetObj.getField();
			factName = factSetObj.getFact();
			Integer days = factSetObj.getDays();
			Integer months = factSetObj.getMonths();
			Integer finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getAvgCreditDebit(requVoObjParam.getReqId(), accNo, custId, transMode, transType, days, fieldName,months, columnName);
					if (finalValue > 0) {
						computedFactsVOObj.setValue(new BigDecimal(finalValue));
						computedFactsVOObj.setFact(factName);
					}
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfAvgCreditDebitAmount : {}", e);
		} finally {

			LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfAvgCreditDebitAmount (AVG) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	}

	@Override
	public ComputedFactsVO ruleOfCountCashDeposit(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getCountCashDepositValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfCountProcess : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	}

	@Override
	public ComputedFactsVO ruleOfSumCashDeposit(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getSumCashDepositValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfCountProcess : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	}
	
	@Override
	public ComputedFactsVO ruleOfSumNonCashDeposit(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfSumNonCashDeposit (COUNT) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getSumNonCashDepositValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfSumNonCashDeposit : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfSumNonCashDeposit (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	}

	@Override
	public ComputedFactsVO ruleOfAvgCashDeposit(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getAvgCashDepositValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfCountProcess : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	}

	@Override
	public ComputedFactsVO ruleOfCountCashWithdraw(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getCountCashWithdrawValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfCountProcess : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	}

	@Override
	public ComputedFactsVO ruleOfSumCashWithdraw(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getSumCashWithdrawValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfCountProcess : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	}

	@Override
	public ComputedFactsVO ruleOfAvgCashWithdraw(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getAugCashWithdrawValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfCountProcess : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	}

	@Override
	public ComputedFactsVO ruleOfSumNonCashWithdraw(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getSumNonCashWithdrawValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfCountProcess : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	}

	@Override
	public ComputedFactsVO ruleOfSumNonDeposit(RuleRequestVo requVoObjParam, Factset factSetObj) {


		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfSumNonDeposit (COUNT) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getSumNonCashWithdrawValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfSumNonDeposit : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfSumNonDeposit (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	
	}

	@Override
	public ComputedFactsVO ruleOfCountAccountTransfer(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getCountAccountTransferValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfCountProcess : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfCountProcess (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	}

	@Override
	public ComputedFactsVO ruleOfSumCashTxn(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfSumCashTxn (COUNT) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getSumCashTxnValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfSumCashTxn : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfSumCashTxn (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	}
	
	@Override
	public ComputedFactsVO ruleOfSumNonCashTxn(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfSumNonCashTxn (COUNT) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getSumNonCashTxnValue(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfSumNonCashTxn : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfSumNonCashTxn (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	}
	
	@Override
	public ComputedFactsVO ruleOfSumAccountToAccountTxn(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfSumAccountToAccountTxn (COUNT) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getSumAccountToAccountTxn(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfSumCashTxn : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfSumAccountToAccountTxn (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	}

	@Override
	public ComputedFactsVO ruleOfSumAccountTransfer(RuleRequestVo requVoObjParam, Factset factSetObj) {

		ComputedFactsVO computedFactsVOObj = null;
		LOGGER.info("REQID : [{}]::::::::::::RulesExecutorService@ruleOfSumAccountToAccountTxn (COUNT) Called::::::::::", requVoObjParam.getReqId());
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
			BigDecimal finalValue = null;
			if (StringUtils.isNotBlank(fieldName) && fieldName.contains(".")) {
				String tableName = fieldName.split("\\.")[0];
				String columnName = fieldName.split("\\.")[1];
				if (StringUtils.isNotBlank(tableName) && tableName.equalsIgnoreCase("TRANSACTION")) {
					finalValue = transactionDetailsRepositryImpl2.getSumAccountTxn(requVoObjParam.getReqId(), accNo, custId, transMode, transType, hours, days, months, fieldName, columnName);
					computedFactsVOObj.setFact(factName);
					computedFactsVOObj.setValue(finalValue);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception found in RulesExecutorService@ruleOfSumCashTxn : {}", e);
		} finally {
			LOGGER.info("REQ ID : [{}]::::::::::::RulesExecutorService@ruleOfSumAccountToAccountTxn (COUNT) End::::::::::\n\n", requVoObjParam.getReqId());
		}
		return computedFactsVOObj;
	
	}

}
