package com.github.yilmazbahadir.loan.calculator.service.engine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.yilmazbahadir.loan.calculator.data.model.Offer;
import com.github.yilmazbahadir.loan.calculator.service.model.Quote;

@Component
public class CompetitiveQuoteEngine implements QuoteEngine {

	private OfferMatchingEngine offerMatchingEngine;
	private InterestCalculator interestCalculator;

	@Autowired
	public CompetitiveQuoteEngine(OfferMatchingEngine offerMatchingEngine, InterestCalculator interestCalculator) {
		this.offerMatchingEngine = offerMatchingEngine;
		this.interestCalculator = interestCalculator;
	}

	public Quote getQuote(BigDecimal requestedLoanAmount, int months) throws InsufficientOffersException {
		List<Offer> offers = this.offerMatchingEngine.match(requestedLoanAmount);
		BigDecimal totalAmountWithInterest = BigDecimal.ZERO;
		BigDecimal weightedRate = BigDecimal.ZERO;
		BigDecimal sum = BigDecimal.ZERO;

		if (offers.isEmpty()) {
			throw new InsufficientOffersException();
		}

		for (Offer offer : offers) {
			BigDecimal amountWithInterest = this.interestCalculator.calculate(offer.getAvailable(), offer.getRate(),
					months);
			sum = sum.add(offer.getAvailable().multiply(offer.getRate()));
			totalAmountWithInterest = totalAmountWithInterest.add(amountWithInterest);
		}
		weightedRate = sum.divide(requestedLoanAmount, 8, RoundingMode.HALF_UP);

		BigDecimal monthlyRepayment = totalAmountWithInterest.divide(BigDecimal.valueOf(months), 8,
				RoundingMode.HALF_UP);
		return Quote.builder().requestedAmount(requestedLoanAmount).rate(weightedRate).monthlyRepayment(monthlyRepayment)
				.totalRepayment(totalAmountWithInterest).build();
	}
}
