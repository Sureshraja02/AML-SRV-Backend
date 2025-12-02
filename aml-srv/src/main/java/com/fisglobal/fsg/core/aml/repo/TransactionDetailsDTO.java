package com.fisglobal.fsg.core.aml.repo;

import java.math.BigDecimal;

public class TransactionDetailsDTO {

	public BigDecimal sumAmount = null;
	
	public BigDecimal minAmount = null;
	
	public BigDecimal maxAmount = null;
	
	public Long countAmount = null;
	
	public Double avgAmount=null;
	
	public String counterContryCode=null;

	public BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Long getCountAmount() {
		return countAmount;
	}

	public void setCountAmount(Long countAmount) {
		this.countAmount = countAmount;
	}

	public Double getAvgAmount() {
		return avgAmount;
	}

	public void setAvgAmount(Double avgAmount) {
		this.avgAmount = avgAmount;
	}

	public String getCounterContryCode() {
		return counterContryCode;
	}

	public void setCounterContryCode(String counterContryCode) {
		this.counterContryCode = counterContryCode;
	}
	
	
	
}
