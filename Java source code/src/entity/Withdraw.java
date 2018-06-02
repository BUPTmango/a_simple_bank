package entity;

import java.util.Date;

/**
 * The withdraw class extends to transaction class.
 * @author wgl
 *
 */
public class Withdraw extends Transaction {

	public Withdraw(double amount, Date date) {
		super(amount, date);
	}
	
}
