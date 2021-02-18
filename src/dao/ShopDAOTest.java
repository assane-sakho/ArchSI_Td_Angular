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
        
        Optional<Category> cleUsbCategory = shopHighTech.getCategory("Cl�-USB");
        Article cleUsbSandisk = new Article(0, "cl� usb 16 - go", "Sandisk", 9.99, cleUsbCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/ee/6f/2f/3108846/1540-1/tsp20200718130428/Cle-USB-2-0-Sandisk-Cruzer-Blade-128-Go.jpg");
        
        /* Ajout article */
        shopHighTech.addArticle(cleUsbSandisk);
        System.out.println("\n\nAffichage des articles: \n");
        shopHighTech.getArticles().forEach(article ->  System.out.println(article));
        
        /* Modification article */
        cleUsbSandisk.setLibelle("Cl� USB 16Go");
        shopHighTech.updateArticle(cleUsbSandisk);
        System.out.println("Affichage des articles: \n");
        shopHighTech.getArticles().forEach(article ->  System.out.println(article));
        
        /* Affichage des articles de la cat�gorie ordinateur */

        System.out.println("Affichage des articles de la cat�gorie Ordinateur: \n");
        shopHighTech.getArticlesByCategory("ordinateur").forEach(article ->  System.out.println(article));
        
        /* Affichage des articles de la cat�gorie cl� usb */

        System.out.println("Affichage des articles de la cat�gorie cl� usb: \n");
        shopHighTech.getArticlesByCategory(cleUsbCategory.get()).forEach(article ->  System.out.println(article));
        
        /* Suppression article */

        //shopHighTech.deleteArticle(cleUsbSandisk);
        shopHighTech.deleteArticle(1);
        System.out.println("Affichage des articles: \n");
        shopHighTech.getArticles().forEach(article ->  System.out.println(article));
    }
}