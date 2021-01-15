package com.playsafe.conversions.service;

import org.springframework.stereotype.Service;

@Service
public class ConversionsServiceImpl implements ConversionService {

	public static final double KELVIN_CONSTANT = 273.15;

	@Override
	public double convertKelvintoDegrees(double kelvin) {

		return kelvin - KELVIN_CONSTANT;
	}

}
