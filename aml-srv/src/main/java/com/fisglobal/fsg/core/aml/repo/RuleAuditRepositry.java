package com.fisglobal.fsg.core.aml.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fisglobal.fsg.core.aml.entity.RuleAuditReqEntity;

import jakarta.data.repository.Repository;

@Repository
public interface RuleAuditRepositry<T> extends JpaRepository<RuleAuditReqEntity, String>  {

}