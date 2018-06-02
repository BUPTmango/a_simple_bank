package entity;

/**
 * The junior account class extends to account class(only under 16 years old)
 * @author wgl
 *
 */
public class JuniorAccount extends Account {
	

	public JuniorAccount(Customer customer) {
		super(customer);
		this.type = 2;
	}

	private double overdraftLimit = 0.0;
	

}
