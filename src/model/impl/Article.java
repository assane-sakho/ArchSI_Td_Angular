package model.impl;

public class Article extends ComponentImpl{
    private String brand;
    private Double price;
    protected Category category;
    private String picture;

    public Article(int id, String libelle, String brand, Double price, Category category, String picture) {
        super(id, libelle);
        this.libelle = libelle;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.picture = picture;
    }

    public Article setLibelle(String libelle)
    {
        this.libelle = libelle;
        return this;
    }

    public Article setBrand(String brand)
    {
        this.brand = brand;
        return this;
    }

    public Article setPrice(Double price)
    {
        this.price = price;
        return this;
    }

    public Article setPicture(String picture)
    {
        this.picture = picture;
        return this;
    }

    public Article setCategorie(Category category)
    {
        this.category = category;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Double getPrice() {
        return price;
    }

    public String getPicture() {
        return picture;
    }

    public Category getCategory() {
        return category;
    }
    
    @Override 
    public boolean equals(Object obj) { 
        if (!(obj instanceof Article) || obj == null)
    		return false; 
        if(((Article)obj).getId() == id)
        	return true; 
        return false;
    }
    
}
