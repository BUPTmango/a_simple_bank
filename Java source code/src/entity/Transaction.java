package entity;

import java.util.Date;

/**
 * The transaction entity class
 * @author wgl
 *
 */
public class Transaction {
	protected double amount;
	protected Date date;
	protected Date time;
	
	public Transaction(double amount, Date date) {
		super();
		this.amount = amount;
		this.date = date;
	}
	
}
