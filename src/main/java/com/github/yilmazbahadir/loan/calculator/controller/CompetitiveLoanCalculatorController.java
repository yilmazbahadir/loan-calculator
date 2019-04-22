package com.github.yilmazbahadir.loan.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.github.yilmazbahadir.loan.calculator.controller.model.QuoteResponse;
import com.github.yilmazbahadir.loan.calculator.exception.LoanAmountInvalidIncrementException;
import com.github.yilmazbahadir.loan.calculator.exception.LoanAmountOutOfRangeException;
import com.github.yilmazbahadir.loan.calculator.service.LoanCalculatorService;
import com.github.yilmazbahadir.loan.calculator.service.engine.InsufficientOffersException;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmount;
import com.github.yilmazbahadir.loan.calculator.service.model.Quote;

@Controller
public class CompetitiveLoanCalculatorController implements LoanCalculatorController<Quote> {

	private LoanCalculatorService loanCalculatorService;

	@Autowired
	public CompetitiveLoanCalculatorController(LoanCalculatorService loanCalculatorService) {
		this.loanCalculatorService = loanCalculatorService;
	}

	@Override
	public QuoteResponse<Quote> calculateLoan(String marketFilePath, String requestedLoanAmount) {

		QuoteResponse<Quote> response;
		try {
			System.setProperty("loan.calculator.data.market.file.path", marketFilePath);
			response = QuoteResponse.ok(this.loanCalculatorService.calculateLoan(new LoanAmount(requestedLoanAmount)));
		} catch (NumberFormatException e) {
			response = QuoteResponse.error(String
					.format("Requested Loan Amount[%s] format is wrong! It must be numeric.", requestedLoanAmount));
		} catch (LoanAmountOutOfRangeException | LoanAmountInvalidIncrementException | InsufficientOffersException e) {
			response = QuoteResponse.error(e.getMessage());
		} catch (Exception e) {
			response = QuoteResponse.error(e.getMessage()); // More generalized message
		} 
		return response;
	}

}
