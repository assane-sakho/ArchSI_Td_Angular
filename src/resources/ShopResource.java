package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import dao.ShopDAO;
import model.impl.Article;
import model.impl.Shop;

@Path("/shop")
public class ShopResource {
	 @Context
	 UriInfo uriInfo;
	 
	 @Context
	 Request request;
	    
	 @Path("/infos")
	 @GET
	 @Produces({ MediaType.TEXT_HTML })
	 public String getInfoShop() {
		 	ShopDAO shopDAO = ShopDAO.getINSTANCE();
		 	Shop shopHighTech = shopDAO.getBoutique();
		 	List<String> infoShop=new ArrayList<>();
		 	infoShop.add(shopHighTech.getDescription());
		 	infoShop.add(shopHighTech.getAddress());
		 	infoShop.add(shopHighTech.getContact());
		 	return infoShop.stream().collect(Collectors.joining(", "));
	 }
}
