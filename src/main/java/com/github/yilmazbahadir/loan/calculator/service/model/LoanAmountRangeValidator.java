package com.github.yilmazbahadir.loan.calculator.service.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class LoanAmountRangeValidator implements ConstraintValidator<ValidLoanAmountIncrement, LoanAmount> {

	@Override
	public boolean isValid(LoanAmount amount, ConstraintValidatorContext context) {
		//TODO check increment step => must be 100.0
		if(amount != null && amount.compareTo(new LoanAmount("1000.0")) >= 0 && amount.compareTo(new LoanAmount("15000.0")) < 1) {
			return true;
		}
		return false;
	}

}
