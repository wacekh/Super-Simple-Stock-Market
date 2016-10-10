package wacekh.sssm.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Object containing data of single trade.
 * 
 * @author Waclaw Holub
 *
 */
public class Trade {
	private Date timestamp;
	private Integer quantity;
	private TradeType indicator;
	private BigDecimal price;

	public Trade(Date timestamp, Integer quantity, TradeType indicator, BigDecimal price) {
		super();
		this.timestamp = timestamp;
		this.quantity = quantity;
		this.indicator = indicator;
		this.price = price;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public TradeType getIndicator() {
		return indicator;
	}

	public void setIndicator(TradeType indicator) {
		this.indicator = indicator;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
