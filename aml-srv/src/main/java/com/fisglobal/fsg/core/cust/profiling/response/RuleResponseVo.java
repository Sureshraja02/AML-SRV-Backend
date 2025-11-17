package com.fisglobal.fsg.core.cust.profiling.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RuleResponseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("reqId")
	private String reqId;
	
	@JsonProperty("ruleId")
	private String ruleId;
	
	@JsonProperty("cumulativeValue")
	private String cumulativeValue;

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

	@JsonProperty("cumulativeValue")
	public String getCumulativeValue() {
		return cumulativeValue;
	}

	@JsonProperty("cumulativeValue")
	public void setCumulativeValue(String cumulativeValue) {
		this.cumulativeValue = cumulativeValue;
	}

}
