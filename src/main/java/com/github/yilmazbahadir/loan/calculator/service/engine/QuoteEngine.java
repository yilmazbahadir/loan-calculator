package com.github.yilmazbahadir.loan.calculator.service.engine;

import java.math.BigDecimal;

import com.github.yilmazbahadir.loan.calculator.service.model.Quote;

public interface QuoteEngine {

	Quote getQuote(BigDecimal requestedLoanAmount, int months) throws InsufficientOffersException;
}
