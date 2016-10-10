package wacekh.sssm.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import wacekh.sssm.model.Stock;
import wacekh.sssm.model.StockType;
import wacekh.sssm.model.Trade;
import wacekh.sssm.model.TradeType;
import wacekh.sssm.repositories.impl.StocksRepositoryImpl;

public class StocksServiceImplTest {
	private StocksServiceImpl service;
	private StocksRepositoryImpl repository;

	@Before
	public void setUp() throws Exception {
		repository = new StocksRepositoryImpl();
		service = new StocksServiceImpl(repository);
	}

	@Test
	public void testGbce() {
		String symbol = "TEA";
		Stock stock = new Stock(symbol, StockType.COMMON, new BigDecimal(0), new BigDecimal(0),
				BigDecimal.valueOf(100));
		repository.addStock(stock);
		repository.addTrade(symbol, new Trade(new Date(), 10, TradeType.SELL, BigDecimal.valueOf(100)));
		repository.addStock(stock);
		repository.addTrade(symbol, new Trade(new Date(), 100, TradeType.BUY, BigDecimal.valueOf(10)));
		Double value = service.gbce();
		assertEquals(Double.valueOf(18.182), value);
	}

	@Test
	public void testDividendYeldTEA() {
		String symbol = "TEA";
		Stock stock;
		stock = new Stock(symbol, StockType.COMMON, new BigDecimal(0), new BigDecimal(0), BigDecimal.valueOf(100));
		repository.addStock(stock);
		BigDecimal value = service.dividendYield(symbol, new BigDecimal(10));
		assertTrue(BigDecimal.valueOf(0).compareTo(value) == 0);
	}

	@Test
	public void testDividendYeldPOP() {
		String symbol = "POP";
		Stock stock;
		stock = new Stock(symbol, StockType.COMMON, BigDecimal.valueOf(8), BigDecimal.valueOf(0),
				BigDecimal.valueOf(100));
		repository.addStock(stock);
		BigDecimal value = service.dividendYield(symbol, new BigDecimal(10));
		assertTrue(BigDecimal.valueOf(0.8).compareTo(value) == 0);
	}

	@Test
	public void testDividendYeldGIN() {
		String symbol = "GIN";
		Stock stock;
		stock = new Stock(symbol, StockType.PREFERRED, BigDecimal.valueOf(8), BigDecimal.valueOf(2),
				BigDecimal.valueOf(100));
		repository.addStock(stock);
		BigDecimal value = service.dividendYield(symbol, new BigDecimal(10));
		assertTrue(BigDecimal.valueOf(0.2).compareTo(value) == 0);
	}

	@Test
	public void testPeRatioTEA() {
		String symbol = "TEA";
		Stock stock;
		stock = new Stock(symbol, StockType.COMMON, new BigDecimal(0), new BigDecimal(0), BigDecimal.valueOf(100));
		repository.addStock(stock);
		BigDecimal value = service.peRatio(symbol, new BigDecimal(10));
		assertNull(value);
	}

	@Test
	public void testPeRatioPOP() {
		String symbol = "POP";
		Stock stock;
		stock = new Stock(symbol, StockType.COMMON, BigDecimal.valueOf(8), BigDecimal.valueOf(0),
				BigDecimal.valueOf(100));
		repository.addStock(stock);
		BigDecimal value = service.peRatio(symbol, new BigDecimal(10));
		assertTrue(BigDecimal.valueOf(1.25).compareTo(value) == 0);
	}

	@Test
	public void testVolumeWeightedStockPriceSingleTrade() {
		String symbol = "TEA";
		Stock stock;
		stock = new Stock(symbol, StockType.COMMON, new BigDecimal(0), new BigDecimal(0), BigDecimal.valueOf(100));
		repository.addStock(stock);
		repository.addTrade(symbol, new Trade(new Date(), 10, TradeType.SELL, BigDecimal.valueOf(100)));
		BigDecimal value = service.volumeWeightedStockPrice(symbol);
		assertTrue(BigDecimal.valueOf(100).compareTo(value) == 0);
	}

	@Test
	public void testVolumeWeightedStockPriceTwoTrade() {
		String symbol = "TEA";
		Stock stock;
		stock = new Stock(symbol, StockType.COMMON, new BigDecimal(0), new BigDecimal(0), BigDecimal.valueOf(100));
		repository.addStock(stock);
		repository.addTrade(symbol, new Trade(new Date(), 10, TradeType.SELL, BigDecimal.valueOf(100)));
		repository.addTrade(symbol, new Trade(new Date(), 100, TradeType.BUY, BigDecimal.valueOf(10)));
		BigDecimal value = service.volumeWeightedStockPrice(symbol);
		assertTrue(BigDecimal.valueOf(18.182).compareTo(value) == 0);
	}

}
