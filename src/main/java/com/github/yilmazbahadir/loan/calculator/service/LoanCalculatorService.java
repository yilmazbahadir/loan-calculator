package com.github.yilmazbahadir.loan.calculator.service;

import java.math.BigDecimal;

import com.github.yilmazbahadir.loan.calculator.service.model.LoanResponse;

public interface LoanCalculatorService {

	LoanResponse calculateLoan(BigDecimal requestedLoanAmount);
	
}
