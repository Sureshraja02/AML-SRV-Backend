package com.fisglobal.fsg.core.duckdb.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FINSEC_FUNCTIONS")
public class AGG_Group_MasterRepo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "FUNC_ID", nullable = true)
	private String funcId;
	
	@Column(name = "FUNC_NAME", nullable = true)
	private String funcName;
	
	@Column(name = "DESCP", nullable = true)
	private String desc;
}
