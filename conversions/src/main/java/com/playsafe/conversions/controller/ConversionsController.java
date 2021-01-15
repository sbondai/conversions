package com.playsafe.conversions.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.playsafe.conversions.error.ApiError;
import com.playsafe.conversions.logging.LogExecutionTime;
import com.playsafe.conversions.service.ConversionService;
import com.playsafe.conversions.utils.Degree;
import com.playsafe.conversions.utils.Distance;
import com.playsafe.conversions.utils.Kelvin;

@RestController
@RequestMapping("/conversions")
public class ConversionsController {

	@Autowired
	ConversionService conversionService;

	@PostMapping("/ktoc")
	@LogExecutionTime
	double convertKelvintoCelcius(@Valid @RequestBody Kelvin kelvin) {
		return conversionService.convertKelvintoDegrees(kelvin.getEnteredValue());
	}

	@PostMapping("/ctok")
	@LogExecutionTime
	double convertDegreestoKelvin(@Valid @RequestBody Degree degree) {

		return conversionService.convertDegreetoKelvin(degree.getEnteredValue());
	}

	@PostMapping("/mtok")
	@LogExecutionTime
	double convertMilestoKm(@Valid @RequestBody Distance miles) {
		return conversionService.convertMilestoKm(miles.getEnteredValue());
	}

	@PostMapping("/ktom")
	@LogExecutionTime
	double convertKmtoMiles(@Valid @RequestBody Distance km) {
		return conversionService.convertKmtoMiles(km.getEnteredValue());
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ApiError handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request) {

		ApiError apiError = new ApiError(400, "Validation error", request.getServletPath());

		BindingResult result = exception.getBindingResult();
		Map<String, String> validationErrors = new HashMap<>();
		for (FieldError fieldError : result.getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		apiError.setValidationErrors(validationErrors);

		return apiError;
	}

}
