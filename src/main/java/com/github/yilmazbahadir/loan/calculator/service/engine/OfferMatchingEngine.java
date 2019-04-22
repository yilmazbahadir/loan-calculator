package com.github.yilmazbahadir.loan.calculator.service.engine;

import java.math.BigDecimal;
import java.util.List;

import com.github.yilmazbahadir.loan.calculator.data.model.Offer;

public interface OfferMatchingEngine {

	public List<Offer> match(BigDecimal requestedLoanAmount) throws InsufficientOffersException;
}
