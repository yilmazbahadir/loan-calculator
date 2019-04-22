package com.github.yilmazbahadir.loan.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.yilmazbahadir.loan.calculator.controller.model.QuoteResponse;
import com.github.yilmazbahadir.loan.calculator.service.model.Quote;

@Component
public class QuoteResponsePrinter {

	private QuoteResponseFormatter quoteResponseFormatter;
	
	@Autowired
	public QuoteResponsePrinter(QuoteResponseFormatter quoteResponseFormatter) {
		this.quoteResponseFormatter = quoteResponseFormatter;
	}
	
	public void print(QuoteResponse<Quote> quoteResponse) {
		System.out.println(this.quoteResponseFormatter.format(quoteResponse));
	}
}
