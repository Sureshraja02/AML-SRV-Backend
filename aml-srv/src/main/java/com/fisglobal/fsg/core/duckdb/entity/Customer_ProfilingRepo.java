package com.fisglobal.fsg.core.duckdb.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUSTOME_PROFILING")
public class Customer_ProfilingRepo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CUSTOMER_PROF_ID", nullable = false)
	private String customerProfId;
	
	@Column(name = "CUSTOMER_ID", nullable = false)
	private String customerId;
	
	@Column(name = "CUSTOMER_TYPE", nullable = false)
	private String customerType;
	
	@Column(name = "ACCOUNT_NO", nullable = false)
	private String accountNo;
	
	
	@Column(name = "ACCOUNT_TYPE", nullable = false)
	private String accountType;
	
	
	@Column(name = "FUNCATION_ID", nullable = false)
	private String functionId;
	
	@Column(name = "NO_OF_MONTH", nullable = false)
	private String noOfMonth;
	
	@Column(name = "CUMULATIVE_VALUE", nullable = false)
	private String cumulativeValue;
	

	@Column(name = "CREATED_DATE", nullable = false)
	private Timestamp createdDate;
	
	@Column(name = "UPDATED_DATE", nullable = false)
	private Timestamp updatedDate;
	
}
