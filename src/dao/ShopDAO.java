package dao;

import model.impl.Shop;
import model.impl.Administrator;
import model.impl.Article;
import model.impl.Category;

import java.util.*;
import java.util.stream.Collectors;

public class ShopDAO {
    private static ShopDAO INSTANCE;
    private Shop shopHighTech;

    private ShopDAO()
 
    {
  
        Map<String, Optional<String>> categoriesMap = new HashMap<String, Optional<String>>() {
            {
                put("ordinateur", Optional.empty());
                put("stockage", Optional.empty());
                put("telephonie", Optional.empty());
                put("pc-portable", Optional.of("ordinateur"));
                put("pc-de-bureau", Optional.of("ordinateur"));
                put("accessoires-ordinateur", Optional.of("ordinateur"));
                put("smartphone", Optional.of("telephonie"));
                put("tel-fixe", Optional.of("telephonie"));
                put("accessoires-telephonie", Optional.of("telephonie"));
                put("disque-dur", Optional.of("stockage"));
                put("cle-usb", Optional.of("stockage"));
                put("accessoires-stockage", Optional.of("stockage"));
            }
        };
        
        List<Administrator> admins = new ArrayList<>();
        
        admins.add(new Administrator(1,"Brochado","Alexandre","test","test"));
        admins.add(new Administrator(2,"DAS","Rahul","test2","test2"));
        admins.add(new Administrator(3,"Mouzouri","Ilhame","test3","test3"));
        

        List<Category> categories = new ArrayList<>();
        int i = 0;

    	
        for (Map.Entry<String, Optional<String>> entry : categoriesMap.entrySet()) {
            Category c = new Category(i, entry.getKey());
            categories.add(c);
            i++;
        }
        
        List<String> subCategoriesStrList = categoriesMap.entrySet()
        											     .stream()
										        		 .filter(e -> e.getValue().isPresent())
										        	  	 .map(Map.Entry::getKey)
										        		 .collect(Collectors.toList());
        
        List<Category> subCategoriesList = categories.stream()
									        		 .filter(c -> subCategoriesStrList.contains(c.getLibelle()))
									        		 .collect(Collectors.toList());
        
           
        for (Category subCategory : subCategoriesList) {
        	String parentLibelle = categoriesMap.entrySet()
								       			 .stream()
								       			 .filter(e -> e.getKey() == subCategory.getLibelle())
								       		 	 .findFirst()
								       		  	 .get().getValue().get();
			
			Category parent = categories.stream()
						.filter(c -> c.getLibelle().equals(parentLibelle))
						.findFirst()
						.get();
			parent.addChild(subCategory);
			subCategory.setParent(parent);
        }

        shopHighTech = new Shop(0, "Boutique High tech", "une description", "06.01.02.14.57 - boutique-ht@gmail.com", "2 rue des boutiques - 75009 - Paris");
        shopHighTech.AddCategories(categories);
        shopHighTech.addAdmins(admins);
        Optional<Category> cleUsbCategory = shopHighTech.getCategory("cle-usb");
        Article cleUsbSandisk = new Article(0, "clé usb 16 - go", "Sandisk", 9.99, cleUsbCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/ee/6f/2f/3108846/1540-1/tsp20200718130428/Cle-USB-2-0-Sandisk-Cruzer-Blade-128-Go.jpg");
        shopHighTech.addArticle(cleUsbSandisk);
        Optional<Category> disquedurCategory = shopHighTech.getCategory("disque-dur");
        Article disquedur1 = new Article(0, "disque", "disque", 9.99, disquedurCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/ee/6f/2f/3108846/1540-1/tsp20200718130428/Cle-USB-2-0-Sandisk-Cruzer-Blade-128-Go.jpg");
        shopHighTech.addArticle(disquedur1);
    }

    public static ShopDAO getINSTANCE()
    {
        if(INSTANCE == null)
            INSTANCE = new ShopDAO();
        return INSTANCE;
    }

    public Shop getBoutique() {
        return shopHighTech;
    }
    
    /*public Map<String, Shop> getModel(){
        return shopHighTech;
    }*/
}
