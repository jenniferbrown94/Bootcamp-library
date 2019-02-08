package informed.dev.Classes;

import java.util.ArrayList;

public class Person {
	private String name;
	private int age;
	private int idNumber;
	private ArrayList<Item>  onLoanItems= null;
	private int max = 10;

	public Person(String name, int age, int idNumber) {
		this.name = name;
		this.age = age;
		this.idNumber = idNumber;
		this.onLoanItems = new ArrayList<Item>();
	}

	// TODO
	public void search(String name) {

	}

	public boolean addOnLoanItem(Item borrowing) {
		boolean success = false;
		if (onLoanItems.size()<max) {
			onLoanItems.add(borrowing);
			success = true;
		}
		return success;
	}
	
	public boolean removeOnLoanItem(Item borrowing) {
		onLoanItems.remove(borrowing);
		return true;
	}
		
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.idNumber;
	}
	
	public int getAge() {
		return this.age;
	}

}
