package com.github.yilmazbahadir.loan.calculator.service;

import com.github.yilmazbahadir.loan.calculator.exception.LoanAmountInvalidIncrementException;
import com.github.yilmazbahadir.loan.calculator.exception.LoanAmountOutOfRangeException;
import com.github.yilmazbahadir.loan.calculator.service.engine.InsufficientOffersException;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmount;
import com.github.yilmazbahadir.loan.calculator.service.model.Quote;

public interface LoanCalculatorService {

	Quote calculateLoan(LoanAmount requestedLoanAmount)
			throws LoanAmountOutOfRangeException, LoanAmountInvalidIncrementException, InsufficientOffersException;

}
