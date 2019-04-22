package com.github.yilmazbahadir.loan.calculator.repository;

import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.DefaultApplicationArguments;

import com.github.yilmazbahadir.loan.calculator.data.model.Offer;
import com.github.yilmazbahadir.loan.calculator.data.repository.FileMarketRepository;

public class FileMarketRepositoryTests {

	private FileMarketRepository fileMarketRepository;

	@Before
	public void init() {
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(new String[] { "Market.csv" });

		this.fileMarketRepository = new FileMarketRepository(appArgs);
		this.fileMarketRepository.init();
	}

	@Test
	public void findAllOffers_WhenFilePathExists_ShouldReturnTheExactOffersIntheFile() {
		List<Offer> actualOffers = this.fileMarketRepository.findAllOffers();

		assertThat(actualOffers,
				hasItems(new Offer("Bob", new BigDecimal("0.075"), BigDecimal.valueOf(640)),
						new Offer("Jane", new BigDecimal("0.069"), BigDecimal.valueOf(480)),
						new Offer("Fred", new BigDecimal("0.071"), BigDecimal.valueOf(520)),
						new Offer("Mary", new BigDecimal("0.104"), BigDecimal.valueOf(170)),
						new Offer("John", new BigDecimal("0.081"), BigDecimal.valueOf(320)),
						new Offer("Dave", new BigDecimal("0.074"), BigDecimal.valueOf(140)),
						new Offer("Angela", new BigDecimal("0.071"), BigDecimal.valueOf(60))));
	}
}
