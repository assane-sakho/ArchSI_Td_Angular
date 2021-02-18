package model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

    
public class Shop extends ComponentImpl{
    private String description;
    private String address;
    private String contact;
    private List<Category> categories;
    private List<Administrator> admins;
    private int idArticle;

    public Shop(int id, String libelle, String description, String address, String contact) {
        super(id, libelle);
        this.description = description;
        this.address = address;
        this.contact = contact;
        this.categories = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.idArticle = this.getArticles().size();
    }

    public Shop addArticle(Article article)
    {
    	article.getCategory().addArticle(article);
    	idArticle++;
        return this;
    }

    public Shop updateArticle(Article article)
    {
    	article.getCategory().updateArticle(article);
        return this;
    }

    /*public Shop deleteArticle(Article article)
    {
        this.articles.add(article);
        return this;
    }*/
    
    public Shop deleteArticle(Integer id)
    {
    	Optional<Article> optionalArticle  = getArticles().stream().filter(article -> article.getId() == id).findFirst();
    	if(optionalArticle.isPresent()) {
    		Article article = optionalArticle.get();
    		Category category = article.getCategory();
    		category.deleteArticle(article);
    	}
        return this;
    }

    public List<Article> getArticles() {
        return categories.stream().flatMap(c -> c.getArticles().stream()).collect(Collectors.toList());
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }
    
    public int getIdArticle()
    {
    	return this.idArticle;
    }

    public List<Category> getMainCategories()
    {
        return categories.stream().filter(c -> c.getChildren().stream().count() > 0).collect(Collectors.toList());
    }

    public List<Category> getSubCategories()
    {
        return categories.stream().filter(c -> c.getChildren().stream().count() == 0).collect(Collectors.toList());
    }
    
    public Optional<Category> getCategory(Integer categoryId)
    {
    	return categories.stream().filter(c -> c.getId() == categoryId).findFirst();
    }
    
    public Optional<Category> getCategory(String libelle)
    {
    	return categories.stream().filter(c -> c.getLibelle().toLowerCase().equals(libelle.toLowerCase())).findFirst();
    }
    
    public Shop AddCategories(List<Category> categories) {
    	this.categories.addAll(categories);
    	return this;
    }
    
    public List<Article> getArticlesByCategory(Category category) {
        return getArticlesByCategory(category.getLibelle());
    }
    
    public List<Article> getArticlesByCategory(String libelle) {
    	List<Article> articlesFiltred = new ArrayList();
    	
    	System.out.println(libelle);
    	
    	Optional<Category> optionalCategory = getCategory(libelle);
    	
    	if(optionalCategory.isPresent())
    	{
    		Category category = optionalCategory.get();

    		articlesFiltred = category.getArticles();
    	}
    	
        return articlesFiltred;
    }
    
    public Shop addAdmins(List<Administrator> admins)
    {
        this.admins.addAll(admins);
        return this;
    }

    public Shop deleteAdmins(List<Administrator> admins)
    {
        this.admins.removeAll(admins);
        return this;
    }

    public Shop addAdmin(Administrator admin)
    {
        this.admins.add(admin);
        return this;
    }

    public Shop updateAdmin(Administrator admin)
    {
        Optional<Administrator> adminToEdit = this.admins.stream().filter(a -> a.getId() == admin.getId()).findFirst();

        adminToEdit.ifPresent(value ->
                value.setFirstname(admin.getFirstname())
                     .setLastname(admin.getLastname())
                     .setLogin(admin.getLogin())
                     .setMdp(admin.getMdp()));
                     
        return this;
    }

    public Shop deleteAdmin(Administrator admin)
    {
        this.admins.remove(admin);
        return this;
    }

    public List<Administrator> getAdmins() {
        return admins;
    }
    
    @Override
    public String toString()
    {
    	return libelle + "\n" +
				"- Decription : " + description  + "\n" +
				"- Adresse : " + address  + "\n" +
    			"- Contact : " + contact  + "\n" +
				"- Cat�gories : " + getMainCategories().stream().map(Category::getLibelle).collect(Collectors.joining(", ")) + "\n" +
				"- Sous-cat�gories : " + getSubCategories().stream().map(Category::getLibelle).collect(Collectors.joining(", "))
    			;
    }
}
