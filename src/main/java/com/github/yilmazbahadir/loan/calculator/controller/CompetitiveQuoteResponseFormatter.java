package com.github.yilmazbahadir.loan.calculator.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.github.yilmazbahadir.loan.calculator.controller.model.QuoteResponse;
import com.github.yilmazbahadir.loan.calculator.service.model.Quote;

@Component
public class CompetitiveQuoteResponseFormatter implements QuoteResponseFormatter {

	@Override
	public String format(QuoteResponse<Quote> quoteResponse) {
		String result = "";
		// nice to have => use template engine
		if (quoteResponse.getStatus() == QuoteResponse.Status.SUCCESS) {
			result = String.format("Requested amount: £%s\nRate: %s%% \nMonthly repayment: £%s\nTotal repayment: £%s",
					quoteResponse.getBody().getRequestedAmount().setScale(0).toString(),
					quoteResponse.getBody().getRate().multiply(BigDecimal.valueOf(100))
							.setScale(1, RoundingMode.HALF_UP).toString(),
					quoteResponse.getBody().getMonthlyRepayment().setScale(2, RoundingMode.HALF_UP).toString(),
					quoteResponse.getBody().getTotalRepayment().setScale(2, RoundingMode.HALF_UP).toString());
		} else { // QuoteResponse.Status.ERROR
			result = quoteResponse.getMessage();
		}

		return result;
	}

}
