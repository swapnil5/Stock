package com.stocks.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stocks.model.Stock;

@Repository
public interface StockRepository  extends CrudRepository<Stock, Integer>{
	
	List<Stock> findByUserName(String username);
}
