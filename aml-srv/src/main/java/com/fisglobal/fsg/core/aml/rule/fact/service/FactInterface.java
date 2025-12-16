package com.fisglobal.fsg.core.aml.rule.fact.service;

import java.util.List;

import com.fisglobal.fsg.core.aml.rule.process.request.Factset;
import com.fisglobal.fsg.core.aml.rule.process.request.RuleRequestVo;
import com.fisglobal.fsg.core.aml.rule.process.response.ComputedFactsVO;

public interface FactInterface {

	public ComputedFactsVO getFactExecutor(RuleRequestVo requVoObjParam, Factset factSetObj,List<ComputedFactsVO> computedFacts );
}
