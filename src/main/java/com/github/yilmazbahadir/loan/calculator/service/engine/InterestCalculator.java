package com.github.yilmazbahadir.loan.calculator.service.engine;

import java.math.BigDecimal;

public interface InterestCalculator {

	BigDecimal calculate(BigDecimal loanAmount, BigDecimal interestRate, int months);
}
