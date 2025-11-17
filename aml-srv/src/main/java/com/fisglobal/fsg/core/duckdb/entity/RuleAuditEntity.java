package com.fisglobal.fsg.core.duckdb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FINSEC_RULE_AUDIT")
public class RuleAuditEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "AUDIT_ID", nullable = true)
	private String auditId;
	
	@Column(name = "RULE_ID", nullable = true)
	private String ruleId;
	
	@Column(name = "REQ_ID", nullable = true)
	private String reqId;
	
	@Column(name = "CUSTOMER_ID", nullable = true)
	private String customerId;
	
	@Column(name = "ACCOUNT_NO", nullable = true)
	private String accountNo;
	
	@Column(name = "NO_DAYS", nullable = true)
	private String days;
	
	@Column(name = "NO_HOURS", nullable = true)
	private String hours;
	
	@Column(name = "FACT", nullable = true)
	private String fact;
	
	@Column(name = "TRANSACTION_MODE", nullable = true)
	private String transactionMode;
	
	@Column(name = "TNX_TYPE", nullable = true)
	private String tnxType;
	
	@Column(name = "ACCOUNT_STATUS", nullable = true)
	private String accountStatus;
	
	@Column(name = "ACCOUNT_HOLDER_TYPE", nullable = true)
	private String accountHolderType;

	@Column(name = "TRANSACTION_TIME", nullable = true)
	private LocalTime transactionTime;
	
	//// Example time to store
    //LocalTime localTime = LocalTime.of(14, 30, 45); ---14:30:45
    
	@Column(name = "REQ_DATE_TIME", nullable = true)
	private Timestamp reqDateTime;
	
	@Column(name = "CUMULATIVE_VALUE", nullable = true)
	private String cumulativeValue;

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getFact() {
		return fact;
	}

	public void setFact(String fact) {
		this.fact = fact;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public String getTnxType() {
		return tnxType;
	}

	public void setTnxType(String tnxType) {
		this.tnxType = tnxType;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAccountHolderType() {
		return accountHolderType;
	}

	public void setAccountHolderType(String accountHolderType) {
		this.accountHolderType = accountHolderType;
	}

	public LocalTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	public Timestamp getReqDateTime() {
		return reqDateTime;
	}

	public void setReqDateTime(Timestamp reqDateTime) {
		this.reqDateTime = reqDateTime;
	}

	public String getCumulativeValue() {
		return cumulativeValue;
	}

	public void setCumulativeValue(String cumulativeValue) {
		this.cumulativeValue = cumulativeValue;
	}
	
}
