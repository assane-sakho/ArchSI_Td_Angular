package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    
    
    
    //ADD ARTICLE
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newArticle(@FormParam("libelle") String libelle, 
    		@FormParam("brand") String brand, 
    		@FormParam("price") Double price, 
    		@FormParam("category") String category, 
    		@FormParam("picture") String picture, 
    		@Context HttpServletRequest request, 
    		@Context HttpServletResponse servletResponse) throws IOException, ServletException {
    	
    	
    	ShopDAO shopDAO = ShopDAO.getINSTANCE();
        Shop shopHighTech = shopDAO.getBoutique();
    	Optional<Category> optionalCategory = shopHighTech.getCategory(category);
    	
    	
    	if(optionalCategory.isPresent())
    	{
    		
    		Article article = new Article(shopHighTech.getIdArticle(), libelle, brand, price, optionalCategory.get(), picture);
    		
    		//System.out.println("avant : " + shopHighTech.getIdArticle());
            
            ShopDAO.getINSTANCE().getBoutique().addArticle(article);
            
            //System.out.println("apres : " + shopHighTech.getIdArticle());
            
            //redirection vers listes des articles.
            servletResponse.sendRedirect("../admin.html");
           
    	}
    	else
    	{
    		//System.out.println("Catégorie inexistant");
    		String msg = "Catégorie inexistant";
    		request.setAttribute("msg", msg);
    		request.getRequestDispatcher("../AjoutArticle.html").forward(request, servletResponse);
    		//servletResponse.sendRedirect("../AjoutArticle.html");
    		
    	}
    }
    
    
    
    @Path("{article}")
    public ArticleResource getTodo(@PathParam("article") Integer id) {
        return new ArticleResource(uriInfo, request, id);
    }
}

