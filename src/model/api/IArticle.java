package model.api;

import model.impl.Article;
import model.impl.Category;

public interface IArticle {
	Article setLibelle(String libelle);
	Article setBrand(String brand);
	Article setPrice(Double price);
	Article setPicture(String picture);
	Article setCategorie(Category category);
	String getBrand();
	Double getPrice();
	String getPicture();
	Category getCategory();
}
