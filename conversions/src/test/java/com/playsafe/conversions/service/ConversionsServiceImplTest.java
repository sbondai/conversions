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

}
