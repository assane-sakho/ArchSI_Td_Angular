package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.FormParam;
import javax.ws.rs.Consumes;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
	
	 
	@POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newConnexion(@FormParam("login") String login,
            @FormParam("mdp") String mdp,
            @Context HttpServletResponse servletResponse) throws IOException {
		if(ShopDAO.getINSTANCE().getBoutique().getAdmins().stream().filter(a -> (a.getLogin().equals(login)) && (a.getMdp().equals(mdp))).findFirst().isPresent()) {
			servletResponse.sendRedirect("../admin.html");
		}else {
			servletResponse.sendRedirect("../connection.html");
		}

    }
	
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
	 @Context
	 Request request;
	 
	 // Return the list of the articles from a category
    @Path("/{category}/articles")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public String getArticlesByCategory(@PathParam("category") String categoryLibelle) {
    	
        ShopDAO shopDAO = ShopDAO.getINSTANCE();
        Shop shopHighTech = shopDAO.getBoutique();

        List<Article> articles = shopHighTech.getArticlesByCategory(categoryLibelle);

        return "[" +  articles.stream().map(article -> article.toString()).collect(Collectors.joining(",")) + "]";
    }
}
