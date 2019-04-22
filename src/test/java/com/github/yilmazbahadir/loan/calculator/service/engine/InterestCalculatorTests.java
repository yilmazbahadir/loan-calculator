package com.github.yilmazbahadir.loan.calculator.service.engine;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.yilmazbahadir.loan.calculator.service.engine.CompoundInterestCalculator;

@RunWith(MockitoJUnitRunner.class)
public class InterestCalculatorTests {

	@InjectMocks
	private CompoundInterestCalculator interestCalculator;

	@Test
	public void calculate_ValidInput_ShouldReturnInterestAmount() {
		BigDecimal interestAmount1 = interestCalculator.calculate(new BigDecimal("480"), new BigDecimal("0.069"), 36);
		BigDecimal interestAmount2 = interestCalculator.calculate(new BigDecimal("520"), new BigDecimal("0.071"), 36);

		BigDecimal totalRepayment = interestAmount1.add(interestAmount2).setScale(2, RoundingMode.HALF_UP);

		assertThat(totalRepayment, is(equalTo(new BigDecimal("1108.10"))));
	}
}
