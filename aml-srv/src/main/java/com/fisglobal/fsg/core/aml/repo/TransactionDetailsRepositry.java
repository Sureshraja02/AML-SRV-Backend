package com.fisglobal.fsg.core.aml.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fisglobal.fsg.core.aml.entity.TransactionDetailsEntity;

@Repository
public interface TransactionDetailsRepositry  extends JpaRepository<TransactionDetailsEntity, String>{

}