package com.github.yilmazbahadir.loan.calculator.controller;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.yilmazbahadir.loan.calculator.controller.model.QuoteResponse;
import com.github.yilmazbahadir.loan.calculator.service.model.Quote;

public class CompetitiveQuoteResponseFormatterTests {

	@Test
	public void format_ExpectedQuoteResponse_ShouldReturnFormattedString() {
		CompetitiveQuoteResponseFormatter responseFormatter = new CompetitiveQuoteResponseFormatter();
		Quote quote = Quote.builder().requestedAmount(new BigDecimal("1000")).rate(new BigDecimal("0.07004000"))
				.monthlyRepayment(new BigDecimal("30.78059560")).totalRepayment(new BigDecimal("1108.10144160")).build();
		QuoteResponse<Quote> quoteResponse = QuoteResponse.<Quote>ok(quote);
		String expectedFormattedString = "Requested amount: £1000\nRate: 7.0% \nMonthly repayment: £30.78\nTotal repayment: £1108.10";
		String actualFormattedString = responseFormatter.format(quoteResponse);
		
		assertThat(actualFormattedString, is(equalTo(expectedFormattedString)));
		
	}
}
