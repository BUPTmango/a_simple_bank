package services;

import java.util.ArrayList;
import java.util.Date;

import entity.*;

/**
 * The control class of the bank system.
 * @author wgl
 *
 */
public class Bank_Control {

	public ArrayList<Customer> cusList = new ArrayList<>();
	
	/**
	 * This method is used to add a customer.
	 * @param name String
	 * @param birth Date
	 * @param address String
	 */
	public void addCustomer(String name, Date birth, String address) {
		Customer cus = new Customer(name, birth, address);
		cusList.add(cus);
	}
	
	/**
	 * This method is used to confirm a customer's credit status.
	 * @param cus Customer
	 */
	public void confirmCreditStatus(Customer cus) {
		cus.confirmCreditStatus();
	}

	/**
	 * This method is used to create a new account.
	 * @param cus Customer
	 * @param type int
	 */
	public void createAccount(Customer cus, int type) {
		cus.confirmCreditStatus();
		cus.addAccount(type);
	}

	/**
	 * This method is used to deposit.
	 * @param acc Account
	 * @param amount int
	 * @param cleared boolean
	 */
	public void deposit(Account acc, int amount, boolean cleared) {
		acc.addDeposit(amount, cleared);
	}

	/**
	 * This method is used to clear funds which are cheque.
	 * @param acc Account
	 */
	public void clearFunds(Account acc) {
		acc.clearFunds();
	}

	/**
	 * This method is used to give notice to withdraw(especially for saver account).
	 * @param acc Account
	 * @param amount double
	 */
	public void giveNotice(Account acc, double amount) {
		SaverAccount sacc = (SaverAccount) acc;
		sacc.setNotice(new Date(), amount);
	}

	/**
	 * This method is used to withdraw.
	 * @param acc Account
	 * @param amount int
	 */
	public void withdraw(Account acc, int amount) {
		acc.addWithdraw(amount);
	}

	/**
	 * This method is used to suspend a account.
	 * @param acc Account
	 */
	public void suspend(Account acc) {
		acc.setSuspended(true);
		System.out.println("your account have been suspended!");
	}

	/**
	 * This method is used to reinstate a account.
	 * @param acc Account
	 */
	public void reinstate(Account acc) {
		acc.setSuspended(false);
		System.out.println("your account have been reinstated!");
	}

	/**
	 * This method is used to close a account.
	 * @param acc Account
	 */
	public void closeAccount(Account acc) {
		acc.close();
	}

}
