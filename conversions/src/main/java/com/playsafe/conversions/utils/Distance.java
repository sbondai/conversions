package com.playsafe.conversions.utils;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@NotNull(message = "Request cannot be null")
public class Distance {

	@NotNull
	@Digits(fraction = 2, integer = 16)
	private double enteredValue;

	public double getEnteredValue() {
		return enteredValue;
	}

	public void setEnteredValue(double enteredValue) {
		this.enteredValue = enteredValue;
	}
}
