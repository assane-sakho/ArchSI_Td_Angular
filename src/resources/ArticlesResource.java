package resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    
    
    /*
    
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
    */
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Article addArticleJSON(@Context HttpServletRequest request, InputStream requestBody) throws IOException, ServletException {
    	ShopDAO shopDAO = ShopDAO.getINSTANCE();
    	//System.out.println(requestBody);
    	 BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
         StringBuilder out = new StringBuilder();
         String line;
         while ((line = reader.readLine()) != null) {
        	 
             out.append(line);
         }
         System.out.println(out.toString());
         ObjectMapper mapper = new ObjectMapper();

         Map<String, String> map = new HashMap<String, String>();

         map = mapper.readValue(out.toString(), new TypeReference<Map<String, String>>(){});
         
         System.out.println(map.get("category"));
         
         String libelle = map.get("libelle");
         String brand = map.get("brand");
         Category category = shopDAO.getBoutique().getCategory(map.get("category")).get();
         
         
         
         String picture = map.get("picture");
         Double price = Double.valueOf(map.get("price"));
         

         
         Article article = shopDAO.getBoutique().addArticle(libelle, brand, price, category, picture);
         
         

         reader.close();
         
         return article;
    }
    
    
    
    @Path("{article}")
    public ArticleResource getTodo(@PathParam("article") Integer id) {
        return new ArticleResource(uriInfo, request, id);
    }
}

