package com.fisglobal.fsg.core.duckdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fisglobal.fsg.core.duckdb.entity.AMLDataRepo;
import jakarta.data.repository.Repository;

@Repository
public interface AMLDataReposity<T> extends JpaRepository<AMLDataRepo, Integer> {

}
