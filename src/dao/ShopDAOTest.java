package dao;

import java.util.List;
import java.util.Optional;

import model.impl.Article;
import model.impl.Category;
import model.impl.Shop;

class ShopDAOTest {
    public static void main(String[] args) {
        ShopDAO shopDAO = ShopDAO.getINSTANCE();
        Shop shopHighTech = shopDAO.getBoutique();

        System.out.println(shopHighTech);
        
        Optional<Category> cleUsb = shopHighTech.getCategory("Clé USB");
        Article cleUSb = new Article(0, "Clé usb 16Go", "Sandisk", 9.99, cleUsb.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/ee/6f/2f/3108846/1540-1/tsp20200718130428/Cle-USB-2-0-Sandisk-Cruzer-Blade-128-Go.jpg");
        
        shopHighTech.addArticle(cleUSb);
        System.out.println("Affichage des articles: \n");
        shopHighTech.getArticles().forEach(article ->  System.out.println(article));
        
        cleUSb.setLibelle("Clé USB 16Go");
        shopHighTech.updateArticle(cleUSb);
        System.out.println("Affichage des articles: \n");
        shopHighTech.getArticles().forEach(article ->  System.out.println(article));
        
        shopHighTech.deleteArticle(cleUSb);
        System.out.println("Affichage des articles: \n");
        shopHighTech.getArticles().forEach(article ->  System.out.println(article));
    }
}