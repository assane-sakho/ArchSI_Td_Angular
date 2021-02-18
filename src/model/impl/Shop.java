package model.impl;

import model.api.IShop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Shop extends ComponentImpl implements IShop {
    private final String description;
    private final String address;
    private final String contact;
    private final List<Article> articles;
    private final List<Category> categories;

    public Shop(int id, String libelle, String description, String address, String contact, List<Category> categories) {
        super(id, libelle);
        this.description = description;
        this.address = address;
        this.contact = contact;
        this.articles = new ArrayList<>();
        this.categories = categories;
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
