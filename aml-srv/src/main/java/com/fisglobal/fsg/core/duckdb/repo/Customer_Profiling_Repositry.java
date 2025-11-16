package com.fisglobal.fsg.core.duckdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fisglobal.fsg.core.duckdb.entity.Customer_ProfilingRepo;

import jakarta.data.repository.Repository;

@Repository
public interface Customer_Profiling_Repositry  extends JpaRepository<Customer_ProfilingRepo, String>{

}