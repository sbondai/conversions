package com.playsafe.conversions.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConversionsServiceImplTest {

	@InjectMocks
	private ConversionsServiceImpl conversionsService;

	@Test
	public void convert_KelvintoCelcius_whenInputIsValid() {
		double kelvin = 274.15;
		assertThat(conversionsService.convertKelvintoDegrees(kelvin)).isEqualTo(1);
	}

	@Test
	public void convert_CelciustoKelvin_whenInputIsValid() {
		double degree = 1;
		assertThat(conversionsService.convertDegreetoKelvin(degree)).isEqualTo(274.15);
	}

	@Test
	public void convert_MilestoKM_whenInputIsValid() {
		double miles = 1;
		assertThat(conversionsService.convertMilestoKm(miles)).isEqualTo(1.60934);
	}

	@Test
	public void convert_KMtoMileswhenInputIsValid() {
		double km = 1;
		assertThat(conversionsService.convertKmtoMiles(km)).isEqualTo(0.621371);
	}

}
