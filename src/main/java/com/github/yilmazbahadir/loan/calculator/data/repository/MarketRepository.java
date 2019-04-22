package com.github.yilmazbahadir.loan.calculator.data.repository;

import java.util.List;

import com.github.yilmazbahadir.loan.calculator.data.model.Offer;

public interface MarketRepository {
	
	public List<Offer> findAllOffers();
}
