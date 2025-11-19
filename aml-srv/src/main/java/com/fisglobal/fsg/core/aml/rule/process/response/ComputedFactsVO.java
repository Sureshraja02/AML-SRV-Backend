package com.fisglobal.fsg.core.aml.rule.process.response;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author : E5554365 (Prabakaran.R)
 * @Project : aml-srv
 * @year : 2025
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComputedFactsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("fact")
	private String fact;

	@JsonProperty("value")
	private BigDecimal value;

	@JsonProperty("fact")
	public String getFact() {
		return fact;
	}

	@JsonProperty("fact")
	public void setFact(String fact) {
		this.fact = fact;
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
