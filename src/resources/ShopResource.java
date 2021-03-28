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
    public List<Article> getArticlesByCategory(@PathParam("category") String categoryLibelle) {
    	
        ShopDAO shopDAO = ShopDAO.getINSTANCE();
        Shop shopHighTech = shopDAO.getBoutique();

        List<Article> articles = shopHighTech.getArticlesByCategory(categoryLibelle);

        return articles;
    }
    
    @Path("/{category}/articles")
    @GET
    @Produces({ MediaType.TEXT_HTML })
    public String getArticlesByCategory2(@PathParam("category") String categoryLibelle) {
    	
    	String baseArticle = 
	    			"<div class=\"card h-100\">\r\n" + 
	    			"    <a href=\"#\"><img class=\"card-img-top articleImage\" src=\"#articlePicture\" alt=\"\" width=\"450\" height=\"400\"></a>\r\n" + 
	    			"  <input id=\"articleId\" value=\"#articleId\"/>\r\n" + 
	    			" <div class=\"card-body\">\r\n" + 
	    			"    <h4 class=\"card-title\">\r\n" + 
	    			"        <a href=\"\\ArchSI_Td_Angular\\rest\\articles\\#articleId\" class=\"articleName\">#articleName</a>\r\n" + 
	    			"    </h4>\r\n" + 
	    			"    <h5 class=\"articlePrice\">#articlePrice</h5>\r\n" + 
	    			"    <p class=\"card-text articleCategory\">#articleCategory</p>\r\n" + 
	    			"        <button class=\"btn btn-info\">Modifier</button>\r\n" + 
	    			"        <button class=\"btn btn-primary\">Supprimer</button>\r\n" + 
	    			"    </div>\r\n" + 
    			"</div>";
        ShopDAO shopDAO = ShopDAO.getINSTANCE();
        Shop shopHighTech = shopDAO.getBoutique();

        List<Article> articles = shopHighTech.getArticlesByCategory(categoryLibelle);
        String result = "";
        for(Article arcticle : articles)
        {
        	String tmp = baseArticle;
        	tmp = tmp.replace("#articleId", "" + arcticle.getId())
        			.replace("#articlePicture", arcticle.getPicture())
        			.replace("#articleName", arcticle.getLibelle())
        			.replace("#articlePrice", "" + arcticle.getPrice())
        			.replace("#articleCategory", arcticle.getCategory().getLibelle());
        	result +=tmp;
        }
        
        System.out.println(result);

        return result;
    }
}
