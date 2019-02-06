package informed.dev.Classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Item {
	private String name;
	private Person borrower;
	private Person reserver;
	private LocalDate dueDate;
	
	public Item(String name) {
		this.name = name;
	}
	
	public boolean borrow(Person borrower) {
		if (borrower == null) {
			boolean success = borrower.addOnLoanItem(this);
			if (success) {
				this.borrower = borrower;
				LocalDate today = LocalDate.now();
				this.dueDate = today.plus(2, ChronoUnit.WEEKS);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public boolean reserve (Person reserver) {
		if (borrower != null && reserver == null) {
			this.reserver = reserver;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean returnItem() {
		boolean result = false;
		if (borrower != null) {
			boolean success = borrower.removeOnLoanItem(this);
			if (success) {
				if (reserver != null) {
					this.borrower = this.reserver;
			
				}
				else {
					this.borrower = null;
				}
				result = true;
			}
		}
		return result;
	}
	
	
}
