package com.fisglobal.fsg.core.aml.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fisglobal.fsg.core.aml.entity.FS_FIUIndSwiftConsltPurposeEntity;

import jakarta.data.repository.Repository;

@Repository
public interface FS_FIUIndSwiftConsltPurposeRepositry<T> extends JpaRepository<FS_FIUIndSwiftConsltPurposeEntity, Integer> {

}
