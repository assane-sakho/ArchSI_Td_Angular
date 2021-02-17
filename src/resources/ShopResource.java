package resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

public class ShopResource {
	 @Context
	 UriInfo uriInfo;
	 
	 @Context
	 Request request;
}
