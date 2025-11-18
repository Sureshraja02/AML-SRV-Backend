package com.fisglobal.fsg.core.aml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.RuleResponseVo;
import com.google.gson.Gson;

@RestController
@RequestMapping({ "/api/service/" })
public class RuleValidationController {

	private Logger LOGGER = LoggerFactory.getLogger(RuleValidationController.class);

	@RequestMapping(value = { "/ruleprocess" }, method = { RequestMethod.POST })
	public ResponseEntity<?> ruleProcessMethod(@RequestBody RuleRequestVo requestObjParam) {

		ResponseEntity<Object> retunRespEntity = null;
		RuleResponseVo ruleResponseVoObj = null;
		try {

			retunRespEntity = getResponseEntity(new Gson().toJson(ruleResponseVoObj), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Exception found in RuleValidationController@ruleProcessMethod : {}", e);
			retunRespEntity = getResponseEntity("Exception, Will check with Admin", HttpStatus.BAD_REQUEST);
		} finally {

		}
		return retunRespEntity;

	}

	public ResponseEntity<Object> getResponseEntity(String respMsg, HttpStatus httpStatus) {
		return new ResponseEntity<Object>(respMsg, httpStatus);
	}
}