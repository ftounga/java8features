package stream.domain.transactions;

public class Transaction {

	private final Traders trader;
	private final int value;
	private final int year;	
	
	public Transaction(Traders trader, int value, int year) {
		super();
		this.trader = trader;
		this.value = value;
		this.year = year;
	}
	public Traders getTrader() {
		return trader;
	}
	public Integer getValue() {
		return value;
	}
	public Integer getYear() {
		return year;
	}
	
	
}
