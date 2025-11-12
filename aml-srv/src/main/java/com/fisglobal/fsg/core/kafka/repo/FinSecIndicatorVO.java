package com.fisglobal.fsg.core.kafka.repo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class FinSecIndicatorVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uuid;
	private String csv2DuckDbImprtIsReady; // DuckDB Import
	private String customerProfileIsReady; // Customer Profiling status

	private String productTblIsReady; // Product Table
	private String asaTblIsReady; // Account Status Table
	private String crmTblIsReady;// Currency Mgmt Table
	private String insturmentTblIsReady;// Instrument Table
	private String nomineeTblIsReady;// Nominee Table
	private String countryTblIsReady;// Country Table
	private String transTypeTblIsReady;// Transaction Type Table
	private String minAccBalanceTblIsReady;// Minaccountbalance Table
	private String accountTblIsReady; // Account Table
	private String accCustTblIsReady;// AccCust table
	private String branchTblIsReady; // Branch Table
	private String eodTblIsReady; // EOD Table
	private String chequeDtlTblIsReady; // Cheque Details Table
	private String nctTransTblIsReady;// NGO or NGO transaction table
	private String cstTblIsReady; // CST or Customer Details
	private String ncbTblIsReady;// NCB Customer transaction table
	private String mcdTblIsReady;// Guardin Details Table
	private String joinHolderTblIsReady; // Join Holder Table
	private String tradeAccTransTblIsReady;// TBM - Trade Account Transaction Table
	private String transactionTblIsReady; // TRN - Transaction
	private String lockerTblsIsReady; // LCK Table

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCsv2DuckDbImprtIsReady() {
		return csv2DuckDbImprtIsReady;
	}

	public void setCsv2DuckDbImprtIsReady(String csv2DuckDbImprtIsReady) {
		this.csv2DuckDbImprtIsReady = csv2DuckDbImprtIsReady;
	}

	public String getCustomerProfileIsReady() {
		return customerProfileIsReady;
	}

	public void setCustomerProfileIsReady(String customerProfileIsReady) {
		this.customerProfileIsReady = customerProfileIsReady;
	}

	public String getProductTblIsReady() {
		return productTblIsReady;
	}

	public void setProductTblIsReady(String productTblIsReady) {
		this.productTblIsReady = productTblIsReady;
	}

	public String getAsaTblIsReady() {
		return asaTblIsReady;
	}

	public void setAsaTblIsReady(String asaTblIsReady) {
		this.asaTblIsReady = asaTblIsReady;
	}

	public String getCrmTblIsReady() {
		return crmTblIsReady;
	}

	public void setCrmTblIsReady(String crmTblIsReady) {
		this.crmTblIsReady = crmTblIsReady;
	}

	public String getInsturmentTblIsReady() {
		return insturmentTblIsReady;
	}

	public void setInsturmentTblIsReady(String insturmentTblIsReady) {
		this.insturmentTblIsReady = insturmentTblIsReady;
	}

	public String getNomineeTblIsReady() {
		return nomineeTblIsReady;
	}

	public void setNomineeTblIsReady(String nomineeTblIsReady) {
		this.nomineeTblIsReady = nomineeTblIsReady;
	}

	public String getCountryTblIsReady() {
		return countryTblIsReady;
	}

	public void setCountryTblIsReady(String countryTblIsReady) {
		this.countryTblIsReady = countryTblIsReady;
	}

	public String getTransTypeTblIsReady() {
		return transTypeTblIsReady;
	}

	public void setTransTypeTblIsReady(String transTypeTblIsReady) {
		this.transTypeTblIsReady = transTypeTblIsReady;
	}

	public String getMinAccBalanceTblIsReady() {
		return minAccBalanceTblIsReady;
	}

	public void setMinAccBalanceTblIsReady(String minAccBalanceTblIsReady) {
		this.minAccBalanceTblIsReady = minAccBalanceTblIsReady;
	}

	public String getAccountTblIsReady() {
		return accountTblIsReady;
	}

	public void setAccountTblIsReady(String accountTblIsReady) {
		this.accountTblIsReady = accountTblIsReady;
	}

	public String getAccCustTblIsReady() {
		return accCustTblIsReady;
	}

	public void setAccCustTblIsReady(String accCustTblIsReady) {
		this.accCustTblIsReady = accCustTblIsReady;
	}

	public String getBranchTblIsReady() {
		return branchTblIsReady;
	}

	public void setBranchTblIsReady(String branchTblIsReady) {
		this.branchTblIsReady = branchTblIsReady;
	}

	public String getEodTblIsReady() {
		return eodTblIsReady;
	}

	public void setEodTblIsReady(String eodTblIsReady) {
		this.eodTblIsReady = eodTblIsReady;
	}

	public String getChequeDtlTblIsReady() {
		return chequeDtlTblIsReady;
	}

	public void setChequeDtlTblIsReady(String chequeDtlTblIsReady) {
		this.chequeDtlTblIsReady = chequeDtlTblIsReady;
	}

	public String getNctTransTblIsReady() {
		return nctTransTblIsReady;
	}

	public void setNctTransTblIsReady(String nctTransTblIsReady) {
		this.nctTransTblIsReady = nctTransTblIsReady;
	}

	public String getCstTblIsReady() {
		return cstTblIsReady;
	}

	public void setCstTblIsReady(String cstTblIsReady) {
		this.cstTblIsReady = cstTblIsReady;
	}

	public String getNcbTblIsReady() {
		return ncbTblIsReady;
	}

	public void setNcbTblIsReady(String ncbTblIsReady) {
		this.ncbTblIsReady = ncbTblIsReady;
	}

	public String getMcdTblIsReady() {
		return mcdTblIsReady;
	}

	public void setMcdTblIsReady(String mcdTblIsReady) {
		this.mcdTblIsReady = mcdTblIsReady;
	}

	public String getJoinHolderTblIsReady() {
		return joinHolderTblIsReady;
	}

	public void setJoinHolderTblIsReady(String joinHolderTblIsReady) {
		this.joinHolderTblIsReady = joinHolderTblIsReady;
	}

	public String getTradeAccTransTblIsReady() {
		return tradeAccTransTblIsReady;
	}

	public void setTradeAccTransTblIsReady(String tradeAccTransTblIsReady) {
		this.tradeAccTransTblIsReady = tradeAccTransTblIsReady;
	}

	public String getTransactionTblIsReady() {
		return transactionTblIsReady;
	}

	public void setTransactionTblIsReady(String transactionTblIsReady) {
		this.transactionTblIsReady = transactionTblIsReady;
	}

	public String getLockerTblsIsReady() {
		return lockerTblsIsReady;
	}

	public void setLockerTblsIsReady(String lockerTblsIsReady) {
		this.lockerTblsIsReady = lockerTblsIsReady;
	}

}