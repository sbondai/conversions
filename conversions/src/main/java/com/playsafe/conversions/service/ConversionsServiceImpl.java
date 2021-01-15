package com.playsafe.conversions.service;

import org.springframework.stereotype.Service;

@Service
public class ConversionsServiceImpl implements ConversionService {

	public static final double KELVIN_CONSTANT = 273.15;
	public static final double MILES_TO_KM_CONSTANT = 1.60934;
	public static final double KM_TO_MILES_CONSTANT = 0.621371;

	@Override
	public double convertKelvintoDegrees(double kelvin) {

		return kelvin - KELVIN_CONSTANT;
	}

	@Override
	public double convertDegreetoKelvin(double degree) {
		return degree + KELVIN_CONSTANT;
	}

	@Override
	public double convertMilestoKm(double miles) {
		return miles * MILES_TO_KM_CONSTANT;
	}

	@Override
	public double convertKmtoMiles(double km) {
		return km * KM_TO_MILES_CONSTANT;
	}

}
