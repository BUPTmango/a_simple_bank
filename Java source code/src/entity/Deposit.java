package entity;

import java.util.Date;

/**
 * The deposit class extends to transaction class.
 * @author wgl
 *
 */
public class Deposit extends Transaction {
	
	private boolean cleared;
	
	public Deposit(double amount, Date date, boolean cleared) {
		super(amount, date);
		this.cleared = cleared;
	}

}
