package resources;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import dao.ShopDAO;
import model.impl.Article;
import model.impl.Category;
import model.impl.Shop;


public class ArticleResource {
	 @Context
	 UriInfo uriInfo;
	 
	 @Context
	 Request request;
	 Integer id;
	 
    public ArticleResource(UriInfo uriInfo, Request request, Integer id) {
        this.id = id;
    	this.uriInfo = uriInfo;
        this.request = request;
    }
    
    //Application integration
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Article getArticle() {
    	List<Article> articles = ShopDAO.getINSTANCE().getBoutique().getArticles();
    	
        Optional<Article> oArticle = articles.stream().filter(a -> a.getId() == id).findFirst();
        
        if(!oArticle.isPresent())
            throw new RuntimeException("Get: article with " + id +  " not found");
        
        return oArticle.get();
    }

    @DELETE
    public void deleteArticle() {
    	//categoryTelephonie/articles/1
        ShopDAO shopDAO = ShopDAO.getINSTANCE();
        Shop shopHighTech = shopDAO.getBoutique().deleteArticle(id);
        System.out.println("Delete shop size="+shopHighTech.getArticles().size());

        if(shopHighTech==null)
            throw new RuntimeException("Delete: Article avec " + id +  " non trouve");
    }
    
    
//Modifier un article
    
    @Path("/{id}")
    @PUT
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateArticle()
    {
    	ShopDAO shopDAO = ShopDAO.getINSTANCE();
    	Shop shopHighTech = shopDAO.getBoutique();
    	
    	Article articleToEdit = shopHighTech.getArticleById(id).get();
    	if(articleToEdit == null)
    	{
    		throw new RuntimeException("Update: Article avec " + id +  " non trouve");
    	}
    	else
    	{
    		//shopHighTech.updateArticle(article);
    		articleToEdit.setLibelle("s");
    		articleToEdit.setBrand("s");
    		Optional<Category> optionalCategory = shopHighTech.getCategory("disque-dur");
    		articleToEdit.setCategorie(optionalCategory.get());
    		articleToEdit.setPicture("s");
    		articleToEdit.setPrice(9.0);
    		
    		System.out.println("Update article " + articleToEdit.getId() + "from shop success");
    	}
    	
    }
    
    
}
