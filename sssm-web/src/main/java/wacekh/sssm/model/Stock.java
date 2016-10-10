package wacekh.sssm.model;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Containing information about a stock.
 * 
 * @author Waclaw Holub
 *
 */
@XmlRootElement
public class Stock {

	private String symbol;
	private StockType type;
	private BigDecimal lastDividend;
	private BigDecimal fixedDividend;
	private BigDecimal parValue;
	private ArrayList<Trade> trades = new ArrayList<>();

	public Stock(String symbol, StockType type, BigDecimal lastDividend, BigDecimal fixedDividend,
			BigDecimal parValue) {
		super();
		this.symbol = symbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public StockType getType() {
		return type;
	}

	public void setType(StockType type) {
		this.type = type;
	}

	public BigDecimal getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(BigDecimal lastDividend) {
		this.lastDividend = lastDividend;
	}

	public BigDecimal getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(BigDecimal fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public BigDecimal getParValue() {
		return parValue;
	}

	public void setParValue(BigDecimal parValue) {
		this.parValue = parValue;
	}

	public ArrayList<Trade> getTrades() {
		return trades;
	}

	public void setTrades(ArrayList<Trade> trades) {
		this.trades = trades;
	}

	public void addTrade(Trade trade) {
		trades.add(trade);
	}
}
