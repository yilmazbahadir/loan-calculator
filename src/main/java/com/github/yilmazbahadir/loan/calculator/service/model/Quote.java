package com.github.yilmazbahadir.loan.calculator.service.model;

import java.math.BigDecimal;

public final class Quote {

	private final BigDecimal requestedAmount;
	private final BigDecimal rate;
	private final BigDecimal monthlyRepayment;
	private final BigDecimal totalRepayment;

	private Quote(BigDecimal requestedAmount, BigDecimal rate, BigDecimal monthlyRepayment, BigDecimal totalRepayment) {
		this.requestedAmount = requestedAmount;
		this.rate = rate;
		this.monthlyRepayment = monthlyRepayment;
		this.totalRepayment = totalRepayment;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private BigDecimal requestedAmount;
		private BigDecimal rate;
		private BigDecimal monthlyRepayment;
		private BigDecimal totalRepayment;

		public Builder requestedAmount(BigDecimal requestedAmount) {
			this.requestedAmount = requestedAmount;
			return this;
		}

		public Builder rate(BigDecimal rate) {
			this.rate = rate;
			return this;
		}

		public Builder monthlyRepayment(BigDecimal monthlyRepayment) {
			this.monthlyRepayment = monthlyRepayment;
			return this;
		}

		public Builder totalRepayment(BigDecimal totalRepayment) {
			this.totalRepayment = totalRepayment;
			return this;
		}

		public Quote build() {
			return new Quote(this.requestedAmount, this.rate, this.monthlyRepayment, this.totalRepayment);
		}

	}

	public BigDecimal getRequestedAmount() {
		return requestedAmount;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public BigDecimal getMonthlyRepayment() {
		return monthlyRepayment;
	}

	public BigDecimal getTotalRepayment() {
		return totalRepayment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((monthlyRepayment == null) ? 0 : monthlyRepayment.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		result = prime * result + ((requestedAmount == null) ? 0 : requestedAmount.hashCode());
		result = prime * result + ((totalRepayment == null) ? 0 : totalRepayment.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quote other = (Quote) obj;
		if (monthlyRepayment == null) {
			if (other.monthlyRepayment != null)
				return false;
		} else if (!monthlyRepayment.equals(other.monthlyRepayment))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		if (requestedAmount == null) {
			if (other.requestedAmount != null)
				return false;
		} else if (!requestedAmount.equals(other.requestedAmount))
			return false;
		if (totalRepayment == null) {
			if (other.totalRepayment != null)
				return false;
		} else if (!totalRepayment.equals(other.totalRepayment))
			return false;
		return true;
	}

}
