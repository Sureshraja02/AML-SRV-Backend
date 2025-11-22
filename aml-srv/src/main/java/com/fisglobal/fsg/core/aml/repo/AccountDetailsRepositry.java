package com.fisglobal.fsg.core.aml.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fisglobal.fsg.core.aml.entity.AccountDetailsEntity;

import jakarta.data.repository.Repository;

@Repository
public interface AccountDetailsRepositry<T> extends JpaRepository<AccountDetailsEntity, Long>  {

}