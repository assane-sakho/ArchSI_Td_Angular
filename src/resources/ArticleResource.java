package resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Article updateArticle(@Context HttpServletRequest request, InputStream requestBody) throws IOException, ServletException
    {
    	ShopDAO shopDAO = ShopDAO.getINSTANCE();
    	Shop shopHighTech = shopDAO.getBoutique();
    	
    	Article articleToEdit = shopHighTech.getArticleById(id).get();
    	
    	//System.out.println(articleToEdit.getId());
    	
    	
    	if(articleToEdit == null)
    	{
    		System.out.println("Article non trouvé");
    		throw new RuntimeException("Update: Article avec " + id +  " non trouve");
    	}
    	else
    	{
    		//System.out.println("article trouvé : " + id);
    		
    		BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
           	 
                out.append(line);
            }
            //System.out.println(out.toString());
            
            ObjectMapper mapper = new ObjectMapper();

            Map<String, String> map = new HashMap<String, String>();

            map = mapper.readValue(out.toString(), new TypeReference<Map<String, String>>(){});
            
            if(map.get("libelle") != "") {
            	articleToEdit.setLibelle(map.get("libelle"));
            }
            if(map.get("brand") != "")
            {
            	articleToEdit.setBrand(map.get("brand"));
            }
            if(map.get("picture") != "")
            {
            	articleToEdit.setPicture(map.get("picture"));
            }
            if(map.get("price") != "")
            {
            	articleToEdit.setPrice(Double.valueOf(map.get("price")));
            }
            if(map.get("category") != "")
            {
            	articleToEdit.setCategorie(shopDAO.getBoutique().getCategory(map.get("category")).get());
            }
            
            
           /*
            System.out.println(articleToEdit.getId());
            System.out.println(articleToEdit.getLibelle());
            System.out.println(articleToEdit.getBrand());
            System.out.println(articleToEdit.getPicture());
            System.out.println(articleToEdit.getPrice());
            System.out.println(articleToEdit.getCategory());
            */
            
            return articleToEdit;
    		
    	}
    	
    }
    
    
}
