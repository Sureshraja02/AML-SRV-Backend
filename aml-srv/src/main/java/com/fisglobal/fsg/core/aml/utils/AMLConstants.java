package com.fisglobal.fsg.core.aml.utils;

import org.springframework.stereotype.Component;

/**
 * 
 * @author : E5554365 (Prabakaran.R)
 * @Project : aml-srv
 * @year : 2025
 */
@Component
public class AMLConstants {

	public static final String CSV_FORMAT = ".csv";
	public static final String FLT_FORMAT = ".flt";

	public static final String KAFKA_PUB_TOPIC = "TRASN-PUB";
	public static final String KAFAK_RETURN_PUB_TOPIC = "TRANS-RTN-PUB";

	public static final String YES = "YES";

	public static final String SUM = "SUM";
	public static final String COUNT = "COUNT";
	public static final String MAX = "MAX";
	public static final String AVG = "AVG";
	public static final String TOTAL = "TOTAL";
	public static final String PREVIOUS_FOREX_TURNOVER = "PREVIOUS_FOREX_TURNOVER";

	public static final String FD_CONVERSION = "FD_CONVERSION";
	public static final String LARGE_DEPOSIT = "LARGE_DEPOSIT";
	public static final String IMMEDIATE_WITHDRAWAL = "IMMEDIATE_WITHDRAWAL";

	public static final String COUNTRY_RISK = "COUNTRY_RISK";
	public static final String CUSTOMER_MATCH = "CUSTOMER_MATCH";
	public static final String FCRA_COMPLIANCE = "FCRA_COMPLIANCE";
	public static final String PAN_STATUS = "PAN_STATUS";
	public static final String ACCOUNT_STATUS = "ACCOUNT_STATUS";
	public static final String BENEFICIARY_RELATION = "BENEFICIARY_RELATION";
}
