package com.fisglobal.fsg.core.aml.repo;

import java.time.LocalDate;
import java.util.ArrayList;
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
	public Integer getSumValue(String reqId, String accNo, String custId, String transMode, String transType, Integer hours, Integer days, Integer months,  String fieldName, String columnName) {
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
			LOGGER.info("REQID : [{}] - transType : [{}]", reqId, transType);
			if (StringUtils.isNotBlank(transType)) {
				predicates.add(cb.equal(rootBk.get("depositorWithdrawal"), transType));
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
