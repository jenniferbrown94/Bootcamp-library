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
          customers = new ArrayList<Person>();
          Person hope = new Customer("Hope Bristow", 24, 1);
                  customers.add(hope);
   
                  
                  
                  lib = new ArrayList<Item>();
                  lib.add(new Book("Pride and Pedigree", 1, "Jane Austen", "Fiction", 1 ));
          lib.add(new Book("The Dog Encyclopedia for Kids", 2, "Tammy Gagne", "Kids", 2 ));
          lib.add(new Book("The Hound of the Baskervilles", 4, "Arthur Canine Doyle", "Fiction", 4 ));
          lib.add(new Book("Harry Potter and the Philospher's Bone", 5, "JK Growling", "Fiction", 5 ));
          lib.add(new Book("To the Dog House", 6, "Virginia Woof", "Fiction", 6 ));
          lib.add(new Book("The Adventures of Sherlock Bones", 7, "Arthur Canine Doyle", "Fiction", 7 ));
          Book b = new Book("Mutt Ado About Nothing", 8, "William Shakespeare", "Fiction", 8);
          b.borrow(hope);
          lib.add(b);

          lib.add(new DVD("Jurassic Bark", "Steven Spielberg", "Action", 120, 9 ));
          lib.add(new DVD("Marley and Me", "David Frankel", "Rom Com", 100, 10));
          lib.add(new DVD("How to train your dog", "Disney", "Kids", 100 , 11));
          lib.add(new DVD("Cats and dogs", "Lawrence Guterman", "Kids", 100, 12 ));
          lib.add(new DVD("101 Dalmations", "Stephen Herek", "Kids", 110 , 13));
          lib.add(new DVD("Reservior Dogs", "Quentin Tarantino", "Action", 130, 14 ));
          lib.add(new DVD("Mission Inpawsible", "Bruce Geller", "Action", 130 , 15));
          lib.add(new DVD("Indiana Bones – Raiders of the lost Bark", "Steven Spielberg", "Action", 130, 16 ));
          
          lib.add(new CD("Who Let the Dogs Out", "Baha Men", "Pop", 17));
          lib.add(new CD("Puppy Love", "Paul Anka", "Pop", 18));
          lib.add(new CD("Dogs days are over", "Florence and the Machine", "Pop", 19));
          lib.add(new CD("Common people", "Jarvis Cockerspaniel", "Pop", 20));
          lib.add(new CD("It's raining dogs", "The Weather Girls", "Pop", 21));

          
          lib.add(new Dog("Jasper", "Border Terrier", 2, "meat", 123, "tan", 22));
          lib.add(new Dog("Liesl", "German Pointer", 4, "cheese", 124, "brown", 23));
          lib.add(new Dog("Rosy", "Labrador", 10, "sausages", 125, "black", 24));
          lib.add(new Dog("Bruce", "Bulldog", 5, "meat", 126, "white", 25));
          lib.add(new Dog("Truffle", "Cockapoo", 2, "cheese", 127, "brown", 26));
          lib.add(new Dog("Lucy", "Labradoodle", 7, "cheese", 128, "Golden", 27));
          lib.add(new Dog("Louis", "French bulldog", 4, "sausage", 128, "Blue", 28));


       }
       
    }


	
	@GET
	@Path("item/{id}")
	@Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
	public Item produceJSON( @PathParam("id") String id ) {
		System.out.println( "In produceJSON() for id= "+ id);
		initLib();
		Item ret = null;
		try{
			int num= Integer.parseInt(id);
			ret = lib.get(num -1);
			return ret;
		}
		catch(Exception e) {
			return ret;
		}
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
		catch (Exception swallow) {
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
		catch (Exception e) {
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
    @Path("showitems/{type}")
    @Produces( {MediaType.TEXT_PLAIN})
    public String showItems(@PathParam("type") String type) {
                   String items = "";
                   Iterator<Item> iterator = lib.iterator();
                   while (iterator.hasNext()) {
                                  Item row = iterator.next();
                                  if (row.getType().equalsIgnoreCase(type)) {
                                	  items += "<span class='mybold'>" + row.getName() + "</span>" + "-";
                                	  if (row.getType().equalsIgnoreCase("book")){
                                		  items+=((Book) row).getAuthor() + "<br/> <br/>";
                                	  }
                                  }
                   }
                   return items;
    }
	
	}
