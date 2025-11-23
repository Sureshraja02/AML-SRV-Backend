package com.fisglobal.fsg.core.aml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.RuleResposeDetailsVO;
import com.fisglobal.fsg.core.aml.rule.service.RulesIdentifierService;
import com.google.gson.Gson;

@RestController
@RequestMapping({ "/api/v1/" })
public class RuleValidationController {

	private Logger LOGGER = LoggerFactory.getLogger(RuleValidationController.class);

	@Autowired
	RulesIdentifierService rulesIdentifierService;

	@RequestMapping(value = { "fact/service" }, method = { RequestMethod.POST })
	public ResponseEntity<?> ruleProcessMethod(@RequestBody RuleRequestVo requestObjParam) {

		ResponseEntity<Object> retunRespEntity = null;
		RuleResposeDetailsVO ruleResponseVoObj = null;
		try {

			ruleResponseVoObj = rulesIdentifierService.toComputeAMLData(requestObjParam);
			if (ruleResponseVoObj != null) {
				retunRespEntity = getResponseEntity(new Gson().toJson(ruleResponseVoObj), HttpStatus.OK);
			} else {
				retunRespEntity = getResponseEntity("No Response Found", HttpStatus.OK);
			}

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