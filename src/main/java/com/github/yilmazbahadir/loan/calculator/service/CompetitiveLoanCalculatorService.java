package com.github.yilmazbahadir.loan.calculator.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.yilmazbahadir.loan.calculator.data.repository.MarketRepository;
import com.github.yilmazbahadir.loan.calculator.exception.LoanAmountInvalidIncrementException;
import com.github.yilmazbahadir.loan.calculator.exception.LoanAmountOutOfRangeException;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmount;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmountIncrementValidator;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmountRangeValidator;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanResponse;

@Service
public class CompetitiveLoanCalculatorService implements LoanCalculatorService {

	private MarketRepository marketRepository;
	private LoanAmountRangeValidator loanAmountRangeValidator;
	private LoanAmountIncrementValidator loanAmountIncrementValidator;

	@Autowired
	public CompetitiveLoanCalculatorService(MarketRepository marketRepository,
			LoanAmountRangeValidator loanAmountRangeValidator,
			LoanAmountIncrementValidator loanAmountIncrementValidator) {
		this.marketRepository = marketRepository;
		this.loanAmountRangeValidator = loanAmountRangeValidator;
		this.loanAmountIncrementValidator = loanAmountIncrementValidator;
	}

	@Override
	public LoanResponse calculateLoan(@Valid LoanAmount requestedLoanAmount)
			throws LoanAmountOutOfRangeException, LoanAmountInvalidIncrementException {
		if (!this.loanAmountRangeValidator.isValid(requestedLoanAmount, null)) {
			throw new LoanAmountOutOfRangeException(requestedLoanAmount);
		}

		if (!this.loanAmountIncrementValidator.isValid(requestedLoanAmount, null)) {
			throw new LoanAmountInvalidIncrementException(requestedLoanAmount);
		}
		marketRepository.findAllQuotes();
		return new LoanResponse();
	}

}
