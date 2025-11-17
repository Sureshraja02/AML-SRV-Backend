package com.fisglobal.fsg.core.alldb.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.utils.CommonUtils;

@Component
public class CSVDirectImportPostgresqlService {

	public static final Logger Logger = LoggerFactory.getLogger(CSVDirectImportPostgresqlService.class);
	
	@Autowired
	CommonUtils commonUtils;
	
	@Value("${csv.destination.folder}")
	private String DESTINATION_CSV_FOLDER;
	
	public Connection getDBConn() {
		
		DriverManagerDataSource dataSource = null;
		Connection connectionObj = null;
		try {
			String url = "jdbc:postgresql://localhost:5432/AMLDB";
			String user = "amluser";
			String password = "amluser";
			dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("org.postgresql.Driver");
			dataSource.setUsername(user);
			dataSource.setPassword(password);
			dataSource.setUrl(url);
			connectionObj = dataSource.getConnection();
		} catch (Exception e) {
			connectionObj = null;
		}

		return connectionObj;
	}
	
	public void closeConn(Connection con) throws SQLException {
		if(con!=null) {
			con.close(); con=null;
		}
	}

	public void toImportCsv(String tableName, String csvFilePath) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String csvFileName = null;
		try {
			Long startDate = new Date().getTime();
			Logger.info("[POSTGRESQL] CSV Import Start Time : [{}]", startDate);
			String currentDateNmFldr = new SimpleDateFormat("yyyyMMdd").format(new Date());
			
			Logger.info("[POSTGRESQL] CSV File Path csvFilePath : {}",csvFilePath);
			Path csvFilePathObj = Paths.get(csvFilePath);
			
			csvFileName = csvFilePathObj.getFileName().toString();
			Logger.info("[POSTGRESQL] CSV file Name is : [{}]",csvFileName);
			
			tableName = csvFileName.replace(".csv", "");
			tableName = tableName.replaceAll(" ", "_");
			Logger.info("[POSTGRESQL] Table Name is : [{}]",tableName);
			
			tableName = commonUtils.toSpltFileNameNDGetTableName(csvFileName);
			Logger.info("[POSTGRESQL] Final - Table Name is : [{}]", tableName);
			
			conn = getDBConn();
			
			stmt = conn.createStatement();
        	// Check if table exists
            String checkSql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = '" + tableName + "'";
            rs = stmt.executeQuery(checkSql);
            rs.next();
			boolean tableExists = rs.getInt(1) > 0;
			Logger.info("[POSTGRESQL] Table isexists : {}", tableExists);
            if(tableExists) {
            	String insertSql = "INSERT INTO " + tableName + " SELECT * FROM read_csv_auto('" + csvFilePathObj.toString() + "')";
                stmt.execute(insertSql);
            } else {
            	String createQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " AS SELECT * FROM read_csv_auto('" + csvFilePathObj.toString() + "')";
				stmt.execute(createQuery);
            }
			
			if(rs!=null) {
				rs.close(); rs = null;
			}
			if (stmt != null) {
				stmt.close(); stmt = null;
			}
			closeConn(conn);
			
			Logger.info("::::::::::::[POSTGRESQL] CSV imported successfully.\n\n");
			
			// To move into current data folder
			//Path toPath = Paths.get(DESTINATION_CSV_FOLDER +"/"+ currentDateNmFldr+"/", csvFileName);
			Path toPath = Paths.get(DESTINATION_CSV_FOLDER +"/"+ currentDateNmFldr+"/");
			Logger.info("Before Create destination folder: {}", toPath);
			if (!Files.exists(toPath)) {
				Files.createDirectories(toPath);
				Logger.info("After Created destination folder: {}", toPath);
			}
			toPath = Paths.get(DESTINATION_CSV_FOLDER +"/"+ currentDateNmFldr+"/",csvFileName);
			Logger.info("[POSTGRESQL] Completed from file path : {}", csvFilePathObj);
			Logger.info("[POSTGRESQL] Completed to file path : {}", toPath);
			commonUtils.toMove(csvFilePathObj, toPath);
		
		} catch(Exception e) {
			Logger.error("Exception found in CSVDirectImportPostgresqlService@toImportCsv : {}", e);
		} finally {
			
		}
	}
}
