package com.fisglobal.fsg.core.aml.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fisglobal.fsg.core.aml.entity.TransactionDetailsEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * 
 * @author : E5554365 (Prabakaran.R)
 * @Project : aml-srv
 * @year : 2025
 */
@Repository
public class TransactionDetailsRepositryImpl2 {

	private Logger LOGGER = LoggerFactory.getLogger(TransactionDetailsRepositryImpl2.class);

	@Autowired
	EntityManager entityManager;

	/**
	 * 
	 * @param reqId
	 * @param accNo
	 * @param custId
	 * @param transMode
	 * @param transType
	 * @param days
	 * @param fieldName
	 * @return getSumValue Integer
	 */
	public Integer ruleOfLargerDeposite(String reqId, String accNo, String custId, String transMode, String transType, Integer days, String fieldName, String columnName) {
		LOGGER.info("REQID : [{}] - TransactionDetailsRepositryImpl@getSumValue method called...........", reqId);
		Integer retnVal = 0;
		CriteriaBuilder cb = null;
		List<Predicate> predicates = null;
		CriteriaQuery<Object[]> cq = null;
		Root<TransactionDetailsEntity> rootBk = null;
		try {
			cb = entityManager.getCriteriaBuilder();
			cq = cb.createQuery(Object[].class);
			predicates = new ArrayList<Predicate>();
			rootBk = cq.from(TransactionDetailsEntity.class);
			if (StringUtils.isNotBlank(accNo)) {
				predicates.add(cb.equal(rootBk.get("accountNo"), accNo));
			}
			if (StringUtils.isNotBlank(custId)) {
				predicates.add(cb.equal(rootBk.get("customerId"), custId));
			}			
			
			 predicates.add(cb.equal(rootBk.get("depositorWithdrawal"), "D"));
			
			LOGGER.info("REQID : [{}] - No of days : [{}]", reqId, days);

			if (days != null) {
				Expression<java.sql.Date> txnDateAsDate = cb.function("TRANS_DATE", java.sql.Date.class, rootBk.get("transactionDate"), cb.literal("YYYY-MM-DD") // format of stored string
				);

				// Calculate date range in Java
				LocalDate currentDateTdy = LocalDate.now();
				LocalDate stDate = currentDateTdy.minusDays(days);
				// Convert LocalDate to String in same format as DB
				// String todayStr = today.toString(); // yyyy-MM-dd//String startDateStr =
				// startDate.toString();
				Predicate betweenDates = cb.between(txnDateAsDate, java.sql.Date.valueOf(stDate), java.sql.Date.valueOf(currentDateTdy));
				predicates.add(betweenDates);
			}
			LOGGER.info("REQID : [{}] - columnName is : [{}]", reqId, columnName);
			if (predicates != null && StringUtils.isNotBlank(columnName) && columnName.equalsIgnoreCase("amount")) {
				cq.where(cb.and(predicates.toArray(new Predicate[0])));
				cq.multiselect(cb.count(rootBk), cb.max(rootBk.get("amount")));
				Object[] result = entityManager.createQuery(cq).getSingleResult();
				if (result != null && result.length > 1) {
					retnVal = (Integer) result[1];
					LOGGER.info("REQID : [{}] - retnVal : [{}]", reqId, retnVal);
				} else {
					LOGGER.info("REQID : [{}] - result object is NUll, so retnVal : [{}]", reqId, retnVal);
				}
			}
		} catch (Exception e) {
			retnVal = 0;
			LOGGER.info("REQID : [{}] - Exception found in TransactionDetailsRepositryImpl@getSumValue :{}", reqId, e);
		} finally {
			cb = null;
			predicates = null;
			cq = null;
			rootBk = null;
			LOGGER.info("REQID : [{}] - TransactionDetailsRepositryImpl@getSumValue method End...........\n\n", reqId);
		}
		return retnVal;
	}
	
	
	public Integer ruleOfImmediateWithdraw(String reqId, String accNo, String custId, String transMode, String transType, Integer days, String fieldName, String columnName) {
		LOGGER.info("REQID : [{}] - TransactionDetailsRepositryImpl@ruleOfImmediateWithdraw method called...........", reqId);
		Integer retnVal = 0;
		CriteriaBuilder cb = null;
		List<Predicate> predicates = null;
		CriteriaQuery<Object[]> cq = null;
		Root<TransactionDetailsEntity> rootBk = null;
		try {
			cb = entityManager.getCriteriaBuilder();
			cq = cb.createQuery(Object[].class);
			predicates = new ArrayList<Predicate>();
			rootBk = cq.from(TransactionDetailsEntity.class);
			if (StringUtils.isNotBlank(accNo)) {
				predicates.add(cb.equal(rootBk.get("accountNo"), accNo));
			}
			if (StringUtils.isNotBlank(custId)) {
				predicates.add(cb.equal(rootBk.get("customerId"), custId));
			}			
			
			
			
			LOGGER.info("REQID : [{}] - No of days : [{}]", reqId, days);

			if (days != null) {
				Expression<java.sql.Date> txnDateAsDate = cb.function("TRANS_DATE", java.sql.Date.class, rootBk.get("transactionDate"), cb.literal("YYYY-MM-DD") // format of stored string
				);

				// Calculate date range in Java
				LocalDate currentDateTdy = LocalDate.now();
				LocalDate stDate = currentDateTdy.minusDays(days);
				// Convert LocalDate to String in same format as DB
				// String todayStr = today.toString(); // yyyy-MM-dd//String startDateStr =
				// startDate.toString();
				Predicate betweenDates = cb.between(txnDateAsDate, java.sql.Date.valueOf(stDate), java.sql.Date.valueOf(currentDateTdy));
				predicates.add(betweenDates);
			}
			LOGGER.info("REQID : [{}] - columnName is : [{}]", reqId, columnName);
			if (predicates != null && StringUtils.isNotBlank(columnName) && columnName.equalsIgnoreCase("amount")) {
				cq.where(cb.and(predicates.toArray(new Predicate[0])));
				cq.multiselect(cb.count(rootBk), cb.max(rootBk.get("amount")));
				Object[] result = entityManager.createQuery(cq).getSingleResult();
				if (result != null && result.length > 1) {
					retnVal = (Integer) result[1];
					LOGGER.info("REQID : [{}] - retnVal : [{}]", reqId, retnVal);
				} else {
					LOGGER.info("REQID : [{}] - result object is NUll, so retnVal : [{}]", reqId, retnVal);
				}
			}
		} catch (Exception e) {
			retnVal = 0;
			LOGGER.info("REQID : [{}] - Exception found in TransactionDetailsRepositryImpl@ruleOfImmediateWithdraw :{}", reqId, e);
		} finally {
			cb = null;
			predicates = null;
			cq = null;
			rootBk = null;
			LOGGER.info("REQID : [{}] - TransactionDetailsRepositryImpl@ruleOfImmediateWithdraw method End...........\n\n", reqId);
		}
		return retnVal;
	}

	
	public Integer ruleOfSumCreditAmount(String reqId, String accNo, String custId, String transMode, String transType, Integer days, String fieldName, String columnName) {
		LOGGER.info("REQID : [{}] - TransactionDetailsRepositryImpl@ruleOfSumCreditAmount method called...........", reqId);
		Integer retnVal = 0;
		CriteriaBuilder cb = null;
		List<Predicate> predicates = null;
		CriteriaQuery<Object[]> cq = null;
		Root<TransactionDetailsEntity> rootBk = null;
		try {
			cb = entityManager.getCriteriaBuilder();
			cq = cb.createQuery(Object[].class);
			predicates = new ArrayList<Predicate>();
			rootBk = cq.from(TransactionDetailsEntity.class);
			if (StringUtils.isNotBlank(accNo)) {
				predicates.add(cb.equal(rootBk.get("accountNo"), accNo));
			}
			if (StringUtils.isNotBlank(custId)) {
				predicates.add(cb.equal(rootBk.get("customerId"), custId));
			}	
			
			
			 List<String> type = Arrays.asList("D","W");
			 Predicate inClause = rootBk.get("depositorWithdrawal").in(type);
			 predicates.add(inClause);
			
			LOGGER.info("REQID : [{}] - No of days : [{}]", reqId, days);

			if (days != null) {
				Expression<java.sql.Date> txnDateAsDate = cb.function("TRANS_DATE", java.sql.Date.class, rootBk.get("transactionDate"), cb.literal("YYYY-MM-DD") // format of stored string
				);

				// Calculate date range in Java
				LocalDate currentDateTdy = LocalDate.now();
				LocalDate stDate = currentDateTdy.minusDays(days);
				// Convert LocalDate to String in same format as DB
				// String todayStr = today.toString(); // yyyy-MM-dd//String startDateStr =
				// startDate.toString();
				Predicate betweenDates = cb.between(txnDateAsDate, java.sql.Date.valueOf(stDate), java.sql.Date.valueOf(currentDateTdy));
				predicates.add(betweenDates);
			}
			LOGGER.info("REQID : [{}] - columnName is : [{}]", reqId, columnName);
			if (predicates != null && StringUtils.isNotBlank(columnName) && columnName.equalsIgnoreCase("amount")) {
				cq.where(cb.and(predicates.toArray(new Predicate[0])));
				cq.multiselect(cb.count(rootBk), cb.sum(rootBk.get("amount")));
				Object[] result = entityManager.createQuery(cq).getSingleResult();
				if (result != null && result.length > 1) {
					retnVal = (Integer) result[1];
					LOGGER.info("REQID : [{}] - retnVal : [{}]", reqId, retnVal);
				} else {
					LOGGER.info("REQID : [{}] - result object is NUll, so retnVal : [{}]", reqId, retnVal);
				}
			}
		} catch (Exception e) {
			retnVal = 0;
			LOGGER.info("REQID : [{}] - Exception found in TransactionDetailsRepositryImpl@ruleOfSumCreditAmount :{}", reqId, e);
		} finally {
			cb = null;
			predicates = null;
			cq = null;
			rootBk = null;
			LOGGER.info("REQID : [{}] - TransactionDetailsRepositryImpl@ruleOfSumCreditAmount method End...........\n\n", reqId);
		}
		return retnVal;
	}
	
	public Integer ruleOfAvgCreditDebit(String reqId, String accNo, String custId, String transMode, String transType, Integer days, String fieldName, String columnName) {
		LOGGER.info("REQID : [{}] - TransactionDetailsRepositryImpl@ruleOfAvgCreditDebit method called...........", reqId);
		Integer retnVal = 0;
		CriteriaBuilder cb = null;
		List<Predicate> predicates = null;
		CriteriaQuery<Object[]> cq = null;
		Root<TransactionDetailsEntity> rootBk = null;
		try {
			cb = entityManager.getCriteriaBuilder();
			cq = cb.createQuery(Object[].class);
			predicates = new ArrayList<Predicate>();
			rootBk = cq.from(TransactionDetailsEntity.class);
			if (StringUtils.isNotBlank(accNo)) {
				predicates.add(cb.equal(rootBk.get("accountNo"), accNo));
			}
			if (StringUtils.isNotBlank(custId)) {
				predicates.add(cb.equal(rootBk.get("customerId"), custId));
			}			
			
			 List<String> type = Arrays.asList("D","W");
			 Predicate inClause = rootBk.get("depositorWithdrawal").in(type);
			 predicates.add(inClause);
			
			
			LOGGER.info("REQID : [{}] - No of days : [{}]", reqId, days);

			if (days != null) {
				Expression<java.sql.Date> txnDateAsDate = cb.function("TRANS_DATE", java.sql.Date.class, rootBk.get("transactionDate"), cb.literal("YYYY-MM-DD") // format of stored string
				);

				// Calculate date range in Java
				LocalDate currentDateTdy = LocalDate.now();
				LocalDate stDate = currentDateTdy.minusDays(days);
				// Convert LocalDate to String in same format as DB
				// String todayStr = today.toString(); // yyyy-MM-dd//String startDateStr =
				// startDate.toString();
				Predicate betweenDates = cb.between(txnDateAsDate, java.sql.Date.valueOf(stDate), java.sql.Date.valueOf(currentDateTdy));
				predicates.add(betweenDates);
			}
			LOGGER.info("REQID : [{}] - columnName is : [{}]", reqId, columnName);
			if (predicates != null && StringUtils.isNotBlank(columnName) && columnName.equalsIgnoreCase("amount")) {
				cq.where(cb.and(predicates.toArray(new Predicate[0])));
				cq.multiselect(cb.count(rootBk), cb.avg(rootBk.get("amount")));
				Object[] result = entityManager.createQuery(cq).getSingleResult();
				if (result != null && result.length > 1) {
					retnVal = (Integer) result[1];
					LOGGER.info("REQID : [{}] - retnVal : [{}]", reqId, retnVal);
				} else {
					LOGGER.info("REQID : [{}] - result object is NUll, so retnVal : [{}]", reqId, retnVal);
				}
			}
		} catch (Exception e) {
			retnVal = 0;
			LOGGER.info("REQID : [{}] - Exception found in TransactionDetailsRepositryImpl@ruleOfAvgCreditDebit :{}", reqId, e);
		} finally {
			cb = null;
			predicates = null;
			cq = null;
			rootBk = null;
			LOGGER.info("REQID : [{}] - TransactionDetailsRepositryImpl@ruleOfAvgCreditDebit method End...........\n\n", reqId);
		}
		return retnVal;
	}
}
