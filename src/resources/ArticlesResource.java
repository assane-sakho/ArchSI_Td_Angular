package resources;

import java.util.ArrayList;
import java.util.List;

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


@Path("/articles")
public class ArticlesResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    // Return the list of the articles from a category
    @Path("/category/{category}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Article> getArticlesByCategory(@PathParam("category") Integer categoryId) {
    	
        ShopDAO shopDAO = ShopDAO.getINSTANCE();
        Shop shopHighTech = shopDAO.getBoutique();
        
        System.out.println(shopHighTech.getArticlesByCategory(categoryId));

        return  shopHighTech.getArticlesByCategory(categoryId);
    }
    
    @Path("{article}")
    public ArticleResource getArticle(@PathParam("article") Integer id) {
        return new ArticleResource(uriInfo, request, id);
    }
}
