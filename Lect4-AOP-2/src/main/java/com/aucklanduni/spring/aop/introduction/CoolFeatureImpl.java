package com.aucklanduni.spring.aop.introduction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoolFeatureImpl implements CoolFeature {
	private static final Logger _logger = LoggerFactory.getLogger(CoolFeatureImpl.class);
	
	private int _state = 0;
	
	public void doTheCoolThing() {
		_state++;
		_logger.info("Cooling feature doing its thing [" + _state + "]");
	}
}
