package com.github.yilmazbahadir.loan.calculator.service.model;

import java.math.BigDecimal;

public final class LoanAmount implements Comparable<LoanAmount> {

	@ValidLoanAmountIncrement
	private final BigDecimal value;

	public LoanAmount(BigDecimal value) {
		this.value = value;
	}

	public LoanAmount(String value) {
		this.value = new BigDecimal(value);
	}

	public BigDecimal getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}

	@Override
	public int compareTo(LoanAmount o) {
		return this.getValue().compareTo(o.getValue());
	}

}
