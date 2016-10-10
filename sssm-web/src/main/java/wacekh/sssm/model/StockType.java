package wacekh.sssm.model;

/**
 * Enum defining type of stocks.
 * 
 * @author Waclaw Holub
 *
 */
public enum StockType {
	COMMON("Common"),
	PREFERRED("Preffered");
	
	private String dispalyName;
	private StockType(String name){
		this.dispalyName = name;
	}
	public String getDispalyName() {
		return dispalyName;
	}
}
