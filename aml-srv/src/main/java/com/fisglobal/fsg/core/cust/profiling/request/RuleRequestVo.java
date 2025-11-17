package com.fisglobal.fsg.core.cust.profiling.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RuleRequestVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("reqId")
	private String reqId;
	
	@JsonProperty("customerId")
	private String customerId;
	
	@JsonProperty("ruleId")
	private String ruleId;
	
	@JsonProperty("accountNo")
	private String accountNo;
	
	@JsonProperty("days")
	private String days;
	
	@JsonProperty("hours")
	private String hours;
	
	@JsonProperty("ruleId")
	private String fact;
	
	@JsonProperty("transactionMode")
	private String transactionMode;
	
	@JsonProperty("txnType")
	private String txnType;
	
	@JsonProperty("accountStatus")
	private String accountStatus;
	
	@JsonProperty("accountHolderType")
	private String accountHolderType;
	
	@JsonProperty("reqTime")
	private String reqTime;

	@JsonProperty("reqId")
	public String getReqId() {
		return reqId;
	}

	@JsonProperty("reqId")
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	@JsonProperty("customerId")
	public String getCustomerId() {
		return customerId;
	}

	@JsonProperty("customerId")
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@JsonProperty("ruleId")
	public String getRuleId() {
		return ruleId;
	}

	@JsonProperty("ruleId")
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	@JsonProperty("accountNo")
	public String getAccountNo() {
		return accountNo;
	}

	@JsonProperty("accountNo")
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@JsonProperty("days")
	public String getDays() {
		return days;
	}

	@JsonProperty("days")
	public void setDays(String days) {
		this.days = days;
	}

	@JsonProperty("hours")
	public String getHours() {
		return hours;
	}

	@JsonProperty("hours")
	public void setHours(String hours) {
		this.hours = hours;
	}

	@JsonProperty("fact")
	public String getFact() {
		return fact;
	}

	@JsonProperty("fact")
	public void setFact(String fact) {
		this.fact = fact;
	}

	@JsonProperty("transactionMode")
	public String getTransactionMode() {
		return transactionMode;
	}

	@JsonProperty("transactionMode")
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	@JsonProperty("txnType")
	public String getTxnType() {
		return txnType;
	}

	@JsonProperty("txnType")
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	@JsonProperty("accountStatus")
	public String getAccountStatus() {
		return accountStatus;
	}

	@JsonProperty("accountStatus")
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@JsonProperty("accountHolderType")
	public String getAccountHolderType() {
		return accountHolderType;
	}

	@JsonProperty("accountHolderType")
	public void setAccountHolderType(String accountHolderType) {
		this.accountHolderType = accountHolderType;
	}
	
	@JsonProperty("reqTime")
	public String getReqTime() {
		return reqTime;
	}

	@JsonProperty("reqTime")
	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}
}