package com.github.yilmazbahadir.loan.calculator.exception;

import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmount;

public class LoanAmountInvalidIncrementException extends BaseDomainException {

	public LoanAmountInvalidIncrementException(LoanAmount amount) {
		super(String.format("Requested loan amount[%s] must be divisible by 100", amount));
	}
	

}
