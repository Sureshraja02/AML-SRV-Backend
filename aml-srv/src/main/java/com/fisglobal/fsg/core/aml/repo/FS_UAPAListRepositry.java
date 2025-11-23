package com.fisglobal.fsg.core.aml.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fisglobal.fsg.core.aml.entity.FS_UAPAListEntity;

import jakarta.data.repository.Repository;

@Repository
public interface FS_UAPAListRepositry<T> extends JpaRepository<FS_UAPAListEntity, Integer> {

}
