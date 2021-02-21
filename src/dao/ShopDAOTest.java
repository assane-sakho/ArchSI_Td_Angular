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
        
        Optional<Category> cleUsbCategory = shopHighTech.getCategory("Cle-USB");
        Article cleUsbSandisk = shopHighTech.addArticle("clé usb c 32 - go", "Western Digital", 9.99, cleUsbCategory.get(), "https://shop.westerndigital.com/content/dam/store/en-us/assets/products/usb-flash-drives/ixpand-go-usb-3-0/gallery/iXpand-Flash-Drive-go-up-main.png.wdthumb.319.319.webp");
        
        /* Ajout article */
        System.out.println("\n\nAffichage des articles: \n");
        shopHighTech.getArticles().forEach(article ->  System.out.println(article));
        
        /* Modification article */
        cleUsbSandisk.setLibelle("Clé USB 16Go");
        shopHighTech.updateArticle(cleUsbSandisk);
        System.out.println("\n\nAffichage des articles: \n");
        shopHighTech.getArticles().forEach(article ->  System.out.println(article));
        
        /* Affichage des articles de la catégorie ordinateur */

        System.out.println("\n\nAffichage des articles de la catégorie Ordinateur: \n");
        shopHighTech.getArticlesByCategory("ordinateur").forEach(article ->  System.out.println(article));
        
        /* Affichage des articles de la catégorie clé usb */

        System.out.println("\n\nAffichage des articles de la catégorie clé usb: \n");
        shopHighTech.getArticlesByCategory(cleUsbCategory.get()).forEach(article ->  System.out.println(article));
        
        /* Suppression article */

        //shopHighTech.deleteArticle(cleUsbSandisk);
        shopHighTech.deleteArticle(1);
        System.out.println("\n\nAffichage des articles: \n");
        shopHighTech.getArticles().forEach(article ->  System.out.println(article));
    }
}