package wacekh.sssm.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import wacekh.sssm.model.Stock;
import wacekh.sssm.model.Trade;

/**
 * Interface defining storing and retrieving data.
 * 
 * @author Waclaw Holub
 *
 */
public interface StocksRepository {
	/**
	 * Get all contained stocks.
	 * 
	 * @return list of Stock
	 */
	List<Stock> getAll();

	/**
	 * Get a stock based on his symbol.
	 * 
	 * @param symblo
	 *            of stock
	 * @return selected stock
	 */
	Stock getBySymbol(String symblo);

	/**
	 * Add new stock to repository.
	 * 
	 * @param stock
	 */
	void addStock(Stock stock);

	/**
	 * Select trade for stock with a symbol from range.
	 * 
	 * @param symbol
	 *            of stock
	 * @param from
	 *            start date of range
	 * @param to
	 *            end date of range
	 * @return list of trade
	 */
	ArrayList<Trade> selectFrmoLast(String symbol, Date from, Date to);

	/**
	 * Add new trade to stock.
	 * 
	 * @param symbol
	 *            of stock
	 * @param trade
	 */
	void addTrade(String symbol, Trade trade);
}
