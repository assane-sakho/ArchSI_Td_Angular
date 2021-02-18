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
    
    @Path("{article}")
    public ArticleResource getArticle(@PathParam("article") Integer id) {
        return new ArticleResource(uriInfo, request, id);
    }
}

