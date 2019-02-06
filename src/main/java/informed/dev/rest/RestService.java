package informed.dev.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import informed.dev.Classes.*;

@Path("/items")
public class RestService {

	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getLibrary() {
		Library lib = new Library();
    	Book pnp = new Book("Pride and Prejudice", 1234, "Jane Austen", "Fiction" );
    	lib.addItem(pnp);
    	return lib.getItems();
    		
    }
}
