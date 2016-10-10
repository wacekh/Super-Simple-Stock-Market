package wacekh.sssm.repositories.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.google.inject.Singleton;

import wacekh.sssm.model.Stock;
import wacekh.sssm.model.Trade;
import wacekh.sssm.repositories.StocksRepository;

/**
 * Repository implementation holding data in memory.
 * 
 * @author Waclaw Holub
 *
 */
@Singleton
public class StocksRepositoryImpl implements StocksRepository {
	/**
	 * Map containing stock symbol as key and stock as value.
	 */
	private HashMap<String, Stock> stocks = new HashMap<>();

	/*
	 * (non-Javadoc)
	 * @see wacekh.sssm.repositories.StocksRepository#getAll()
	 */
	@Override
	public List<Stock> getAll() {
		return new ArrayList<>(stocks.values());
	}

	/*
	 * (non-Javadoc)
	 * @see wacekh.sssm.repositories.StocksRepository#getBySymbol(java.lang.String)
	 */
	@Override
	public Stock getBySymbol(String symblo) {
		return stocks.get(symblo);
	}

	/*
	 * (non-Javadoc)
	 * @see wacekh.sssm.repositories.StocksRepository#addStock(wacekh.sssm.model.Stock)
	 */
	@Override
	public void addStock(Stock stock) {
		stocks.put(stock.getSymbol(), stock);
	}

	/*
	 * (non-Javadoc)
	 * @see wacekh.sssm.repositories.StocksRepository#selectFrmoLast(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public ArrayList<Trade> selectFrmoLast(String symbol, Date from, Date to) {
		ArrayList<Trade> result = new ArrayList<>();
		long end = to.getTime();
		long startFrom = from.getTime();
		Stock stock = getBySymbol(symbol);
		for (Trade trade : stock.getTrades()) {
			long curent = trade.getTimestamp().getTime();
			if (curent > startFrom && curent <= end) {
				result.add(trade);
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see wacekh.sssm.repositories.StocksRepository#addTrade(java.lang.String, wacekh.sssm.model.Trade)
	 */
	@Override
	public void addTrade(String symbol, Trade trade) {
		Stock stock = getBySymbol(symbol);
		stock.addTrade(trade);
	}

}
