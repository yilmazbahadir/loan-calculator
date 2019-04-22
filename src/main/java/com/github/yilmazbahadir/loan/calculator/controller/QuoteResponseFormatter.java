package com.github.yilmazbahadir.loan.calculator.controller;

import com.github.yilmazbahadir.loan.calculator.controller.model.QuoteResponse;
import com.github.yilmazbahadir.loan.calculator.service.model.Quote;

public interface QuoteResponseFormatter {
	
	public String format(QuoteResponse<Quote> quoteResponse);

}
