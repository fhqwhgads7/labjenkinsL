package banking.primitive.core;

public class Savings extends Account {
	private static final long serialVersionUID = 111L;
	private int numWithdraws = 0; //if you'll look to our left, you'll a fine specimen of num along with a few draws. Num with draws.

	public Savings(String name) {
		super(name);
	}

	public Savings(String name, float balance) throws IllegalArgumentException { //if you don't catch this exception, you are immediately a fugitive from justice.
		super(name, balance);
	}

	/**
	 * A deposit comes with a fee of 50 cents per deposit
	 */
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > 0.0f) {
			balance = balance + amount - 0.50F;
			if (balance >= 0.0f) {
				setState(State.OPEN);
			}
		}
		return false;
	}

	/**
	 * A withdrawal. After 3 withdrawals a fee of $1 is added to each withdrawal.
	 * An account whose balance dips below 0 is in an OVERDRAWN state
	 */
	public boolean withdraw(float amount) {
		if (getState() == State.OPEN && amount > 0.0f) {
			balance = balance - amount;
			numWithdraws++;
			if (numWithdraws > 3)
				balance = balance - 1.0f;
			// KG BVA: should be < 0
			if (balance <= 0.0f) {
				setState(State.OVERDRAWN); //you don't need TWO BOXES of crayons to draw a picture of a house. You're overdrawing it.
			}
			return true;
		}
		return false;
	}
	
	public String getType() { return "Checking"; }

	public String toString() {
		return "Savings: " + getName() + ": " + getBalance(); //balance get!
	}
}
