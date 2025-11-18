package com.fisglobal.fsg.core.aml.utils;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.aml.config.StartupConfig;

/**
 * 
 * @author : E5554365 (Prabakaran.R)
 * @Project : aml-srv
 * @year : 2025
 */
@Component
public class CommonUtils {

	private static final Logger Logger = LoggerFactory.getLogger(CommonUtils.class);
	
	@Value("${aml.files.prefix.and.table.name}")
	private String amlFilePrifixTblName;
	
	public String findIsHourMinSec(Long timeinMiliSend) {
		String rtnValue = "";
		try {
			long totalSeconds = timeinMiliSend / 1000;
			long hours = totalSeconds / 3600;
			long minutes = (totalSeconds % 3600) / 60;
			long seconds = totalSeconds % 60;
			long remainingMillis = timeinMiliSend % 1000;

			if (hours > 0) {
				rtnValue += hours + " hs ";
			}
			if (minutes > 0) {
				rtnValue += minutes + " min ";
			}
			if (seconds > 0) {
				rtnValue += seconds + " s ";
			}

			if (remainingMillis > 0) {
				rtnValue += remainingMillis + " ms ";
			}
		} catch (Exception e) {

		}
		return rtnValue;
	}
	
	public void toMove(Path fromPath, Path toPath) {
		try {
			Path destinationDir = Paths.get(toPath.toString());
			if (!Files.exists(destinationDir)) {
				Files.createDirectories(destinationDir);
				Logger.info("Processed / imported folder : {}", destinationDir);
			}
			if (fromPath!=null && Files.exists(fromPath)) {
				Logger.info("File moved to: [{}]", toPath.toString());
				Logger.info("isFileReady : [{}]",isFileReady(fromPath));
				while (!isFileReady(fromPath)) {
				    Thread.sleep(5000); // Wait until file is no longer locked
				}
				try {
					Files.move(fromPath, toPath, StandardCopyOption.REPLACE_EXISTING);
				} catch(Exception e) {
					Logger.info("File still reading / blocking some other process.......So MoveFIleSafely method.");
					moveFileSafely(fromPath,toPath);
				}
			}
			
		} catch (Exception e) {
			Logger.error("Exception found in CommonUtils@toMove : {}", e);
		} finally { }
	}
	
	public boolean isFileReady(Path path) {
	    try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
	        return true;
	    } catch (IOException e) {
	        return false;
	    }
	}
	
	public static boolean moveFileSafely(Path source, Path target) {
	    int retries = 10;
	    while (retries-- > 0) {
	        if (isFileStable(source, 2, 1000)) {
	            try {
	                Files.createDirectories(target.getParent());
	                Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
	                return true;
	            } catch (FileSystemException e) {
	                System.err.println("File locked, retrying: " + e.getMessage());
	            } catch (IOException e) {
	                e.printStackTrace();
	                return false;
	            }
	        } else {
	            System.out.println("Waiting for file to stabilize: " + source);
	        }
	        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
	    }
	    System.err.println("Failed to move file after retries: " + source);
	    return false;
	}

	public static boolean isFileStable(Path path, int checks, long intervalMillis) {
	    try {
	        long previousSize = -1;
	        for (int i = 0; i < checks; i++) {
	            long currentSize = Files.size(path);
	            if (currentSize == previousSize) return true;
	            previousSize = currentSize;
	            Thread.sleep(intervalMillis);
	        }
	    } catch (IOException | InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
	    return false;
	}

	public void toDelete(String filePath) {
		try {
			Path path = Paths.get(filePath);
			if (path!=null && Files.exists(path)) {
				Files.delete(path);
				Logger.info("File deleted - toDelete : [{}]", filePath);
			} else {
				Logger.info("File not found - toDelete : [{}]", filePath);
			}
		} catch (Exception e) {
			Logger.error("Exception found in CommonUtils@toDelete : {}", e);
		} finally { }
	}
	
	public void toLoadMapFromPro() {
		Logger.info(":::::::::::::::::::::::toLoadMapFromPro method called.::::::::::::::::::::::");
		ConcurrentHashMap<String, String> amlTableNameMap = null;
		try {
			amlTableNameMap = new ConcurrentHashMap<String, String>();
			if (StringUtils.isNotBlank(amlFilePrifixTblName)) {
				// counterMap = new ConcurrentHashMap<String, String>();
				List<String> amlFileTblNamelstObj = new ArrayList<>(Arrays.asList(amlFilePrifixTblName.split(",")));
				for (String tableNameDtl : amlFileTblNamelstObj) {
					String amtblnamery[] = tableNameDtl.split("\\~");
					if (amtblnamery != null && amtblnamery.length == 2) {
						amlTableNameMap.put(amtblnamery[0], amtblnamery[1]);
					}
				}
			} else {
				amlTableNameMap = null;
			}
			StartupConfig.amlTableNameMap = amlTableNameMap;
		} catch (Exception e) {
			Logger.error("Exception found in CommonUtils@toLoadMapFromPro : {}", e);
		} finally {
			Logger.info(":::::::::::::::::::::::toLoadMapFromPro method End.::::::::::::::::::::::\n");
		}

	}
	
	/**
	 * 
	 * @param tblPrefix
	 * @return
	 * getAMLTableName
	 * String
	 * get AML Table name from map
	 */
	public String getAMLTableName(String tblPrefix) {
		String amlTblName = null;
		try {
			if (StartupConfig.amlTableNameMap != null && StartupConfig.amlTableNameMap.size() > 0) {
				amlTblName = StartupConfig.amlTableNameMap.get(tblPrefix);
			} else {
				Logger.info("No table name found.....Chaeck Config");
				amlTblName = null;
			}

		} catch (Exception e) {
			Logger.error("Exception found in CommonUtils@getAMLTableName : {}", e);
		}
		return amlTblName;
	}
	
	public String toSpltFileNameNDGetTableName(String csvFileName) {
		String threeDigitName = null,finalTableName = null;
		try {
			if(StringUtils.isNotBlank(csvFileName)) {
				threeDigitName = csvFileName.substring(0, 3);
			} else {
				threeDigitName = null;
			}
		} catch (Exception e) {
			Logger.error("Exception found in CommonUtils@toSpltFileName : {}", e);
		}
		Logger.info("Table Three Char : [{}] ", threeDigitName);
		finalTableName = getAMLTableName(threeDigitName);
		return finalTableName;
	}
}
