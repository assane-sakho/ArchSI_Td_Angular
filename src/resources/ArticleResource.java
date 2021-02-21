package resources;

import java.io.IOException;
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
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Article getArticle() {
        Article article = ShopDAO.getINSTANCE().getBoutique().getArticles().get(id);
        if(article==null)
            throw new RuntimeException("Get: article with " + id +  " not found");
        return article;
    }

    // for the browser
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getArticleHTML() {
        Article article = ShopDAO.getINSTANCE().getBoutique().getArticles().stream().filter(a -> a.getId() == id).findFirst().get();
      
    	String result = 
    			"<p>Detail de l'article : </p>" + 
    			"<div class=\"card h-100\">\r\n" + 
    			"    <a href=\"#\"><img class=\"card-img-top articleImage\" src=\"" + article.getPicture() + "\" alt=\"\" width=\"450\" height=\"400\"></a>\r\n" + 
    			"    <div class=\"card-body\">\r\n" + 
    			"    <h4 class=\"card-title\">\r\n" + 
    			"        <a href=\"#\" class=\"articleName\">" + article.getLibelle() + "</a>\r\n" + 
    			"    </h4>\r\n" + 
    			"    <h5 class=\"articlePrice\">" + article.getPrice() + "</h5>\r\n" + 
    			"    <p class=\"card-text articleCategory\">" +  article.getCategory().getLibelle() + "</p>\r\n" + 
    			"    <p>" + article.getId() + "</p>" +
    			"        <button class=\"btn btn-info\">Modifier</button>\r\n" + 
    			"        <button class=\"btn btn-primary\">Supprimer</button>\r\n" + 
    			"    </div>\r\n" + 
			"</div>";

        return result;
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
