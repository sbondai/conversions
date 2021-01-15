package com.playsafe.conversions.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.playsafe.conversions.service.ConversionService;
import com.playsafe.conversions.utils.Kelvin;

@RestController
@RequestMapping("/conversions")
public class ConversionsController {

	@Autowired
	ConversionService conversionService;

	@PostMapping("/ktoc")
	double convertKelvintoCelcius(@Valid @RequestBody Kelvin kelvin) {
		return conversionService.convertKelvintoDegrees(kelvin.getEnteredValue());
	}

}
