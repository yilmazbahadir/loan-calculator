package com.github.yilmazbahadir.loan.calculator.service.engine;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.github.yilmazbahadir.loan.calculator.util.BigDecimalUtils;

@Component
public class CompoundInterestCalculator implements InterestCalculator {

	@Override
	public BigDecimal calculate(BigDecimal loanAmount, BigDecimal interestRate, int months) {
		int SAFE_SCALE = 8;
		
		BigDecimal t = BigDecimalUtils
				.pow(BigDecimal.ONE.add(interestRate),
						BigDecimal.ONE.divide(BigDecimal.valueOf(12), SAFE_SCALE, RoundingMode.HALF_DOWN))
				.subtract(BigDecimal.ONE);

		BigDecimal n = t.multiply(BigDecimal.ONE.add(t).pow(months))
				.divide(BigDecimal.ONE.add(t).pow(months).subtract(BigDecimal.ONE), SAFE_SCALE, RoundingMode.HALF_UP);

		BigDecimal monthly = n.multiply(loanAmount).setScale(SAFE_SCALE, RoundingMode.HALF_UP);

		BigDecimal total = monthly.multiply(BigDecimal.valueOf(months)).setScale(SAFE_SCALE, RoundingMode.HALF_UP);

		return total;
	}

	/*
	 * JavaScript code is simplified as
	 * 
	 * double t = Math.pow(1.0 + r, 1.0 / 12) - 1.0; 
	 * double n = t * Math.pow(1.0 + t, m) / (Math.pow(1.0 + t, m) - 1.0);
	 * 
	 * double monthly = n * a; double total = (monthly) * m;
	 * 
	 */

	/*
	 * For reverse engineering purposes
	 * 
	 * extracted from source code of =>
	 * https://www.moneysupermarket.com/loans/calculator/
	 * 
	 * var l = function(r) { r = 1 + r / 100; return 100 * (r = Math.pow(r, 1 / 12)
	 * - 1) }
	 * 
	 * h = function(r, o, e) { //amount-1000, rate-0.7, term-36 var t = l(o) / 100,
	 * n = t * Math.pow(1 + t, e); return n /= Math.pow(1 + t, e) - 1, { total: (n
	 * *= r) * e, monthly: n, cost: n * e - r } }
	 * 
	 * console.log(h(1000.0, 7, 36));
	 * 
	 */

	/*
	 * Does not produce the result in the example so won't be used. So dig in to find another one :)
	 * 
	 * @Override public BigDecimal calculate(BigDecimal loanAmount, BigDecimal
	 * interestRate, int months) { // Annual interest rate BigDecimal
	 * monthlyInterestRate = interestRate.divide(BigDecimal.valueOf(12), 16,
	 * RoundingMode.HALF_UP);
	 * 
	 * BigDecimal payment = loanAmount.multiply(monthlyInterestRate)
	 * .divide((BigDecimal.ONE.subtract(BigDecimal.ONE
	 * .divide(BigDecimal.ONE.add(monthlyInterestRate), 8,
	 * RoundingMode.HALF_UP).pow(months))), 8, RoundingMode.HALF_UP);
	 * 
	 * return payment.multiply(BigDecimal.valueOf(months)).setScale(2,
	 * RoundingMode.HALF_UP); }
	 */
}
