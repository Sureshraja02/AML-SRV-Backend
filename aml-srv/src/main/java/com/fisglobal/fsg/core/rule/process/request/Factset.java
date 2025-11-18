package com.fisglobal.fsg.core.rule.process.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Factset implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("days")
	private Integer days;
	
	@JsonProperty("months")
	private Integer months;
	
	@JsonProperty("fact")
	private String fact;
	
	@JsonProperty("field")
	private String field;

	@JsonProperty("days")
	public Integer getDays() {
		return days;
	}

	@JsonProperty("days")
	public void setDays(Integer days) {
		this.days = days;
	}

	@JsonProperty("months")
	public Integer getMonths() {
		return months;
	}

	@JsonProperty("months")
	public void setMonths(Integer months) {
		this.months = months;
	}

	@JsonProperty("fact")
	public String getFact() {
		return fact;
	}

	@JsonProperty("fact")
	public void setFact(String fact) {
		this.fact = fact;
	}

	@JsonProperty("field")
	public String getField() {
		return field;
	}

	@JsonProperty("field")
	public void setField(String field) {
		this.field = field;
	}
	
	
	
}
