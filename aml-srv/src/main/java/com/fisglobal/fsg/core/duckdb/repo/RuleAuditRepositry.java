package com.fisglobal.fsg.core.duckdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fisglobal.fsg.core.duckdb.entity.RuleAuditEntity;

import jakarta.data.repository.Repository;

@Repository
public interface RuleAuditRepositry<T> extends JpaRepository<RuleAuditEntity, String>  {

}