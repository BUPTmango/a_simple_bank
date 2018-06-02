package entity;

import java.util.Date;

/**
 * The current account class extends to account class(it can overdraft)
 * @author wgl
 *
 */
public class CurrentAccount extends Account {
	
	public CurrentAccount(Customer customer) {
		super(customer);
		this.type = 3;
	}

	private double overdraft = 500;
    
	
	/**
	 * override the addwithdraw method for overdraft.
	 */
	public void addWithdraw(int amount) {
		if(isActive && !isSuspended){
			Transaction with = new Withdraw(amount, new Date());
			if (balance >= amount) {
				balance = balance - amount;
			} else {
				System.out.println("you can overdraft 500.");
				if (amount - balance <= overdraft) {
					balance -= amount;
					System.out.println("Attention!!! You have overdrawn your balance.");
				} else {
					System.out.println("Exceed the overdraft limit.");
				}
			}
			showBalance();
		}
	}

}
