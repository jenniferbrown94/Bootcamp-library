package informed.dev.Classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Item {
	private String name;
	private Person borrower;
	private Person reserver;
	private LocalDate dueDate;
	private int id;
	private boolean onLoan;
	private String itemType;

	public Item(String name, int id, String itemType) {
		this.name = name;
		this.id = id;
		this.borrower = null;
		this.reserver = null;
		this.dueDate = null;
		this.onLoan = false;
		this.itemType = itemType;
	}

	

	public boolean borrow(Person borrower) {
		if (this.borrower == null) {
			boolean success = borrower.addOnLoanItem(this);
			if (success) {
				this.borrower = borrower;
				LocalDate today = LocalDate.now();
				this.dueDate = today.plus(2, ChronoUnit.WEEKS);
				this.onLoan=true;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean reserve(Person reserver) {
		if (borrower != null && reserver == null) {
			this.reserver = reserver;
			return true;
		} else {
			return false;
		}
	}

	public boolean returnItem() {
		boolean result = false;
		if (borrower != null) {
			boolean success = borrower.removeOnLoanItem(this);
			if (success) {
				if (reserver != null) {
					this.borrow(reserver);

				} else {
					this.borrower = null;
				}
				result = true;
			}
		}
		return result;
	}
	
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
	

	public boolean isOnLoan() {
		return onLoan;
	}
	
	public boolean isReserved() {
		boolean isRes = false;
		if (reserver != null) {
			isRes=true;
		}
		return isRes;
	}

	public String getType() {
		return itemType;

	}
	
	public int getId() {
		return this.id;
	}
	

}
