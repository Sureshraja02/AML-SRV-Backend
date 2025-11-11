package com.fisglobal.fsg.core.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

	private static final Logger Logger = LoggerFactory.getLogger(CommonUtils.class);
	
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
			if (fromPath!=null && Files.exists(fromPath)) {
				Logger.info("File moved to: [{}]", toPath.toString());
				Files.move(fromPath, toPath, StandardCopyOption.REPLACE_EXISTING);
			}
			
		} catch (Exception e) {
			Logger.error("Exception found in FLTtoCSVConverter@toMove : {}", e);
		} finally { }
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
			Logger.error("Exception found in FLTtoCSVConverter@toDelete : {}", e);
		} finally { }
	}
}
