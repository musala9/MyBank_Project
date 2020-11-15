package ATMBankManager;

public class EconomyAccount extends Account {

	public EconomyAccount(String name, User holder) {
		super(name, holder);
		// TODO Auto-generated constructor stub
		this.setOperationfee(0.01);
		this.setWithdrawalfee(1);
	}

}
