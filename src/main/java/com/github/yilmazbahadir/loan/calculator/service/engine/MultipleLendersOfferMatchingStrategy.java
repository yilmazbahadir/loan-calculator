package com.github.yilmazbahadir.loan.calculator.service.engine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.github.yilmazbahadir.loan.calculator.data.model.Offer;

@Component
public class MultipleLendersOfferMatchingStrategy implements OfferMatchingStrategy {

	@Override
	public List<Offer> matchOffers(List<Offer> offers, BigDecimal requestedLoanAmount) throws InsufficientOffersException {
		// sort the list by rate in descending order
		List<Offer> resultOfferList = new ArrayList<>();
		offers.sort((o1, o2) -> o1.getRate().compareTo(o2.getRate())); // TODO and if rates are equal also sort by available asc 
		BigDecimal remainingAmount = requestedLoanAmount;
		Iterator<Offer> iter = offers.iterator();
		while(iter.hasNext() && remainingAmount.compareTo(BigDecimal.ZERO) > 0) {
			Offer offer = iter.next();
			if(remainingAmount.compareTo(offer.getAvailable()) <= 0) {
				resultOfferList.add(new Offer(offer.getLender(), offer.getRate(), remainingAmount));
			} else {
				resultOfferList.add(new Offer(offer));
			}
			remainingAmount = remainingAmount.subtract(offer.getAvailable());
		}
		if(remainingAmount.compareTo(BigDecimal.ZERO) > 0) {
			throw new InsufficientOffersException();
		}
		return resultOfferList;
	}

}
