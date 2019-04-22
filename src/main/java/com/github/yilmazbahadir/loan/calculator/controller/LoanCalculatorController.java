package com.github.yilmazbahadir.loan.calculator.controller;

import com.github.yilmazbahadir.loan.calculator.controller.model.QuoteResponse;

public interface LoanCalculatorController<T> {

	public QuoteResponse<T> calculateLoan(String marketFilePath, String requestedLoanAmount);
}
