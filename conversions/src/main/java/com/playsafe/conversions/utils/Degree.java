package com.playsafe.conversions.utils;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@NotNull(message = "Request cannot be null")
public class Degree {

	private double enteredValue;

	@NotNull
	@DecimalMin("-272.15")
	@DecimalMax("726.85")
	@Digits(fraction = 2, integer = 16)
	public double getEnteredValue() {
		return enteredValue;
	}

	public void setEnteredValue(double enteredValue) {
		this.enteredValue = enteredValue;
	}
}
