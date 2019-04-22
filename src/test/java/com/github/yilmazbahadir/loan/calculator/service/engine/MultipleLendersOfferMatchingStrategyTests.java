package com.github.yilmazbahadir.loan.calculator.service.engine;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.yilmazbahadir.loan.calculator.data.model.Offer;
import com.github.yilmazbahadir.loan.calculator.service.engine.InsufficientOffersException;
import com.github.yilmazbahadir.loan.calculator.service.engine.MultipleLendersOfferMatchingStrategy;
import com.github.yilmazbahadir.loan.calculator.service.engine.OfferMatchingStrategy;

@RunWith(MockitoJUnitRunner.class)
public class MultipleLendersOfferMatchingStrategyTests {

	private OfferMatchingStrategy offerMatchingStrategy;

	@Before
	public void init() {
		this.offerMatchingStrategy = new MultipleLendersOfferMatchingStrategy();
	}

	@Test
	public void matchOffers_WhenInputOffersAreMixOrdered_ShouldReturnTheOffersWithSmallestRate() throws InsufficientOffersException {
		BigDecimal requestedLoanAmount = BigDecimal.valueOf(1000);

		List<Offer> offers = Arrays.asList(new Offer("Bob", new BigDecimal("0.075"), BigDecimal.valueOf(640)),
				new Offer("Jane", new BigDecimal("0.069"), BigDecimal.valueOf(480)),
				new Offer("Fred", new BigDecimal("0.071"), BigDecimal.valueOf(520)),
				new Offer("Mary", new BigDecimal("0.104"), BigDecimal.valueOf(170)),
				new Offer("John", new BigDecimal("0.081"), BigDecimal.valueOf(320)),
				new Offer("Dave", new BigDecimal("0.074"), BigDecimal.valueOf(140)),
				new Offer("Angela", new BigDecimal("0.071"), BigDecimal.valueOf(60)));
		
		List<Offer> expectedOffers = List.of(new Offer("Jane", new BigDecimal("0.069"), BigDecimal.valueOf(480)),
				new Offer("Fred", new BigDecimal("0.071"), BigDecimal.valueOf(520)));
		
		List<Offer> matchedOffers = this.offerMatchingStrategy.matchOffers(offers, requestedLoanAmount);
		
		assertThat(matchedOffers, is(equalTo(expectedOffers)));
	}
	
	
	
}
