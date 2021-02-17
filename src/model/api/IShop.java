package model.api;

import model.impl.Article;
import model.impl.Category;

import java.util.List;

public interface IShop {
    IShop addArticle(Article article);
    IShop updateArticle(Article article);
    IShop deleteArticle(Article article);

    IShop addArticles(List<Article> articles);
    IShop deleteArticles(List<Article> articles);

    List<Article> getArticles();
    String getDescription();
    String getAddress();
    String getContact();

    List<Category> getMainCategories();
    List<Category> getSubCategories();
    
    List<Article> getArticlesByCategory(Category category);

}
