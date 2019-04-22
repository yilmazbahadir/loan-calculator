package com.github.yilmazbahadir.loan.calculator.service.engine;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.yilmazbahadir.loan.calculator.data.model.Offer;
import com.github.yilmazbahadir.loan.calculator.service.engine.CompetitiveQuoteEngine;
import com.github.yilmazbahadir.loan.calculator.service.engine.InsufficientOffersException;
import com.github.yilmazbahadir.loan.calculator.service.engine.InterestCalculator;
import com.github.yilmazbahadir.loan.calculator.service.engine.OfferMatchingEngine;
import com.github.yilmazbahadir.loan.calculator.service.model.Quote;

@RunWith(MockitoJUnitRunner.class)
public class CompetitiveQuoteEngineTests {

	@Mock
	private OfferMatchingEngine offerMatchingEngine;
	
	@Mock
	private InterestCalculator interestCalculator;

	@InjectMocks
	private CompetitiveQuoteEngine quoteEngine;

	@Test
	public void getQuote_ValidInput_ShouldReturnQuote() throws InsufficientOffersException {
		BigDecimal requestedLoanAmount = BigDecimal.valueOf(1000);
		
		List<Offer> matchedOffers = Arrays.asList(new Offer("Jane", new BigDecimal("0.069"), BigDecimal.valueOf(480)),
				new Offer("Fred", new BigDecimal("0.071"), BigDecimal.valueOf(520)));
		when(offerMatchingEngine.match(requestedLoanAmount)).thenReturn(matchedOffers);
		when(interestCalculator.calculate(new BigDecimal("480"), new BigDecimal("0.069"), 36)).thenReturn(new BigDecimal("531.11790720"));
		when(interestCalculator.calculate(new BigDecimal("520"), new BigDecimal("0.071"), 36)).thenReturn(new BigDecimal("576.98353440"));
		
		Quote expectedQuote = Quote.builder().requestedAmount(requestedLoanAmount).rate(new BigDecimal("0.07004000"))
				.monthlyRepayment(new BigDecimal("30.78059560")).totalRepayment(new BigDecimal("1108.10144160")).build();
		
		Quote quote = this.quoteEngine.getQuote(requestedLoanAmount, 36);
		assertThat(quote, is(equalTo(expectedQuote)));
	}
}
