package model.impl;

import model.api.IShop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Shop extends ComponentImpl implements IShop {
    private String description;
    private String address;
    private String contact;
    private List<Article> articles;
    private List<Category> categories;
    private List<Administrator> admins;

    public Shop(int id, String libelle, String description, String address, String contact) {
        super(id, libelle);
        this.description = description;
        this.address = address;
        this.contact = contact;
        this.articles = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.admins = new ArrayList<>();
    }

    public Shop addArticles(List<Article> articles)
    {
        this.articles.addAll(articles);
        return this;
    }

    public Shop deleteArticles(List<Article> articles)
    {
        this.articles.removeAll(articles);
        return this;
    }

    public Shop addArticle(Article article)
    {
        this.articles.add(article);
        return this;
    }

    public Shop updateArticle(Article article)
    {
        Optional<Article> articleToEdit = this.articles.stream().filter(a -> a.getId().equals(article.getId())).findFirst();

        articleToEdit.ifPresent(value ->
                value.setLibelle(article.getLibelle())
                     .setBrand(article.getBrand())
                     .setPicture(article.getPicture())
                     .setCategorie(article.getCategory())
                     .setPrice(article.getPrice()));

        return this;
    }

    /*public Shop deleteArticle(Article article)
    {
        this.articles.remove(article);
        return this;
    }*/
    
    public Shop deleteArticle(Integer id)
    {
    	Optional<Article> article  = articles.stream().filter(a-> a.getId() == id).findFirst();
    	if(article.isPresent()) {
            this.articles.remove(article.get());
    	}
        return this;
    }

    public List<Article> getArticles() {
        return articles;
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
    	return categories.stream().filter(c -> c.getLibelle() == libelle).findFirst();
    }
    
    public Shop AddCategories(List<Category> categories) {
    	this.categories.addAll(categories);
    	return this;
    }
    
    public List<Article> getArticlesByCategory(Category category) {
        return getArticlesByCategory(category.getId());
    }
    
    public List<Article> getArticlesByCategory(Integer categoryId) {
    	List<Article> articlesFiltred = new ArrayList();
    	Optional<Category> c = getCategory(categoryId);
    	if(c.isPresent())
    	{
    		System.out.println(c.get().getLibelle());
    		articlesFiltred.addAll(articles.stream()
				    				.filter(article -> article.getCategory().getId() == categoryId || 
		    								   (article.getCategory().getParent().isPresent() && article.getCategory().getParent().get().getId() == categoryId ))
				    				.collect(Collectors.toList()));

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
				"- Catégories : " + getMainCategories().stream().map(Category::getLibelle).collect(Collectors.joining(", ")) + "\n" +
				"- Sous-catégories : " + getSubCategories().stream().map(Category::getLibelle).collect(Collectors.joining(", "))
    			;
    }
}
