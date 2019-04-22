package com.github.yilmazbahadir.loan.calculator.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.yilmazbahadir.loan.calculator.exception.LoanAmountInvalidIncrementException;
import com.github.yilmazbahadir.loan.calculator.exception.LoanAmountOutOfRangeException;
import com.github.yilmazbahadir.loan.calculator.service.engine.InsufficientOffersException;
import com.github.yilmazbahadir.loan.calculator.service.engine.QuoteEngine;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmount;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmountIncrementValidator;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmountRangeValidator;
import com.github.yilmazbahadir.loan.calculator.service.model.Quote;

@Service
public class CompetitiveLoanCalculatorService implements LoanCalculatorService {

	private QuoteEngine quoteEngine;
	private LoanAmountRangeValidator loanAmountRangeValidator;
	private LoanAmountIncrementValidator loanAmountIncrementValidator;

	@Autowired
	public CompetitiveLoanCalculatorService(QuoteEngine quoteEngine, LoanAmountRangeValidator loanAmountRangeValidator,
			LoanAmountIncrementValidator loanAmountIncrementValidator) {
		this.quoteEngine = quoteEngine;
		this.loanAmountRangeValidator = loanAmountRangeValidator;
		this.loanAmountIncrementValidator = loanAmountIncrementValidator;
	}

	@Override
	public Quote calculateLoan(@Valid LoanAmount requestedLoanAmount)
			throws LoanAmountOutOfRangeException, LoanAmountInvalidIncrementException, InsufficientOffersException {
		/* TODO if statements below shouldn't be there. 
		 * Instead, they should be validated by aop. But in order to test it they are coded as below :( */
		if (!this.loanAmountRangeValidator.isValid(requestedLoanAmount, null)) {
			throw new LoanAmountOutOfRangeException(requestedLoanAmount); // TODO pass parameter lowerBound, upperBound
		}

		if (!this.loanAmountIncrementValidator.isValid(requestedLoanAmount, null)) {
			throw new LoanAmountInvalidIncrementException(requestedLoanAmount); // TODO pass parameter for 100
		}
		return this.quoteEngine.getQuote(requestedLoanAmount.getValue(), 36); // TODO months -> parametric

	}

}
