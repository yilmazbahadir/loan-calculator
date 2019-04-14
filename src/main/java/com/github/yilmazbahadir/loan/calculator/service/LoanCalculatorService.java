package com.github.yilmazbahadir.loan.calculator.service;

import com.github.yilmazbahadir.loan.calculator.exception.LoanAmountInvalidIncrementException;
import com.github.yilmazbahadir.loan.calculator.exception.LoanAmountOutOfRangeException;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmount;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanResponse;

public interface LoanCalculatorService {

	LoanResponse calculateLoan(LoanAmount requestedLoanAmount) throws LoanAmountOutOfRangeException, LoanAmountInvalidIncrementException;
	
}
