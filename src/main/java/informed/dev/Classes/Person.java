package informed.dev.Classes;

public class Person {
	private String name;
	private int age;
	private int idNumber;
	private Item[] onLoanItems = new Item[10];

	public Person(String name, int age, int idNumber) {
		this.name = name;
		this.age = age;
		this.idNumber = idNumber;
	}

	// TODO
	public void search(String name) {

	}

	public boolean addOnLoanItem(Item borrowing) {
		int itemSpace = getNextArraySlot();
		if (itemSpace < 9) {
			onLoanItems[itemSpace] = borrowing;
			return true;
		} else {
			return false;
		}
	}

	public boolean removeOnLoanItem(Item toReturn) {
		boolean success = false;
		for (int i = 0; i < onLoanItems.length; i++) {
			if (onLoanItems[i].equals(toReturn)) {
				onLoanItems[i] = null;
				success = true;
				break;
			}
		}
		return success;
	}

	private int getNextArraySlot() {
		int freeSpace = 10;
		for (int i = 0; i < onLoanItems.length; i++) {
			if (onLoanItems[i] == null) {
				freeSpace = i;
				break;
			}
		}
		return freeSpace;
	}

}
