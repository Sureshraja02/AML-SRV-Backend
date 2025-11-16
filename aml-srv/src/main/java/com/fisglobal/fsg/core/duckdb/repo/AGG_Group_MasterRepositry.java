package com.fisglobal.fsg.core.duckdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fisglobal.fsg.core.duckdb.entity.AGG_Group_MasterRepo;

import jakarta.data.repository.Repository;

@Repository
public interface AGG_Group_MasterRepositry<T> extends JpaRepository<AGG_Group_MasterRepo, String> {

}