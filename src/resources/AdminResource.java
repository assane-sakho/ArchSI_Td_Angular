package resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.POST;
import javax.ws.rs.FormParam;
import javax.ws.rs.Consumes;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.sun.javafx.collections.MappingChange.Map;

import dao.ShopDAO;
import model.impl.Administrator;
import model.impl.Article;
import model.impl.Shop;

@Path("/admin")
public class AdminResource {
	

	@POST
    @Produces(MediaType.APPLICATION_JSON)
	public boolean Connexion(String body) throws IOException {
		HashMap<String, String> map = (HashMap<String, String>) Arrays.asList(body.split("&"))
																		.stream()
																		.map(s -> s.split("="))
																		.collect(Collectors.toMap(e -> e[0], e -> e[1]));
		String login = map.get("login").toString();
		String mdp = map.get("mdp").toString();
		
		System.out.println(login);
		System.out.println(mdp);
		
		List<Administrator> admins = ShopDAO.getINSTANCE().getBoutique().getAdmins();
		
		if(admins.stream().filter(a -> (a.getLogin().equals(login)) && (a.getMdp().equals(mdp))).findFirst().isPresent()) {
			return true;
		}
		return false;
	}
}
