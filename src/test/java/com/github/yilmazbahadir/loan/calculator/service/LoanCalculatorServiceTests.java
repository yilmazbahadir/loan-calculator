package com.github.yilmazbahadir.loan.calculator.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.*;
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

import com.github.yilmazbahadir.loan.calculator.data.model.Quote;
import com.github.yilmazbahadir.loan.calculator.data.repository.MarketRepository;
import com.github.yilmazbahadir.loan.calculator.exception.LoanAmountInvalidIncrementException;
import com.github.yilmazbahadir.loan.calculator.exception.LoanAmountOutOfRangeException;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmount;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmountIncrementValidator;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanAmountRangeValidator;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanResponse;

/*
 * Cases to be tested:
 * - Valid market data and loan amount should return loan response
 * - Valid market data and invalid loan amount out of range should throw out of range exception
 * - Valid market data and valid loan amount inside the range invalid increment should throw invalid increment step
 * - No market data should throw no market data exception
 * - When exactly 1 Quote match found
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
	private MarketRepository lenderMarketRepository;

	@InjectMocks
	private CompetitiveLoanCalculatorService loanCalculatorService;

	@Test
	public void calculateLoan_ValidMarketDataAndLoanAmount_ShouldReturnLoanResponse()
			throws LoanAmountOutOfRangeException, LoanAmountInvalidIncrementException {
		LoanAmount requestedLoanAmount = new LoanAmount(BigDecimal.valueOf(1100.0));

		List<Quote> quotes = new ArrayList<>();
		when(lenderMarketRepository.findAllQuotes()).thenReturn(quotes);
		//when(loanAmountRangeValidator.isValid(requestedLoanAmount, null)).thenReturn(true);
		//when(loanAmountIncrementValidator.isValid(requestedLoanAmount, null)).thenReturn(true);

		LoanResponse loanResponse = this.loanCalculatorService.calculateLoan(requestedLoanAmount);

		assertThat(loanResponse, is(notNullValue()));
	}

	@Test(expected = LoanAmountOutOfRangeException.class)
	public void calculateLoan_LoanAmountOutOfRange_ShouldThrowLoanAmountOutOfRangeException()
			throws LoanAmountOutOfRangeException, LoanAmountInvalidIncrementException {
		LoanAmount requestedLoanAmount = new LoanAmount(BigDecimal.valueOf(900.0));

		//List<Quote> quotes = new ArrayList<>();
		//when(lenderMarketRepository.findAllQuotes()).thenReturn(quotes);
		//when(loanAmountRangeValidator.isValid(requestedLoanAmount, null)).thenReturn(false);

		LoanResponse loanResponse = this.loanCalculatorService.calculateLoan(requestedLoanAmount);
	}

}
