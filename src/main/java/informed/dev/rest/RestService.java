package informed.dev.rest;

import java.util.ArrayList;
import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import informed.dev.Classes.*;

@Path("/library")
public class RestService {
	
	static ArrayList<Item> lib = null;
	static ArrayList<Person> customers = null;
	static Person currentCustomer = null;
	
	private static void initLib() {
		if (lib == null) {
			lib = new ArrayList<Item>();
			lib.add(new Book("Pride and Pedigree", 1, "Jane Austen", "Fiction" ));
			lib.add(new Book("The Dog Encyclopedia for Kids", 2, "Tammy Gagne", "Kids" ));
			lib.add(new Book("Ultimate Encyclopedia of Dogs, Dog Breeds and Dog Care", 3, "Peter Larkin", "Non fiction" ));
			lib.add(new Book("The Hound of the Baskervilles", 4, "Arthur Canine Doyle", "Fiction" ));
			lib.add(new Book("Harry Potter and the Philospher's Bone", 5, "JK Growling", "Fiction" ));
			lib.add(new Book("To the Dog House", 6, "Virginia Woof", "Fiction" ));
			lib.add(new Book("The Adventures of Sherlock Bones", 7, "Arthur Canine Doyle", "Fiction" ));
			lib.add(new Book("Mutt Ado About Nothing", 7, "William Shakespeare", "Fiction" ));

			lib.add(new DVD("Jurassic Bark", "Steven Spielberg", "Action", 120 ));
			lib.add(new DVD("Marley and Me", "David Frankel", "Rom Com", 100 ));
			lib.add(new DVD("How to train your dog", "Disney", "Kids", 100 ));
			lib.add(new DVD("Cats and dogs", "Lawrence Guterman", "Kids", 100 ));
			lib.add(new DVD("101 Dalmations", "Stephen Herek", "Kids", 110 ));
			lib.add(new DVD("Reservior Dogs", "Quentin Tarantino", "Action", 130 ));
			lib.add(new DVD("Mission Inpawsible", "Bruce Geller", "Action", 130 ));
			lib.add(new DVD("Indiana Bones – Raiders of the lost Bark", "Steven Spielberg", "Action", 130 ));
			
			lib.add(new CD("Who Let the Dogs Out", "Baha Men", "Pop"));
			lib.add(new CD("Puppy Love", "Paul Anka", "Pop"));
			lib.add(new CD("Dogs days are over", "Florence and the Machine", "Pop"));
			lib.add(new CD("Common people", "Jarvis Cockerspaniel", "Pop"));
			lib.add(new CD("It's raining dogs", "The Weather Girls", "Pop"));

			
			lib.add(new Dog("Jasper", "Border Terrier", 2, "meat", 123, "tan"));
			lib.add(new Dog("Liesl", "German Pointer", 4, "cheese", 124, "brown"));
			lib.add(new Dog("Rosy", "Labrador", 10, "sausages", 125, "black"));
			lib.add(new Dog("Bruce", "Bulldog", 5, "meat", 126, "white"));
			lib.add(new Dog("Truffle", "Cockapoo", 2, "cheese", 127, "brown"));
			lib.add(new Dog("Lucy", "Labradoodle", 7, "cheese", 128, "Golden"));
			lib.add(new Dog("Louis", "French bulldog", 4, "sausage", 128, "Blue"));



		}
		
		if (customers == null ) {
			customers = new ArrayList<Person>();
			Person hope = new Customer("Hope Bristow", 24, 1);
			customers.add(hope);
		}
	}
	
	@GET
	@Path("item/{id}")
	@Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
	// eg browse to http://localhost:7070/rest/employee/3
	public Item produceJSON( @PathParam("id") String id ) {
		System.out.println( "In produceJSON() for id= "+ id);
		initLib();
		Item ret = null;
		int num= Integer.parseInt(id);
		ret = lib.get(num - 1);	
		return ret;
	}
	
	@GET
	@Path("item/{id}/name")
	@Produces(MediaType.TEXT_PLAIN)
	// eg browse to http://localhost:7070/rest/employee/3/name
	public String getItemName(@PathParam("id") String id) {
		initLib();
	
		try {
			int num= Integer.parseInt(id);
			return lib.get(num - 1).getName();	// Emp id is 1-based
		}
		catch (IndexOutOfBoundsException swallow) {
			return "error";
		}
	}
	
	@GET
	@Path("customer/{id}")
	@Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
	public Person produceJSONCustomer( @PathParam("id") String id ) {
		System.out.println( "In produceJSON() for id= "+ id);
		initLib();
		Person ret = null;
		try {
			int num= Integer.parseInt(id);
			ret = customers.get(num - 1);	
			currentCustomer = ret;
			return ret;
		}
		catch (IndexOutOfBoundsException e) {
			return ret;
		}
	}
	
	@GET
	@Path("newcustomer/{name}/{age}")
	@Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
	public Person produceJSONNewCustomer( @PathParam("name") String name, @PathParam("age") String age ) {
		initLib();
		int id = customers.size()+1;
		System.out.println( "In produceJSON() for new id= "+ id);
		Person ret = null;
			int age2 = Integer.parseInt(age);
			ret = new Customer(name, age2, id);	
			currentCustomer = ret;
			customers.add(ret);
			return ret;
	}
	
	@GET
	@Path("customer/getid")
	@Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
	public Person getId() {
		initLib();
		return currentCustomer;
	}
	
	@GET
	@Path("showitems")
	@Produces( {MediaType.TEXT_PLAIN})
	public String showItems() {
		String items = "";
		Iterator<Item> iterator = lib.iterator();
		while (iterator.hasNext()) {
		    String row = iterator.next().getName();
		    items += row + "<br>";
		}
		return items;
	}
	
}
