package wacekh.sssm.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import wacekh.sssm.model.Stock;
import wacekh.sssm.model.StockType;
import wacekh.sssm.model.Trade;
import wacekh.sssm.model.TradeType;
import wacekh.sssm.repositories.StocksRepository;
import wacekh.sssm.service.StocksService;

/**
 * Implementation of a stock action.
 * 
 * @author Waclaw Holub
 *
 */
@Singleton
public class StocksServiceImpl implements StocksService {

	private final StocksRepository stocksRepository;

	@Inject
	public StocksServiceImpl(StocksRepository stocksRepository) {
		this.stocksRepository = stocksRepository;
	}

	/*
	 * (non-Javadoc)
	 * @see wacekh.sssm.service.StocksService#getStocks()
	 */
	@Override
	public List<Stock> getStocks() {
		return stocksRepository.getAll();
	}

	/*
	 * (non-Javadoc)
	 * @see wacekh.sssm.service.StocksService#addStock(java.lang.String, wacekh.sssm.model.StockType, java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal)
	 */
	@Override
	public void addStock(String symbol, StockType type, BigDecimal lastDividend, BigDecimal fiedDividend,
			BigDecimal parValue) {
		Stock stock = new Stock(symbol, type, (lastDividend != null ? lastDividend : BigDecimal.ZERO),
				(fiedDividend != null ? fiedDividend : BigDecimal.ZERO), parValue);
		stocksRepository.addStock(stock);

	}

	/*
	 * (non-Javadoc)
	 * @see wacekh.sssm.service.StocksService#gbce()
	 */
	@Override
	public Double gbce() {
		List<Stock> stocks = stocksRepository.getAll();
		if (stocks.size() == 0) {
			return 0d;
		}
		Double value = 1d;
		Date to = new Date();
		Date from = new Date(to.getTime() - TimeUnit.MINUTES.toMillis(5l));
		for (Stock stock : stocks) {
			value = value * (volumeWeightedStockPrice(stock.getSymbol(), from, to).doubleValue());
		}
		return Math.pow(value, (1 / stocks.size()));
	}

	/*
	 * (non-Javadoc)
	 * @see wacekh.sssm.service.StocksService#dividendYield(java.lang.String, java.math.BigDecimal)
	 */
	@Override
	public BigDecimal dividendYield(String symbol, BigDecimal price) {
		Stock stock = this.stocksRepository.getBySymbol(symbol);
		BigDecimal value = null;
		switch (stock.getType()) {
		case COMMON:
			value = stock.getLastDividend().divide(price);
			break;
		case PREFERRED:
			value = stock.getFixedDividend().divide(BigDecimal.valueOf(100)).multiply(stock.getParValue())
					.divide(price);
			break;
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see wacekh.sssm.service.StocksService#peRatio(java.lang.String, java.math.BigDecimal)
	 */
	@Override
	public BigDecimal peRatio(String symbol, BigDecimal price) {
		Stock stock = this.stocksRepository.getBySymbol(symbol);
		BigDecimal lastDividend = stock.getLastDividend();
		if (lastDividend.compareTo(BigDecimal.ZERO) == 0) {
			return null;
		}
		return price.divide(lastDividend);
	}

	/*
	 * (non-Javadoc)
	 * @see wacekh.sssm.service.StocksService#addTrade(java.lang.String, java.util.Date, java.lang.Integer, wacekh.sssm.model.TradeType, java.math.BigDecimal)
	 */
	@Override
	public void addTrade(String symbol, Date timestamp, Integer quanity, TradeType indicator, BigDecimal price) {
		Trade trade = new Trade((timestamp != null ? timestamp : new Date()), quanity, indicator, price);
		this.stocksRepository.addTrade(symbol, trade);
	}

	/*
	 * (non-Javadoc)
	 * @see wacekh.sssm.service.StocksService#volumeWeightedStockPrice(java.lang.String)
	 */
	@Override
	public BigDecimal volumeWeightedStockPrice(String symbol) {
		Date now = new Date();
		Date from = new Date(now.getTime() - TimeUnit.MINUTES.toMillis(5l));
		return volumeWeightedStockPrice(symbol, from, now);
	}

	private BigDecimal volumeWeightedStockPrice(String symbol, Date from, Date to) {
		ArrayList<Trade> list = this.stocksRepository.selectFrmoLast(symbol, from, to);
		BigDecimal value1 = BigDecimal.ZERO;
		BigDecimal value2 = BigDecimal.ZERO;
		for (Trade trade : list) {
			value1 = value1.add(trade.getPrice().multiply(new BigDecimal(trade.getQuantity())));
			value2 = value2.add(new BigDecimal(trade.getQuantity()));
		}
		return value1.divide(value2, 3, RoundingMode.HALF_UP);
	}

}