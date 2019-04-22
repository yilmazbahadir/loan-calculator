package com.github.yilmazbahadir.loan.calculator.data.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import com.github.yilmazbahadir.loan.calculator.data.model.Offer;

@Component
public class FileMarketRepository implements MarketRepository {

	private static final String[] HEADERS = { "Lender", "Rate", "Available" }; // TODO enum ?

	private String filePath;

	private List<Offer> offers;

	@Autowired
	public FileMarketRepository(ApplicationArguments appArgs) {
		if (appArgs != null && !appArgs.getNonOptionArgs().isEmpty()) {
			this.filePath = appArgs.getNonOptionArgs().get(0);
		}

	}

	@PostConstruct
	public void init() {
		this.offers = new ArrayList<>();
		
		try (Reader in = new FileReader(new File(
				getClass().getClassLoader().getResource(this.filePath).getFile()
				))) {
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).withFirstRecordAsHeader().parse(in);
			for (CSVRecord record : records) {
				String lender = record.get("Lender");
				BigDecimal rate = new BigDecimal(record.get("Rate"));
				BigDecimal available = new BigDecimal(record.get("Available"));
				offers.add(new Offer(lender, rate, available));
			}
		} catch (FileNotFoundException e) {
			// TODO throw a runtime exception - couldn't find the market data file
			e.printStackTrace();
		} catch (IOException e) {
			// TODO throw a runtime exception -error while reading the market data file
			e.printStackTrace();
		}
	}

	@Override
	public List<Offer> findAllOffers() {
		return this.offers;
	}

}
