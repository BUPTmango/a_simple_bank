package entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The customer entity class
 * @author wgl
 *
 */
public class Customer {
	private String name;
	private Date birth;
	private String address;
	private boolean creditStatus;
	private ArrayList<Account> accList;
	
	public Customer(String name, Date birth, String address) {
		super();
		this.name = name;
		this.birth = birth;
		this.address = address;
	    this.creditStatus = false;
	    accList = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Account> getAccList() {
		return accList;
	}

	public void setAccList(ArrayList<Account> accList) {
		this.accList = accList;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isCreditStatus() {
		return creditStatus;
	}

	public void setCreditStatus(boolean creditStatus) {
		this.creditStatus = creditStatus;
	}
	
	/**
	 * Verify the customer's credit status.
	 */
	public void confirmCreditStatus(){
		this.creditStatus = true;
	}
	
    /**
     * Add account for customer
     * @param type int
     */
	public void addAccount(int type){
		Account acc = null;
		if(this.creditStatus){
			System.out.println("credit is pass! you can open account!");
			if (type == 1) {// saver
				acc = new SaverAccount(this);
				System.out.println("you have opened a saver account!");
			}
			if (type == 2) {// junior
				if(this.findAge() <= 16){
					acc = new JuniorAccount(this);
					System.out.println("you have opened a junior account!");
				}else{
					System.out.println("only under 16 can open junior account!");
				}
			}
			if (type == 3) {// current
				acc = new CurrentAccount(this);
				System.out.println("you have opened a current account!");
			}
		}else{
			System.out.println("you have bad credit, you can't open account!");
		}
		if(acc != null){
			this.getAccList().add(acc);
		}
	}
	
	/**
	 * Find the customer's age via birthday.
	 * @return the age
	 */
	public int findAge(){
		Date currunt = Calendar.getInstance().getTime();
		String currentYear = new SimpleDateFormat("yyyy").format(currunt);
		String birthYear = new SimpleDateFormat("yyyy").format(this.getBirth());
		return Integer.parseInt(currentYear) - Integer.parseInt(birthYear);
	}
	
}




