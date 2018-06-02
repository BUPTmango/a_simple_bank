package UI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

import entity.Account;
import entity.Customer;
import entity.SaverAccount;
import services.Bank_Control;

/**
 * The user interface
 * @author wgl
 *
 */
public class Bank_UI {
	public static Customer loginCus = null;
	public static Account chooseAcc = null;

	static Scanner in = new Scanner(System.in);
	static Bank_Control bc = new Bank_Control();
	
	/**
	 * The welcome interface.
	 * @param args args[]
	 * @throws ParseException the cast exception
	 */
	public static void main(String[] args) throws ParseException {
		System.out.println("**********Welcome to MANGO bank***********");
		boolean flag = true;
		while (flag) {
			System.out.println("Please choose your service.\n1 Register\n2 Login\n3 Exit");
			int enter = in.nextInt();
			switch (enter) {
			case 1:
				register();
				break;
			case 2:
				login();
				break;
			default:
				flag = false;
				System.out.println("Welcome to visit next time!");
			}
		}
	}
	
	/**
	 * The register interface
	 * @throws ParseException
	 */
	private static void register() throws ParseException {
		System.out.println("Please input some basic information.");
		System.out.println("name:");
		String name = in.next();
		System.out.println("birthday:(format:yyyy-MM-dd)");
		String birthday = in.next();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = dateFormat.parse(birthday);
		System.out.println("address:");
		String address = in.next();
		bc.addCustomer(name, myDate, address);
		System.out.println("register successful!");
	}

	/**
	 * The login interface and the account services
	 */
	private static void login() {
		System.out.println("Please input your name to login.");
		String name = in.next();
		boolean flag = false;
		for (Customer cus : bc.cusList) {
			if (name.equals(cus.getName())) {
				flag = true;
				loginCus = cus;
			}
		}
		if (flag) {
			System.out.println("login successful!");
			boolean cusFlag = true;
			while (cusFlag) {
				System.out.println(
						"Please choose your service.\n1 Open a new account.\n2 Deposit\n3 Withdraw\n4 Suspend account\n5 Reinstate account\n6 Close account\n7 Give notice\n8 View accounts\n9 Clear funds.\n10 Back to home");
				int enter = in.nextInt();
				switch (enter) {
				case 1:
					createAcc();
					break;
				case 2:
					showAccToChoose();
					deposit();
					break;
				case 3:
					showAccToChoose();
					withdraw();
					break;
				case 4:
					showAccToChoose();
					suspend();
					break;
				case 5:
					showAccToChoose();
					reinstate();
					break;
				case 6:
					showAccToChoose();
					close();
					break;
				case 7:
					showAccToChoose();
					giveNotice();
					break;
				case 8:
					showAcc();
					break;
				case 9:
					clearFunds();
					break;
				default:
					cusFlag = false;
				}
			}
		} else {
			System.out.println("no such customer, please register first!");
		}
	}

	/**
	 * This method is used to clear all accounts' funds of the login customer.
	 */
	private static void clearFunds() {
		for(Account acc : loginCus.getAccList()){
			bc.clearFunds(acc);
		}
		System.out.println("Funds cleared!");
	}

	/**
	 * This method is used to show all accounts of the login customer(account number, account type, account balance).
	 */
	private static void showAcc() {
		if (loginCus.getAccList().size() == 0) {
			System.out.println("You have no account, please open first!");
		} else {
			System.out.println("These are your accounts:");
			String type = null;
			int index = 1;
			for (Account acc : loginCus.getAccList()) {
				if (acc.getType() == 1) {
					type = "Saver Account";
				}
				if (acc.getType() == 2) {
					type = "Junior Account";
				}
				if (acc.getType() == 3) {
					type = "Current Account";
				}
				System.out.println(index + " Account " + acc.getAccNum() + "(" + type + ")    balance: " + acc.getBalance());
				index++;
			}
		}
	}

	/**
	 * This method is used to give notice to withdraw(especially for saver account).
	 */
	private static void giveNotice() {
		if (!(loginCus.getAccList().size() == 0)){
			if(!chooseAcc.isActive()){
				System.out.println("your account have been closed!");
			}else{
				if(chooseAcc.isSuspended()){
					System.out.println("you account have been suspended!");
				}else{
					if(chooseAcc instanceof SaverAccount){
						System.out.println("Please input the amount you want to notice.");
						int amount = in.nextInt();
						bc.giveNotice(chooseAcc, amount);
					}else{
						System.out.println("you don't need to notice.");
					}
				}
			}
		}
		
	}

	/**
	 * This method is used to close a account.
	 */
	private static void close() {
		if (!(loginCus.getAccList().size() == 0)){
			bc.closeAccount(chooseAcc);
		}
	}

	/**
	 * This method is used to reinstate a account.
	 */
	private static void reinstate() {
		if (!(loginCus.getAccList().size() == 0)){
			bc.reinstate(chooseAcc);
		}
	}

	/**
	 * This method is used to suspend a account.
	 */
	private static void suspend() {
		if (!(loginCus.getAccList().size() == 0)){
			bc.suspend(chooseAcc);
		}
	}

	/**
	 * This method is used to get money from account.
	 */
	private static void withdraw() {
		if (!(loginCus.getAccList().size() == 0)) {
			if(!chooseAcc.isActive()){
				System.out.println("your account have been closed!");
			}else{
				if(chooseAcc.isSuspended()){
					System.out.println("you account have been suspended!");
				}else{
					System.out.println("Please input the amount you want to withdraw:(type:int)");
					int amount = in.nextInt();
					bc.withdraw(chooseAcc, amount);
				}
			}
		}
	}

	/**
	 * This method is used to put money in bank.
	 */
	private static void deposit() {
		if (!(loginCus.getAccList().size() == 0)) {
			if(!chooseAcc.isActive()){
				System.out.println("your account have been closed!");
			}else{
				if(chooseAcc.isSuspended()){
					System.out.println("you account have been suspended!");
				}else{
					System.out.println("Please input the amount you want to deposit:(type:int)");
					int amount = in.nextInt();
					System.out.println("Please choose type: 1 Cash    2 Cheque.");
					int type = in.nextInt();
					boolean booleanType = true;
					if (type == 2) {
						booleanType = false;
					}
					bc.deposit(chooseAcc, amount, booleanType);
				}
			}
		}
	}

	/**
	 * This method is used to show account information and choose one to operate.
	 */
	private static void showAccToChoose() {
		if (loginCus.getAccList().size() == 0) {
			System.out.println("You have no account, please open first!");
		} else {
			System.out.println("Please choose your account:");
			String type = null;
			int index = 1;
			for (Account acc : loginCus.getAccList()) {
				if (acc.getType() == 1) {
					type = "Saver Account";
				}
				if (acc.getType() == 2) {
					type = "Junior Account";
				}
				if (acc.getType() == 3) {
					type = "Current Account";
				}
				System.out.println(index + " Account " + acc.getAccNum() + "(" + type + ")    balance: " + acc.getBalance());
				index++;
			}
			int choose = in.nextInt();
			chooseAcc = loginCus.getAccList().get(choose - 1);
			
		}
	}

	/**
	 * This method is used to create a account.
	 */
	private static void createAcc() {
		System.out.println("Please input account type:(1 Saver Account   2 Junior Account   3 Current Account)");
		int type = in.nextInt();
		bc.createAccount(loginCus, type);
	}

}
