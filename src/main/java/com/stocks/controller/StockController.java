package com.stocks.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stocks.model.Stock;
import com.stocks.model.Stocks;
import com.stocks.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {
	
	@Autowired
	private StockService  stockService;
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = {"application/JSON"})
    public ResponseEntity<?> getQuotes(@PathVariable("username") final String username) {
		Stocks stocks = getStockByUserName(username);
		if(!stocks.getStocks().isEmpty()) {
			return new ResponseEntity<>(stocks, HttpStatus.OK);	
		}else {
			return new ResponseEntity<>("Stocks not found for requested user ", HttpStatus.NOT_FOUND);	
		}
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/JSON"})
    public ResponseEntity<?> add(@RequestBody final Stocks stocks) {

		stockService.saveStock(stocks);
        Stocks response = getStockByUserName(stocks.getUserName());
		if(stocks.getStocks().size() >= 10) {
			return new ResponseEntity<>("Stock you select's are more than ten.", HttpStatus.BAD_REQUEST);
		}else if(!response.getStocks().isEmpty()) {
			return new ResponseEntity<>(response, HttpStatus.OK);	
		}else {
			return new ResponseEntity<>("Stocks not found for requested user ", HttpStatus.NOT_FOUND);	
		}
    }

	@RequestMapping(value = "/delete/{username}", method = RequestMethod.POST, produces = {"application/JSON"})
    public ResponseEntity<?> delete(@PathVariable("username") final String username) {

        stockService.deleteUserStocks(username);
        return new ResponseEntity<>("Stock's for user "+ username +" has been deleted successfully.", HttpStatus.OK);
    }

	private Stocks getStockByUserName(@PathVariable("username") String username) {
		Stocks stocks= new Stocks(); 
		stocks.setStocks(stockService.findByUserName(username)
        		.stream()
        		.map(Stock::getStock)
        		.collect(Collectors.toList()));
		stocks.setUserName(username);
		return stocks;
	}
}
