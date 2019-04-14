package com.github.yilmazbahadir.loan.calculator.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.yilmazbahadir.loan.calculator.data.model.Quote;
import com.github.yilmazbahadir.loan.calculator.data.repository.MarketRepository;
import com.github.yilmazbahadir.loan.calculator.service.model.LoanResponse;

@RunWith(value=MockitoJUnitRunner.class)
public class LoanCalculatorServiceTests {

	@Mock
	private MarketRepository lenderMarketRepository;
	
	@InjectMocks
	private CompetitiveLoanCalculatorService loanCalculatorService;
	
	
	@Test
	public void calculateLoan_ValidMarketDataAndLoanAmount_ShouldReturnLoanResponse() {
		BigDecimal requestedLoanAmount = BigDecimal.valueOf(1100.0);
		
		List<Quote> quotes = new ArrayList<>(); 
		when(lenderMarketRepository.findAllQuotes()).thenReturn(quotes);
		LoanResponse loanResponse = this.loanCalculatorService.calculateLoan(requestedLoanAmount);
		
		assertThat(loanResponse, is(notNullValue()));
	}
	
	
}
