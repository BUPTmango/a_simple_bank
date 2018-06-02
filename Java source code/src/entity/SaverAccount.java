package entity;

import java.util.Date;

/**
 * The saver account class extends to account class(need to notice)
 * @author wgl
 *
 */
public class SaverAccount extends Account {
	

	public SaverAccount(Customer customer) {
		super(customer);
		this.type = 1;
		this.noticeNeeded = true;
	}


	private Date noticeDate;
	private double noticeAmount;
	
	/**
	 * calculate the days between the start day and the end day.
	 * @param start
	 * @param end
	 * @return
	 */
	private int findDays(Date start, Date end){
		return (int)(end.getTime() - start.getTime()) / 1000 / 60 / 60 / 24;
	}
	
	
	
	/**
	 * override the addwithdraw method for notice before 7 days.
	 */
	public void addWithdraw(int amount) {
		if(!isActive){
			System.out.println("your account have been closed!");
		}else{
			if(isSuspended){
				System.out.println("you account have been suspended!");
			}else{
				if(noticeDate == null){
					System.out.println("you need to notice!");
				}else{
					if(findDays(noticeDate, new Date()) >= 7){
						Transaction with = new Withdraw(amount, new Date());
						if (balance >= amount) {
							balance = balance - amount;
						} else {
							System.out.println("you don't have enough money!");
						}
						showBalance();
					}else{
						System.out.println("you must notice before 7 days.");
					}
				}
			}
		}
	}

    /**
     * notice to withdraw
     * @param noticeDate Date
     * @param noticeAmount double
     */
	public void setNotice(Date noticeDate, double noticeAmount){
		this.noticeDate = noticeDate;
		this.noticeAmount = noticeAmount;
		if(noticeAmount <= balance){
			System.out.println("notice succcessful! you can withdraw after 7 days.");
		}else{
			System.out.println("you don't have enough money!");
		}
	}

}
