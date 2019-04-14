package com.github.yilmazbahadir.loan.calculator.service.model;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class LoanAmountIncrementValidator implements ConstraintValidator<ValidLoanAmountIncrement, LoanAmount> {

	@Override
	public boolean isValid(LoanAmount amount, ConstraintValidatorContext context) {
		if(amount != null && amount.getValue().remainder(new BigDecimal("100.0")).compareTo(BigDecimal.ZERO) == 0) {
			return true;
		}
		return false;
	}

}
