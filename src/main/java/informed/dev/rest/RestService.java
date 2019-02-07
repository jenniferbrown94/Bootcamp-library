package informed.dev.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


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
		}
		if (customers == null ) {
			customers = new ArrayList<Person>();
			Person hope = new Person("Hope Bristow", 24, 1);
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
		catch (Exception ex) {
			return "Error getting item name with id: " + id + " : " + ex;
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
}
