package com.github.yilmazbahadir.loan.calculator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Loan Calculator Application
 *
 */
@SpringBootApplication
public class LoanCalculatorApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(LoanCalculatorApplication.class, args);
	}

	public void run(String... args) throws Exception {
		System.out.println("Arguments are captured");
		String marketFilePath = args[0];
		String requestedLoanAmountStr = args[1];
		
		
	}
}
