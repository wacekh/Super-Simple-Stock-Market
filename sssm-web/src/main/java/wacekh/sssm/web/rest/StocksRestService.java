package wacekh.sssm.web.rest;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

import wacekh.sssm.model.Stock;
import wacekh.sssm.model.StockType;
import wacekh.sssm.model.TradeType;
import wacekh.sssm.service.StocksService;

/**
 * RESTFul web service implementation giving action on stocks.
 * 
 * @author Waclaw Holub
 *
 */
@Path("/stocks")
public class StocksRestService {
	private final StocksService stocksService;

	@Inject
	public StocksRestService(StocksService stocksService) {
		this.stocksService = stocksService;
	}

	/**
	 * Return list of registered stocks.
	 * 
	 * @return list of stock
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Stock> getStocks() {
		return stocksService.getStocks();
	}

	/**
	 * Calculate the GBCE All Share Index using the geometric mean of the Volume
	 * Weight Price for all stocks.
	 * 
	 * @return
	 */
	@GET
	@Path("/gbce")
	@Produces(MediaType.TEXT_PLAIN)
	public String gbce() {
		return NumberFormat.getInstance().format(stocksService.gbce());
	}

	/**
	 * Calculate the dividend yield.
	 * 
	 * @param symbol
	 * @param price
	 * @return
	 */
	@GET
	@Path("/{symbol}/dividendyield/price/{price}")
	@Produces(MediaType.TEXT_PLAIN)
	public String dividendYield(@PathParam("symbol") String symbol, @PathParam("price") BigDecimal price) {
		return NumberFormat.getInstance().format(this.stocksService.dividendYield(symbol, price));
	}

	/**
	 * Add nie stock to repository.
	 * 
	 * @param symbol
	 * @param type
	 * @param lastDividend
	 * @param fiedDividend
	 * @param parValue
	 */
	@POST
	@Path("/{symbol}")
	@Produces(MediaType.APPLICATION_JSON)
	public String addStock(@PathParam("symbol") String symbol, @FormParam("type") StockType type,
			@FormParam("lastDividend") BigDecimal lastDividend, @FormParam("fiedDividend") BigDecimal fiedDividend,
			@FormParam("parValue") BigDecimal parValue) {
		this.stocksService.addStock(symbol, type, lastDividend, fiedDividend, parValue);
		return symbol;
	}

	/**
	 * Calculate the P/E Ration
	 * 
	 * @param symbol
	 * @param price
	 * @return
	 */
	@GET
	@Path("/{symbol}/peratio/price/{price}")
	@Produces(MediaType.TEXT_PLAIN)
	public String peRatio(@PathParam("symbol") String symbol, @PathParam("price") BigDecimal price) {
		return NumberFormat.getInstance().format(this.stocksService.peRatio(symbol, price));
	}

	/**
	 * Calculate Volumen Weight Stock Price based on trades in past 5 minuts.
	 * 
	 * @param symbol
	 * @return
	 */
	@GET
	@Path("/{symbol}/vwst")
	@Produces(MediaType.TEXT_PLAIN)
	public String volumeWeightedStockPrice(@PathParam("symbol") String symbol) {
		return NumberFormat.getInstance().format(this.stocksService.volumeWeightedStockPrice(symbol));
	}

	/**
	 * Add new trade to stock.
	 * 
	 * @param symbol
	 * @param timestamp
	 * @param quanity
	 * @param indicator
	 * @param price
	 * @return
	 */
	@POST
	@Path("/{symbol}/trade")
	@Produces(MediaType.TEXT_PLAIN)
	public String addTrade(@PathParam("symbol") String symbol, @FormParam("timestamp") Date timestamp,
			@FormParam("quanity") Integer quanity, @FormParam("indicator") TradeType indicator,
			@FormParam("price") BigDecimal price) {
		this.stocksService.addTrade(symbol, timestamp, quanity, indicator, price);
		return symbol;
	}

}