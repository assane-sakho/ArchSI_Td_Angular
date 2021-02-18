package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import dao.ShopDAO;
import model.impl.Article;
import model.impl.Category;
import model.impl.Shop;


@Path("/articles")
public class ArticlesResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    @Path("{article}")
    @Produces(MediaType.TEXT_HTML)
    public ArticleResource getArticle(@PathParam("article") Integer id) {
        return new ArticleResource(uriInfo, request, id);
    }
    
    
    
    
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newArticle(@FormParam("libelle") String libelle, @FormParam("brand") String brand, @FormParam("price") Double price, @FormParam("category") Integer category, @FormParam("picture") String picture, @Context HttpServletResponse servletResponse) throws IOException {
    	
    	
    	ShopDAO shopDAO = ShopDAO.getINSTANCE();
        Shop shopHighTech = shopDAO.getBoutique();
    	Optional<Category> optionalCategory = shopHighTech.getCategory(category);
    	
    	
    	if(optionalCategory.isPresent())
    	{
    		
    		Article article = new Article(shopHighTech.getIdArticle() + 1, libelle, brand, price, optionalCategory.get(), picture);
            
            ShopDAO.getINSTANCE().getBoutique().addArticle(article);
            
            //redirection vers listes des articles.
            servletResponse.sendRedirect("../admin.html");
           
    	}
    	else
    	{
    		//System.out.println("Catégorie inexistant");
    		servletResponse.sendRedirect("../AjoutArticle.html");
    	}
    	
        
    }
}

