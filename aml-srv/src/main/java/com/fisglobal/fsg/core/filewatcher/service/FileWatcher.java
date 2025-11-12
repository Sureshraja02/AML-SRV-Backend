package com.fisglobal.fsg.core.filewatcher.service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fisglobal.fsg.core.duckdb.service.CSVDirectImportService;
import com.fisglobal.fsg.core.utils.AMLConstants;
import com.fisglobal.fsg.core.utils.CommonUtils;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileWatcher {

	private static final Logger Logger = LoggerFactory.getLogger(FileWatcher.class);

	@Autowired
	CommonUtils commonUtils;
	
	@Value("${file.directory.to.watch}")
	private String DIRECTORY_TO_WATCH;
	
	@Value("${csv.destination.folder}")
	private String DESTINATION_CSV_FOLDER;
	
	@Value("${processed.file.path}")
	private String DESTINATION_PROCESSED_FOLDER;
	
	@Value("${from.file.format}")
	private String FROM_FILE_FRMT;
	
	@Value("${to.file.format}")
	private String TO_FILE_FRMT;

	@Autowired
	FLTtoCSVConverter converter;

	@Autowired
	CSVDirectImportService cvsDirectImportService;

	
	@PostConstruct
	public void watchDirectory() {
		Thread thread = new Thread(() -> {
			try {
				WatchService watchService = FileSystems.getDefault().newWatchService();
				Path path = Paths.get(DIRECTORY_TO_WATCH);
				path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
						//, StandardWatchEventKinds.ENTRY_MODIFY);// StandardWatchEventKinds.ENT
				Logger.info("=============Processing started==================");
				Logger.info("Watching directory: {}", DIRECTORY_TO_WATCH);
				while (true) {
					WatchKey key = watchService.take();
					List<WatchEvent<?>> watcheventss = key.pollEvents();
					Integer fileCount = watcheventss.size();
					Logger.info("fileCount : {}", fileCount);
					for (WatchEvent<?> event : watcheventss) {
						WatchEvent.Kind<?> kind = event.kind();
						Path fileName = (Path) event.context();
						Logger.info("kind : {}", kind);
						if (!StandardWatchEventKinds.ENTRY_DELETE.equals(kind)
								&& !StandardWatchEventKinds.ENTRY_MODIFY.equals(kind)) {
							Logger.info("FROM_FILE_FRMT : {}", FROM_FILE_FRMT);
							if (fileName!=null && fileName.toString().endsWith(FROM_FILE_FRMT)) {
								Logger.info("File format {} Block",FROM_FILE_FRMT);
								Path fullPath = path.resolve(fileName);
								
								// Waiting for file write completion
								waitForFileCompletion(fullPath);
								
								String csvNewFilename = converter.convertFLTToCsv(fullPath, DESTINATION_CSV_FOLDER,DESTINATION_PROCESSED_FOLDER);
								try {
									if(StringUtils.isNotBlank(csvNewFilename)) {
									Path csvFilePath = Paths.get(DESTINATION_CSV_FOLDER, csvNewFilename);
										if (csvFilePath != null && Files.exists(csvFilePath)) {
										Logger.info("Final CSV Path and Name after Convert : {}", csvFilePath.toString());
										Long startDate = new Date().getTime();
										Logger.info("FileWatcher CSV Import Start Time : [{}]", startDate);
										cvsDirectImportService.importCsv(csvFilePath.toString());
										Long endTime = new Date().getTime();
										Logger.info("FileWatcher CSV Import - Total time : {}", commonUtils.findIsHourMinSec((endTime - startDate)));
										}
									}
								} catch (SQLException e) {
									Logger.error("Exception found in watchDirectory : {}", e);
								}
								
							} else if (fileName!=null && fileName.toString().endsWith(AMLConstants.CSV_FORMAT)) {
								Logger.info("File format {} block entred.",AMLConstants.CSV_FORMAT);
								// Move file
								Path fullPath = path.resolve(fileName);
								String toCsvFileName = fileName.getFileName().toString();
								Path csvFilePath = Paths.get(DESTINATION_CSV_FOLDER, toCsvFileName);
								commonUtils.toMove(fullPath, csvFilePath);
								Logger.info("File moved successfully");
								Logger.info("File format {} block End.",AMLConstants.CSV_FORMAT);
							}
							Thread.sleep(6000);
						}
					}
					boolean valid = key.reset();
					if (!valid) {
						break;
					}
				}
			} catch (IOException | InterruptedException e) {
				Logger.error("Exception found in FileWatcher@watchDirectory : {}",e);
			}  finally {
				Logger.info("Thread count : "+Thread.activeCount());
			}
		});
		thread.setDaemon(true);
		thread.start();
	}
	
	private static void waitForFileCompletion(Path file) throws InterruptedException {
        long previousSize = -1;
        while (true) {
            long currentSize = file.toFile().length();
            if (currentSize == previousSize) break;
            previousSize = currentSize;
            Thread.sleep(1000); // Wait and check again
        }
    }
}
