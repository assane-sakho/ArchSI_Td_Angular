package model.impl;

import model.api.IComposite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Category extends ComponentImpl implements IComposite<Category> {
    private Optional<Category> categorieParent;
    private List<Category> sousCategories;
    private List<Article> articles;

    public Category(int id, String libelle) {
        super(id, libelle);
        this.categorieParent = Optional.empty();
        this.sousCategories = new ArrayList<>();
        this.articles = new ArrayList<>();
    }

    public Category(int id, String libelle, Optional<Category> categorieParent) {
        super(id, libelle);
        this.categorieParent = categorieParent;
        this.sousCategories = new ArrayList<>();
        this.articles = new ArrayList<>();
    }

    public Category(int id, String libelle, List<Category> sousCategories) {
        super(id, libelle);
        this.categorieParent = Optional.empty();
        this.sousCategories = sousCategories;
        this.articles = new ArrayList<>();
    }

    public Category(int id, String libelle, Optional<Category> categorieParent, List<Category> sousCategories) {
        super(id, libelle);
        this.categorieParent = categorieParent;
        this.sousCategories = sousCategories;
        this.articles = new ArrayList<>();
    }

    public void setParent(Category category)
    {
    	categorieParent = Optional.of(category);
    }

    @Override
    public Optional<Category> getParent() {
        return categorieParent;
    }

    @Override
    public List<Category> getChildren() {
        return sousCategories;
    }

    @Override
    public void addChildren(List<Category> children) {
        for(Category c : children)
        	addChild(c);
    }

    @Override
    public void addChild(Category child) {
    	if(!sousCategories.stream().filter(c -> c.getId() == child.getId()).findAny().isPresent())
        	sousCategories.add(child);
    }
    
    @Override
    public String toString()
    {
    	String p = categorieParent.isPresent() ? " (parent "+ categorieParent.toString()  + ")": "";
    	return libelle +  p;			
    }
    
    public void addArticle(Article article)
    {
    	articles.add(article);
    }
    
    public void updateArticle(Article article)
    {
        Optional<Article> articleToEdit = this.articles.stream().filter(a -> a.getId().equals(article.getId())).findFirst();

        articleToEdit.ifPresent(value ->
                value.setLibelle(article.getLibelle())
                     .setBrand(article.getBrand())
                     .setPicture(article.getPicture())
                     .setCategorie(article.getCategory())
                     .setPrice(article.getPrice()));

    }

    public void deleteArticle(Article article)
    {
    	articles.remove(article);
    }
    
    public List<Article> getSubArticles()
    {
    	List<Article> allArticles = new ArrayList();
	    
    	for(Category subCategory : sousCategories)
    	{
    		allArticles.addAll(subCategory.getArticles()); 	
    	}
    	return allArticles;
    }
    
    public List<Article> getArticles()
    {
    	List<Article> result = new ArrayList();
    	result.addAll(articles);
    	result.addAll(getSubArticles());
    	return result;
    }
    
}
