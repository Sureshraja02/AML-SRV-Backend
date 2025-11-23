package com.fisglobal.fsg.core.aml.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fisglobal.fsg.core.aml.entity.FS_FIUIndCriminalListEntity;

import jakarta.data.repository.Repository;

@Repository
public interface FS_FIUIndCriminalListRepositry<T> extends JpaRepository<FS_FIUIndCriminalListEntity, Integer> {

}
