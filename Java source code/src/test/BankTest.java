package test;

import static org.junit.Assert.*;
import java.text.*;
import java.util.Date;
import org.junit.*;
import entity.*;
import services.Bank_Control;

/**
 * Junit Test class
 * @author wgl
 *
 */
public class BankTest {
	Bank_Control bc;
	Date myDate;
	
	/**
	 * This method is used to create bank control object and initialize date.
	 * @throws ParseException the cast exception
	 */
	@Before
	public void setUp() throws ParseException{
		bc = new Bank_Control();
		myDate = new SimpleDateFormat("yyyy-MM-dd").parse("1997-03-20");
	}
	
	/**
	 * This method is used to test register a customer to bank.
	 */
	@Test
	public void addCustest() {
		bc.addCustomer("wgl", myDate, "Beijing");
		int flag = 0;
		for(Customer cus : bc.cusList){
			if(cus.getName().equals("wgl")){
				flag = 1;
			}
		}
		assertEquals(1, flag);
	}
	
	/**
	 * This method is used to create a account for a customer.
	 */
	@Test
	public void createAccTest() {
		Customer cus = new Customer("wgl", myDate, "Beijing");
		bc.createAccount(cus, 1);
		assertEquals(1, cus.getAccList().size());
	}
	
	/**
	 * This method is used to test deposit.
	 */
	@Test
	public void depositTest(){
		Customer cus = new Customer("wgl", myDate, "Beijing");
		Account cAcc = new CurrentAccount(cus);
		bc.deposit(cAcc, 500, true);
		boolean flag = false;
		if(cAcc.getBalance() == 500.0){
			flag = true;
		}
		assertTrue(flag);
	}
	
	/**
	 * This method is used to test withdraw.
	 */
	@Test
	public void withdrawTest(){
		Customer cus = new Customer("wgl", myDate, "Beijing");
		Account cAcc = new CurrentAccount(cus);
		bc.withdraw(cAcc, 300);
		boolean flag = false;
		if(cAcc.getBalance() == -300.0){
			flag = true;
		}
		assertTrue(flag);
	}

}
