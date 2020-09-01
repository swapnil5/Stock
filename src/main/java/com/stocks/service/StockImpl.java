package com.stocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stocks.model.Stock;
import com.stocks.model.Stocks;
import com.stocks.repository.StockRepository;

@Service
public class StockImpl implements StockService {
	
	@Autowired
	private StockRepository stockRepository;

	@Override
	public List<Stock> findByUserName(String username) {

		return stockRepository.findByUserName(username);
		
	}

	@Override
	public void saveStock(Stocks stocks) {
		stocks.getStocks()
        .stream()
        .map(stock -> new Stock(stocks.getUserName(), stock))
        .forEach(stock -> stockRepository.save(stock));
	}

	@Override
	public void deleteUserStocks(String username) {
		List<Stock> stocks = stockRepository.findByUserName(username);
		stocks.stream()
        .forEach(stock -> stockRepository.delete(stock));
	}

}
