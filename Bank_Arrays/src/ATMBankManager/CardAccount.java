package ATMBankManager;

public class CardAccount extends Account {
       
	
	
	public CardAccount(String name, User holder) {
		super(name, holder);
		// TODO Auto-generated constructor stub
		this.setOperationfee(0.05);
		this.setWithdrawalfee(0.03);
	}

}
