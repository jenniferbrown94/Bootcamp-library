package informed.dev.Classes;

import java.util.*;

public class Library {

	private ArrayList<Item> libraryContents = new ArrayList<Item>(500);
	private ArrayList<Person> Customers = new ArrayList<Person>(500);

	public Library() {

	}

	public String getItems() {
		StringBuffer items = new StringBuffer();
		Iterator<Item> itr = libraryContents.iterator();
		while(itr.hasNext()) {
			items.append(itr.next());
		}
		return new String(items);	
	}
		

	public void addItem(Item i) {
		libraryContents.add(i);
	}
}
