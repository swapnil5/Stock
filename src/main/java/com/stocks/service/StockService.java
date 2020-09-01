package com.stocks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stocks.model.Stock;
import com.stocks.model.Stocks;


@Service
public interface StockService {
	
	List<Stock> findByUserName(String username);

	void saveStock(Stocks stocks);

	void deleteUserStocks(String username);

}
