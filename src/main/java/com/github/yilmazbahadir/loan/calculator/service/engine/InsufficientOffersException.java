package com.github.yilmazbahadir.loan.calculator.service.engine;

import com.github.yilmazbahadir.loan.calculator.exception.BaseDomainException;

public class InsufficientOffersException extends BaseDomainException {

	public InsufficientOffersException() {
		super("Insufficient offers at the market.");
	}

}
