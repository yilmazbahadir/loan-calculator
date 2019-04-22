package com.github.yilmazbahadir.loan.calculator.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.yilmazbahadir.loan.calculator.data.model.Offer;
import com.github.yilmazbahadir.loan.calculator.exception.LoanAmountInvalidIncrementException;
import com.github.yilmazbahadir.loan.calculator.exception.LoanAmountOutOfRangeException;
import com.github.yilmazbahadir.loan.calculator.service.engine.InsufficientOffersException;
import com.github.yilmazbahadir.loan.calculator.service.engine.QuoteEngine;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmount;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmountIncrementValidator;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmountRangeValidator;
import com.github.yilmazbahadir.loan.calculator.service.model.Quote;

/*
 * Cases to be tested:
 * OK - Valid market data and loan amount should return loan response
 * OK - Valid market data and invalid loan amount out of range should throw out of range exception
 * OK - Valid market data and valid loan amount inside the range invalid increment should throw invalid increment step
 * - When exactly 1 Quote match found
 * 		QuoteMatchingEngine
 * 			QuoteMatchStrategy
 * - When response quote is aggregated multiple quotes
 * - When no match found(insufficient amount in the market) 
 * */

@RunWith(value = MockitoJUnitRunner.class)
public class LoanCalculatorServiceTests {

	@Spy
	private LoanAmountIncrementValidator loanAmountIncrementValidator;

	@Spy
	private LoanAmountRangeValidator loanAmountRangeValidator;

	@Mock
	private QuoteEngine quoteEngine;

	@InjectMocks
	private CompetitiveLoanCalculatorService loanCalculatorService;

	@Test
	public void calculateLoan_ValidMarketDataAndLoanAmount_ShouldReturnLoanResponse()
			throws LoanAmountOutOfRangeException, LoanAmountInvalidIncrementException, InsufficientOffersException {
		LoanAmount requestedLoanAmount = new LoanAmount(BigDecimal.valueOf(1100.0));

		List<Offer> offers = new ArrayList<>();
		when(quoteEngine.getQuote(requestedLoanAmount.getValue(), 36)).thenReturn(Quote.builder().build());

		Quote quote = this.loanCalculatorService.calculateLoan(requestedLoanAmount);

		assertThat(quote, is(notNullValue()));
	}

	@Test(expected = LoanAmountOutOfRangeException.class)
	public void calculateLoan_LoanAmountOutOfRange_ShouldThrowLoanAmountOutOfRangeException()
			throws LoanAmountOutOfRangeException, LoanAmountInvalidIncrementException, InsufficientOffersException {
		LoanAmount requestedLoanAmount = new LoanAmount(BigDecimal.valueOf(900.0));

		this.loanCalculatorService.calculateLoan(requestedLoanAmount);
	}

	@Test(expected = LoanAmountInvalidIncrementException.class)
	public void calculateLoan_LoanAmountOutOfRange_ShouldThrowLoanAmountInvalidIncerementException()
			throws LoanAmountOutOfRangeException, LoanAmountInvalidIncrementException, InsufficientOffersException {
		LoanAmount requestedLoanAmount = new LoanAmount(BigDecimal.valueOf(1105.0));

		this.loanCalculatorService.calculateLoan(requestedLoanAmount);
	}

	@Test
	public void calculateLoan_OneExactMatchQuoteFound_ShouldReturnResult()
			throws LoanAmountOutOfRangeException, LoanAmountInvalidIncrementException, InsufficientOffersException {
		LoanAmount requestedLoanAmount = new LoanAmount(BigDecimal.valueOf(1000));
		Quote expectedQuote = Quote.builder().requestedAmount(requestedLoanAmount.getValue()).rate(new BigDecimal("0.07"))
				.monthlyRepayment(new BigDecimal("30.78")).totalRepayment(new BigDecimal("1108.10")).build();
		when(this.quoteEngine.getQuote(requestedLoanAmount.getValue(), 36)).thenReturn(expectedQuote);
		Quote quote = this.loanCalculatorService.calculateLoan(requestedLoanAmount);

		assertThat(quote, is(equalTo(expectedQuote)));
	}

}
