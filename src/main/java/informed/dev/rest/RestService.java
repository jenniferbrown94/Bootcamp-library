package informed.dev.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import informed.dev.Classes.*;

@Path("/msg")
public class RestService {
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String createPerson() {
    	Person jenny = new Person("Jenny", 24, 1234);
    	return jenny.toString();
    	
    }
}
