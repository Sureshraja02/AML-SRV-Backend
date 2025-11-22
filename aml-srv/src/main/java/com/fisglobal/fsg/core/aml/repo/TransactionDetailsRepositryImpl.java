package com.fisglobal.fsg.core.aml.repo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fisglobal.fsg.core.aml.entity.TransactionDetailsEntity;
import com.fisglobal.fsg.core.aml.rule.process.request.Range;

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
public class TransactionDetailsRepositryImpl {

	private Logger LOGGER = LoggerFactory.getLogger(TransactionDetailsRepositryImpl.class);

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
	public BigDecimal getSumValue(String reqId, String accNo, String custId, String transMode, String transType, Integer hours, Integer days, Integer months,  String fieldName, String columnName,Range range) {
		LOGGER.info("REQID : [{}] - TransactionDetailsRepositryImpl@getSumValue method called...........", reqId);
		BigDecimal retnVal = null;
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
			LOGGER.info("REQID : [{}] - transType : [{}]", reqId, transType);
			if (StringUtils.isNotBlank(transType)) {
				predicates.add(cb.equal(rootBk.get("depositorWithdrawal"), transType));
			}
			LOGGER.info("REQID : [{}] - No of days : [{}]", reqId, days);

			 if (range != null) {
					if (range.getMin() != null && range.getMax() != null) {
						predicates.add(cb.between(rootBk.get("amount"), range.getMin(), range.getMax()));
					}
					else if (range.getMin() != null) {
					    // Only min present → greaterThanOrEqualTo
					    predicates.add(cb.greaterThanOrEqualTo(rootBk.get("amount"), range.getMin()));
					} else if (range.getMax() != null) {
					    // Only max present → lessThanOrEqualTo
					    predicates.add(cb.lessThanOrEqualTo(rootBk.get("amount"), range.getMax()));
					}

				}

			if (days != null) {
				//Expression<java.sql.Date> txnDateAsDate = cb.function("TRANS_DATE", java.sql.Date.class, rootBk.get("transactionDate"), cb.literal("YYYY-MM-DD") // format of stored string);
				
			/*	Expression<java.sql.Date> txnDateAsDate =
					    cb.function("TO_DATE", java.sql.Date.class,
					        rootBk.get("transactionDate"),
					        cb.literal("YYYY-MM-DD"));
				*/
				LocalDate currentDateTdy = LocalDate.now();
				LocalDate stDate = currentDateTdy.minusDays(days);
				// Convert LocalDate to String in same format as DB
				 String todayStr = currentDateTdy.toString(); // yyyy-MM-dd
				String startDateStr = stDate.toString();
				//LOGGER.info("REQID : [{}] - columnName is : [{}]", reqId, stDate);
//				LOGGER.info("REQID : [{}] - columnName is : [{}]", reqId, String.valueOf(stDate));
//				LOGGER.info("REQID : [{}] - columnName is : [{}]", reqId, String.valueOf(currentDateTdy));
				LOGGER.info("REQID : [{}] - Current / today Date : [{}]  startDateStr : [{}]", reqId, todayStr,startDateStr);
				Expression<java.sql.Date> txnDateAsDate =
					    cb.function("to_Date", java.sql.Date.class,
					        rootBk.get("transactionDate"),
					        cb.literal("YYYY-MM-DD"));	
				Predicate betweenDates = cb.between(txnDateAsDate, java.sql.Date.valueOf(startDateStr), java.sql.Date.valueOf(todayStr));
				predicates.add(betweenDates);
			}
			LOGGER.info("REQID : [{}] - columnName is : [{}]", reqId, columnName);
			if (predicates != null && StringUtils.isNotBlank(columnName) && columnName.equalsIgnoreCase("amount")) {
				cq.where(cb.and(predicates.toArray(new Predicate[0])));
				cq.multiselect(cb.count(rootBk), cb.sum(rootBk.get("amount")));
				Object[] result = entityManager.createQuery(cq).getSingleResult();
				if (result != null && result.length > 1) {
					BigDecimal value=  (BigDecimal) result[1];
					retnVal=value;
					LOGGER.info("REQID : [{}] - retnVal : [{}]", reqId, retnVal);
				} else {
					retnVal = new BigDecimal(0);
					LOGGER.info("REQID : [{}] - result object is NUll, so retnVal : [{}]", reqId, retnVal);
				}
			}
		} catch (Exception e) {
			retnVal = new BigDecimal(0);
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

}
