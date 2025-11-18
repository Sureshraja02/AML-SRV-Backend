package com.fisglobal.fsg.core.aml.rule.process.response;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RuleResposeDetailsVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("reqId")
	private String reqId;
	
	@JsonProperty("ruleId")
	private String ruleId;
	
	@JsonProperty("accountHolderType")
	private String accountHolderType;
	
	@JsonProperty("accountStatus")
	private String accountStatus;
	
	@JsonProperty("value")
	private BigDecimal value;

	@JsonProperty("reqId")
	public String getReqId() {
		return reqId;
	}

	@JsonProperty("reqId")
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	@JsonProperty("ruleId")
	public String getRuleId() {
		return ruleId;
	}

	@JsonProperty("ruleId")
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	@JsonProperty("accountHolderType")
	public String getAccountHolderType() {
		return accountHolderType;
	}

	@JsonProperty("accountHolderType")
	public void setAccountHolderType(String accountHolderType) {
		this.accountHolderType = accountHolderType;
	}

	@JsonProperty("accountStatus")
	public String getAccountStatus() {
		return accountStatus;
	}

	@JsonProperty("accountStatus")
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@JsonProperty("value")
	public BigDecimal getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	
}
