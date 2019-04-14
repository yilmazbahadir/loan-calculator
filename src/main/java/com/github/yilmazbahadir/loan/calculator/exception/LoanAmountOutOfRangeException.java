package com.github.yilmazbahadir.loan.calculator.exception;

import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmount;

public class LoanAmountOutOfRangeException extends BaseDomainException {

	public LoanAmountOutOfRangeException(LoanAmount amount) {
		super(String.format("Requested loan amount[%s] is out of the range", amount));
	}
	

}
