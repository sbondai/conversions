package com.playsafe.conversions.utils;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@NotNull(message = "Request cannot be null")
public class Kelvin {

	@NotNull
	@Range(min = 1, max = 1000, message = "Please enter between the range of 1 and 1000")
	@Digits(fraction = 2, integer = 16)
	private double enteredValue;

	public double getEnteredValue() {
		return enteredValue;
	}

	public void setEnteredValue(double enteredValue) {
		this.enteredValue = enteredValue;
	}
}
