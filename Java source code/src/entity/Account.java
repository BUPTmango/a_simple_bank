package entity;

import java.util.Date;
import java.util.Random;

/**
 * Account entity class
 * 
 * @author wgl
 *
 */
public class Account {
	// instance variables
	protected int accNum;
	protected int pin;
	protected Customer customer;
	protected double balance;
	protected double overdraftLimit;
	protected boolean isSuspended;
	protected boolean isActive;
	protected boolean noticeNeeded;
	protected double chequeSum;
	protected int type;
	
	/**
	 * Use param to generate a object.
	 * @param customer Customer
	 */
	public Account(Customer customer) {
		this.customer = customer;
		this.balance = 0.0;
		this.isActive = true;
		this.noticeNeeded = false;
		generatePin();
		generateAccnum();
	}

	/**
	 * Generate random pin.
	 */
	private void generatePin() {
		Random ran = new Random();
		pin = 100000 + ran.nextInt(900000);
	}

	/**
	 * Generate random account number.
	 */
	private void generateAccnum() {
		Random ran = new Random();
		accNum = 1000 + ran.nextInt(9000);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAccNum() {
		return accNum;
	}

	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getOverdraftLimit() {
		return overdraftLimit;
	}

	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isNoticeNeeded() {
		return noticeNeeded;
	}

	public void setNoticeNeeded(boolean noticeNeeded) {
		this.noticeNeeded = noticeNeeded;
	}

	public double getChequeSum() {
		return chequeSum;
	}

	public void setChequeSum(double chequeSum) {
		this.chequeSum = chequeSum;
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	/**
	 * new a deposit object and deposit
	 * 
	 * @param amount int
	 * @param cleared boolean
	 */
	public void addDeposit(int amount, boolean cleared) {
		Transaction dep = new Deposit(amount, new Date(), cleared);
		if (cleared) {
			balance = balance + amount;
		} else {
			chequeSum += amount;
		}
		showBalance();

	}

	/**
	 * display your account balance.
	 */
	protected void showBalance() {
		System.out.println("Your balance: " + this.getBalance() + ".");
	}

	/**
	 * new a withdraw object and withdraw
	 * 
	 * @param amount int
	 */
	public void addWithdraw(int amount) {

		Transaction with = new Withdraw(amount, new Date());
		if (balance >= amount) {
			balance = balance - amount;
		} else {
			System.out.println("you don't have enough money!");
		}
		showBalance();

	}

	/**
	 * Used to clear the cheque funds.
	 */
	public void clearFunds() {
		if (chequeSum != 0) {
			balance += chequeSum;
			chequeSum = 0.0;
		}
	}

	/**
	 * set the state of suspend or reinstate
	 * 
	 * @param susFlag boolean
	 */
	public void setSuspended(boolean susFlag) {
		this.isSuspended = susFlag;
	}

	/**
	 * close the account
	 */
	public void close() {
		if(balance == 0.0){
			this.isActive = false;
			System.out.println("your account have been closed!");
		}else{
			System.out.println("Your balance is not cleared. You can't close it.");
		}
	}

}
