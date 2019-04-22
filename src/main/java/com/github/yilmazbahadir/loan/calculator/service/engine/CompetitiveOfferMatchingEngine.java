package com.github.yilmazbahadir.loan.calculator.service.engine;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.yilmazbahadir.loan.calculator.data.model.Offer;
import com.github.yilmazbahadir.loan.calculator.data.repository.MarketRepository;

@Component
public class CompetitiveOfferMatchingEngine implements OfferMatchingEngine {

	private MarketRepository marketRepository;
	private OfferMatchingStrategy offerMatcher;

	@Autowired
	public CompetitiveOfferMatchingEngine(MarketRepository marketRepository, OfferMatchingStrategy offerMatcher) {
		this.marketRepository = marketRepository;
		this.offerMatcher = offerMatcher;
	}

	public List<Offer> match(BigDecimal requestedLoanAmount) throws InsufficientOffersException {
		return this.offerMatcher.matchOffers(this.marketRepository.findAllOffers(), requestedLoanAmount);
	}
}
