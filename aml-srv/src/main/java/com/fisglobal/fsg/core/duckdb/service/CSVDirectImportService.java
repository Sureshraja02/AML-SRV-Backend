package com.fisglobal.fsg.core.duckdb.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.utils.CommonUtils;

@Component
public class CSVDirectImportService {

	public static final Logger Logger = LoggerFactory.getLogger(CSVDirectImportService.class);
		
	//@Value("${aml.data.table.name}")
	private String tableName = null;

	@Value("${duckdb.file.path.url}")//duckdb.file.path.url
	private String jdbcURLDtl;

	@Value("${final.duckdb.file.path}")
	private String finalDuckDBPath;
	
	@Value("${csv.destination.folder}")
	private String DESTINATION_CSV_FOLDER;
	
	@Autowired
	CommonUtils commonUtils;
	
	// @PostConstruct
	public void importCsv(String csvFilePath) throws SQLException {// (id INTEGER, name VARCHAR, email VARCHAR)
		Logger.info(":::::::::::::::::::Import Csv Started:::::::::::::::::");
		Long startDate = new Date().getTime();
		Logger.info("CSV Import Start Time : [{}]", startDate);
		String currentDateNmFldr =new SimpleDateFormat("yyyyMMdd").format(new Date());
		// String jdbcUrl = "jdbc:duckdb:";
		// Adjust path as needed //String csvPath = "src/main/resources/data.csv";
		//String csvPath = path + csvFileName;
		String finalDUckdbfldr = finalDuckDBPath + currentDateNmFldr + "/";
		String finalDbConnURL = null;
		
		Connection conn = null;
		Statement stmt = null;
		String csvFileName = null;
		ResultSet rs = null;
		try {
			Logger.info("CSV File Path csvFilePath : {}",csvFilePath);
			Path csvFilePathObj = Paths.get(csvFilePath);
			
			csvFileName = csvFilePathObj.getFileName().toString();
			Logger.info("CSV file Name is : [{}]",csvFileName);
			
			tableName = csvFileName.replace(".csv", "");
			Logger.info("Table Name is : [{}]",tableName);
			
			Logger.info("Final Duck DB FOLDER Path : {}",finalDUckdbfldr);
			//finalDbConnURL = jdbcURLDtl+finalDUckdbfldr + currentDateNmFldr + "_" + csvFileName.replace(".csv", ".db");
			finalDbConnURL = jdbcURLDtl + finalDUckdbfldr + currentDateNmFldr + "_aml.db";
			Logger.info("Final Duck DB COnnection URL : {}", finalDbConnURL);
			
			Path duckDBFinalDir = Paths.get(finalDUckdbfldr);
			if (!Files.exists(duckDBFinalDir)) {
				Files.createDirectories(duckDBFinalDir);
				Logger.info("Created Final Duck DB folder: {}", duckDBFinalDir);
			}
			
			Class.forName("org.duckdb.DuckDBDriver");
			conn = DriverManager.getConnection(finalDbConnURL);
	        if(conn!=null) {
	        	stmt = conn.createStatement();
	        	// Check if table exists
	            String checkSql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = '" + tableName + "'";
	            rs = stmt.executeQuery(checkSql);
	            rs.next();
				boolean tableExists = rs.getInt(1) > 0;
				Logger.info("Table isexists : {}", tableExists);
	            if(tableExists) {
	            	String insertSql = "INSERT INTO " + tableName + " SELECT * FROM read_csv_auto('" + csvFilePathObj.toString() + "')";
	                stmt.execute(insertSql);
	            } else {
	            	String createQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " AS SELECT * FROM read_csv_auto('" + csvFilePathObj.toString() + "')";
					stmt.execute(createQuery);
	            }
	        	
				// stmt.execute("CREATE TABLE IF NOT EXISTS " + tableName + " (id INTEGER, name VARCHAR, email VARCHAR)");
				// stmt.execute("COPY " + tableName + " FROM '" + csvPath + "' (DELIMITER ',',  HEADER)");
				Logger.info("CSV imported successfully.");
				
				// To move into current data folder
				Path toPath = Paths.get(DESTINATION_CSV_FOLDER + currentDateNmFldr+"/", csvFileName);
				Logger.info("Completed from file path : {}", toPath);
				Logger.info("Completed to file path : {}", toPath);
				commonUtils.toMove(csvFilePathObj, toPath);
				
	        } else {
				Logger.info("Database not connected");
	        }
		} catch (Exception e) {
			Logger.error("Exception found in CsvImportServiceDirectfile@importCsv : {}", e);
		} finally {
			if(rs!=null) {
				rs.close(); rs = null;
			}
			if (stmt != null) {
				stmt.close(); stmt = null;
			}
			if (conn != null) {
				conn.close(); conn = null;
			}
			Long endTime = new Date().getTime();
			Logger.info("Total time : {}", commonUtils.findIsHourMinSec((endTime - startDate)));
			Logger.info(":::::::::::::::::::Import Csv End:::::::::::::::::\n");
		}
	}
}
