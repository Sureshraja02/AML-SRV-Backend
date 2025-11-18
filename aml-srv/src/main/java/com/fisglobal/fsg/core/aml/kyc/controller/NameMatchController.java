package com.fisglobal.fsg.core.aml.kyc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisglobal.fsg.core.aml.kyc.request.NameMatchRequest;
import com.fisglobal.fsg.core.aml.kyc.response.NameMatchResponse;
import com.fisglobal.fsg.core.aml.kyc.service.NameMatchService;

@RestController
@RequestMapping(value="/api/v1.0/service", consumes = MediaType.ALL_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
public class NameMatchController {
	
	public static final Logger Logger = LoggerFactory.getLogger(NameMatchController.class);
	
	@Autowired
	NameMatchService nameMatchservice;

	 @PostMapping("/namematch")
		public ResponseEntity<?> getMatch(@RequestBody NameMatchRequest req) {
			Logger.info(":::::::::::::::::::NameMatchController getMatch Entry:::::::::::::::::");
			NameMatchResponse res = nameMatchservice.matchPercentage(req);
			Logger.info(":::::::::::::::::::NameMatchController getMatch Exit:::::::::::::::::");
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
}
