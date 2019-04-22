package com.github.yilmazbahadir.loan.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.yilmazbahadir.loan.calculator.controller.LoanCalculatorController;
import com.github.yilmazbahadir.loan.calculator.controller.QuoteResponsePrinter;
import com.github.yilmazbahadir.loan.calculator.service.model.Quote;

/**
 * Loan Calculator Application
 *
 */
@SpringBootApplication
public class LoanCalculatorApplication implements CommandLineRunner {

	private LoanCalculatorController<Quote> loanCalculatorController;
	private QuoteResponsePrinter quoteResponsePrinter;

	@Autowired
	public LoanCalculatorApplication(LoanCalculatorController<Quote> loanCalculatorController,
			QuoteResponsePrinter quoteResponsePrinter) {
		this.loanCalculatorController = loanCalculatorController;
		this.quoteResponsePrinter = quoteResponsePrinter;

	}

	public static void main(String[] args) {
		SpringApplication.run(LoanCalculatorApplication.class, args);
	}

	public void run(String... args) throws Exception {
		String marketFilePath = args[0];
		String requestedLoanAmountStr = args[1];
		this.quoteResponsePrinter
				.print(this.loanCalculatorController.calculateLoan(marketFilePath, requestedLoanAmountStr));

	}
}
