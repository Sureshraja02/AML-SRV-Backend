package com.fisglobal.fsg.core.duckdb.repo;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisglobal.fsg.core.duckdb.entity.AMLDataRepo;

@Service
public class DuckDBimpl {

	Logger logger = org.slf4j.LoggerFactory.getLogger(DuckDBimpl.class);

	@Autowired
	AMLDataReposity<?> amlDataReposity;

	public void saveData(int id, String name, String email) {
		AMLDataRepo amlDataRepo = null;
		try {
			amlDataRepo = new AMLDataRepo();
			amlDataRepo.setEmail(email);
			amlDataRepo.setName(name);
			amlDataRepo.setId(id);
			amlDataReposity.save(amlDataRepo);
		} catch (Exception e) {
			logger.info("Exception found in DuckDBimpl@saveData", e);
		} finally {

		}
	}
}
