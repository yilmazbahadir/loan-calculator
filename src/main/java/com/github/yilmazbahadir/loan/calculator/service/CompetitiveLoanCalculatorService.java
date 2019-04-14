package com.github.yilmazbahadir.loan.calculator.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.yilmazbahadir.loan.calculator.data.repository.MarketRepository;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanResponse;

@Service
public class CompetitiveLoanCalculatorService implements LoanCalculatorService {

	private MarketRepository marketRepository;

	@Autowired
	public CompetitiveLoanCalculatorService(MarketRepository marketRepository) {
		this.marketRepository = marketRepository;
	}

	public LoanResponse calculateLoan(BigDecimal requestedLoanAmount) {

		return new LoanResponse();
	}

}
