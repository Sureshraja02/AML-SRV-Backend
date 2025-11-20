package com.fisglobal.fsg.core.aml.rule.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.aml.entity.TransactionDetailsEntity;
import com.fisglobal.fsg.core.aml.rule.process.response.ComputedFactsVO;
import com.fisglobal.fsg.core.aml.utils.AMLConstants;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * 
 * @author : E5554365 (Prabakaran.R)
 * @Project : aml-srv
 * @year : 2025
 */
@Component
public class RulesUtils {

	private Logger LOGGER = LoggerFactory.getLogger(RulesUtils.class);

	@Autowired
	EntityManager entityManager;

	
	public ComputedFactsVO toGetWithdaralFacts(String reqId, String accountNo, String custmerId, BigDecimal depositeLargerAmount, String transDate, String factName, String columnName, String conditianName) {
		LOGGER.info("REQ ID : [{}] - toGetWithdaralFacts Method Called........", reqId);
		ComputedFactsVO computedFactsVO = null;
		PercentageDetailsVO percentageDetailsVOObj = null;
		try {
			computedFactsVO = new ComputedFactsVO();
			LOGGER.info("REQ ID : [{}] - toGetWithdaralFacts conditianName : [{}]........", conditianName);
			percentageDetailsVOObj = toGetValueByImediateWithDraw(reqId, accountNo, custmerId, depositeLargerAmount, transDate, factName, columnName);
			if(StringUtils.isNotBlank(conditianName)) {
				switch (conditianName) {
				case AMLConstants.IMMEDIATE_WITHDRAWAL_DIFFERENT_LOCATIONS:
					//toGetPercentAge(percentageDetailsVOObj);
					computedFactsVO.setFact(factName);
					computedFactsVO.setValue(toGetPercentAge(percentageDetailsVOObj));
					break;
				case AMLConstants.IMMEDIATE_WITHDRAWAL:
					//toGetPercentAge(percentageDetailsVOObj);
					computedFactsVO.setFact(factName);
					computedFactsVO.setValue(toGetPercentAge(percentageDetailsVOObj));
					break;
				case AMLConstants.IMMEDIATE_WITHDRAWAL_ATM_OR_OTHER:
					//toGetPercentAge(percentageDetailsVOObj);
					computedFactsVO.setFact(factName);
					computedFactsVO.setValue(toGetPercentAge(percentageDetailsVOObj));
					break;
				default:
					LOGGER.info("REQ ID : [{}] - toGetWithdaralFacts default block Condition not match");
					computedFactsVO.setFact(factName);
					computedFactsVO.setValue(percentageDetailsVOObj.getTotalValue());
				}
			} else {
				LOGGER.info("REQ ID : [{}] - toGetWithdaralFacts conditation not available.");
				computedFactsVO.setFact(factName);
				computedFactsVO.setValue(percentageDetailsVOObj.getTotalValue());
			}
			
		} catch (Exception e) {
			computedFactsVO = null;
			LOGGER.error("Exception found in toGetWithdaralFacts : {}", e);
			
		} finally {
			LOGGER.info("REQ ID : [{}] - toGetWithdaralFacts Method End........", reqId);
		}
		return computedFactsVO;
	}
	
	/**
	 * 
	 * @param reqId
	 * @param accountNo
	 * @param custmerId
	 * @param depositeLargerAmount
	 * @param transDate
	 * @param factName
	 * @param columnName
	 * @return toGetValueByImediateWithDraw ComputedFactsVO
	 */
	public PercentageDetailsVO toGetValueByImediateWithDraw(String reqId, String accountNo, String custmerId, BigDecimal depositeLargerAmount, String transDate, String factName, String columnName) {
		LOGGER.info("REQ ID : [{}] - toGetValueByImediateWithDraw Method Called............", reqId);
		PercentageDetailsVO percentageDetailsVOObj =null;
		BigDecimal retnVal = null;
		//ComputedFactsVO computedFactsVO = null;
		CriteriaBuilder cb = null;
		List<Predicate> predicates = null;
		CriteriaQuery<Object[]> cq = null;
		Root<TransactionDetailsEntity> rootBk = null;
		try {
			percentageDetailsVOObj = new PercentageDetailsVO();
		//	computedFactsVO = new ComputedFactsVO();
			//computedFactsVO.setFact(factName);

			cb = entityManager.getCriteriaBuilder();
			cq = cb.createQuery(Object[].class);
			predicates = new ArrayList<Predicate>();
			rootBk = cq.from(TransactionDetailsEntity.class);
			if (StringUtils.isNotBlank(accountNo)) {
				predicates.add(cb.equal(rootBk.get("accountNo"), accountNo));
			}
			if (StringUtils.isNotBlank(custmerId)) {
				predicates.add(cb.equal(rootBk.get("customerId"), custmerId));
			}

			LOGGER.debug("REQID : [{}] - Transaction Date : [{}] - Deposite amt : [{}]", reqId, transDate, depositeLargerAmount);
			predicates.add(cb.equal(rootBk.get("depositorWithdrawal"), "W"));
			predicates.add(cb.greaterThanOrEqualTo(rootBk.get("transactionDate"), transDate));
			predicates.add(cb.lessThanOrEqualTo(rootBk.get("amount"), depositeLargerAmount));

			if (predicates != null && StringUtils.isNotBlank(columnName) && columnName.equalsIgnoreCase("amount")) {
				cq.where(cb.and(predicates.toArray(new Predicate[0])));
				cq.multiselect(cb.count(rootBk), cb.max(rootBk.get("amount")));
				Object[] result = entityManager.createQuery(cq).getSingleResult();
				if (result != null && result.length > 1) {
					// BigDecimal value=
					percentageDetailsVOObj.setReqId(reqId);
					percentageDetailsVOObj.setNoOfTimes((BigDecimal) result[0]);
					percentageDetailsVOObj.setTotalValue((BigDecimal) result[1]);
					LOGGER.info("REQID : [{}] - retnVal : [{}]", reqId, retnVal);
				} else {
					percentageDetailsVOObj =null;
					LOGGER.info("REQID : [{}] - result object is NUll, so retnVal : [{}]", reqId, retnVal);
				}
			}

		} catch (Exception e) {
			percentageDetailsVOObj =null;
			LOGGER.error("REQ ID : [{}] - Exception found in RulesUtils@toGetValueByImediateWithDraw : {}", reqId, e);
		} finally {
			cb = null;
			predicates = null;
			cq = null;
			rootBk = null;
		}
		LOGGER.info("REQ ID : [{}] - toGetValueByImediateWithDraw Method End............\n\n", reqId);
		return percentageDetailsVOObj;
	}

	

	/**
	 * 
	 * @param reqId
	 * @param noOfTimes
	 * @param totalValue
	 * @return toGetPercentAge BigDecimal
	 */
	public BigDecimal toGetPercentAge(PercentageDetailsVO percentageDetailsObj) {
		LOGGER.info("REQ ID : [{}] - toGetPercentAge Method Called............", percentageDetailsObj.getReqId());
		BigDecimal perCentageValue = null;
		try {
			perCentageValue = percentageDetailsObj.getNoOfTimes().divide(percentageDetailsObj.getTotalValue(), 10, RoundingMode.HALF_UP) // scale 10 for precision
					.multiply(new BigDecimal("100"));

		} catch (Exception e) {
			LOGGER.error("REQ ID : [{}] - Exception found in RulesUtils@toGetPercentAge : {}", percentageDetailsObj.getReqId(), e);
		} finally {
			LOGGER.info("REQ ID : [{}] - toGetPercentAge Method End............\n\n", percentageDetailsObj.getReqId());
		}
		return perCentageValue;
	}

}
