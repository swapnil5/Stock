package com.stocks.model;

import java.util.List;

public class Stocks {

	private String userName;
	
	private List<String> stocks;
	

	public Stocks() {
	}


	public Stocks(String userName, List<String> stocks) {
		this.userName = userName;
		this.stocks = stocks;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public List<String> getStocks() {
		return stocks;
	}


	public void setStocks(List<String> stocks) {
		this.stocks = stocks;
	}
	
	

}
