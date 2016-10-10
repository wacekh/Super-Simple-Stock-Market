package wacekh.sssm.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import wacekh.sssm.model.Stock;
import wacekh.sssm.model.StockType;
import wacekh.sssm.model.TradeType;

/**
 * Service give action on stock.
 * 
 * @author Waclaw Holub
 *
 */
public interface StocksService {

	/**
	 * Return list of all stocks.
	 * 
	 * @return list of stock
	 */
	List<Stock> getStocks();

	/**
	 * Add new stock to repository.
	 * 
	 * @param symbol
	 *            identifying stock
	 * @param type
	 * @param lastDividend
	 * @param fiedDividend
	 * @param parValue
	 */
	void addStock(String symbol, StockType type, BigDecimal lastDividend, BigDecimal fiedDividend, BigDecimal parValue);

	/**
	 * Add new trade to stock.
	 * 
	 * @param symbol
	 *            of stock
	 * @param timestamp
	 * @param quanity
	 * @param indicator
	 * @param price
	 */
	void addTrade(String symbol, Date timestamp, Integer quanity, TradeType indicator, BigDecimal price);

	/**
	 * Calculate the GBCE All Share Index using the geometric mean of the Volume
	 * Weight Price for all stocks.
	 * 
	 * @return
	 */
	Double gbce();

	/**
	 * Calculate the dividend yield.
	 * 
	 * @param symbol
	 * @param price
	 * @return
	 */
	BigDecimal dividendYield(String symbol, BigDecimal price);

	/**
	 * Calculate the P/E Ration
	 * 
	 * @param symbol
	 * @param price
	 * @return
	 */
	BigDecimal peRatio(String symbol, BigDecimal price);

	/**
	 * Calculate Volumen Weight Stock Price based on trades in past 5 minuts.
	 * 
	 * @param symbol
	 * @return
	 */
	BigDecimal volumeWeightedStockPrice(String symbol);
}
