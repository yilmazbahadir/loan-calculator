package com.github.yilmazbahadir.loan.calculator.service.engine;

import static org.hamcrest.Matchers.hasItems;
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
import com.github.yilmazbahadir.loan.calculator.data.repository.MarketRepository;
import com.github.yilmazbahadir.loan.calculator.service.engine.CompetitiveOfferMatchingEngine;
import com.github.yilmazbahadir.loan.calculator.service.engine.InsufficientOffersException;
import com.github.yilmazbahadir.loan.calculator.service.engine.OfferMatchingStrategy;

@RunWith(MockitoJUnitRunner.class)
public class CompetitiveOfferMatchingEngineTests {

	@Mock
	private MarketRepository marketRepository;
	@Mock
	private OfferMatchingStrategy offerMatcher;

	@InjectMocks
	private CompetitiveOfferMatchingEngine offerMatchingEngine;

	@Test
	public void getQuote_ValidInput_ShouldReturnQuote() throws InsufficientOffersException {
		BigDecimal requestedLoanAmount = BigDecimal.valueOf(1000);
		
		List<Offer> offers = Arrays.asList(new Offer("Bob", new BigDecimal("0.075"), BigDecimal.valueOf(640)),
				new Offer("Jane", new BigDecimal("0.069"), BigDecimal.valueOf(480)),
				new Offer("Fred", new BigDecimal("0.071"), BigDecimal.valueOf(520)),
				new Offer("Mary", new BigDecimal("0.104"), BigDecimal.valueOf(170)),
				new Offer("John", new BigDecimal("0.081"), BigDecimal.valueOf(320)),
				new Offer("Dave", new BigDecimal("0.074"), BigDecimal.valueOf(140)),
				new Offer("Angela", new BigDecimal("0.071"), BigDecimal.valueOf(60)));
		when(this.marketRepository.findAllOffers()).thenReturn(offers);
		List<Offer> matchedOffers = Arrays.asList(new Offer("Jane", new BigDecimal("0.069"), BigDecimal.valueOf(480)),
				new Offer("Fred", new BigDecimal("0.071"), BigDecimal.valueOf(520)));
		when(offerMatcher.matchOffers(offers, requestedLoanAmount)).thenReturn(matchedOffers);
		List<Offer> resultList = this.offerMatchingEngine.match(requestedLoanAmount);
		
		assertThat(resultList, hasItems(new Offer("Jane", new BigDecimal("0.069"), BigDecimal.valueOf(480)),
				new Offer("Fred", new BigDecimal("0.071"), BigDecimal.valueOf(520))));
	}
}
