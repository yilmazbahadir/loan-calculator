package com.github.yilmazbahadir.loan.calculator.data.model;

import java.math.BigDecimal;

public final class Offer {

	private final String lender;
	private final BigDecimal rate;
	private final BigDecimal available;

	public Offer(String lender, BigDecimal rate, BigDecimal available) {
		this.lender = lender;
		this.rate = rate;
		this.available = available;
	}
	
	public Offer(Offer toBeCopied) {
		// all of them immutable, no need to deep copy
		this.lender = toBeCopied.getLender();
		this.rate = toBeCopied.getRate();
		this.available = toBeCopied.getAvailable(); 
	}

	public String getLender() {
		return lender;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public BigDecimal getAvailable() {
		return available;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((available == null) ? 0 : available.hashCode());
		result = prime * result + ((lender == null) ? 0 : lender.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
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
		Offer other = (Offer) obj;
		if (available == null) {
			if (other.available != null)
				return false;
		} else if (!available.equals(other.available))
			return false;
		if (lender == null) {
			if (other.lender != null)
				return false;
		} else if (!lender.equals(other.lender))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		return true;
	}

}
