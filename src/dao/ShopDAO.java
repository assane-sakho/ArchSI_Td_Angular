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
        
        admins.add(new Administrator(1,"Brochado","Alexandre","alexandre.brochado@htstore.com","adminadmin"));
        admins.add(new Administrator(2,"DAS","Rahul","rahul.das@htstore.com","adminadmin"));
        admins.add(new Administrator(3,"Mouzouri","Ilhame","ilhame.mouzouri@htstore.com","adminadmin"));
        admins.add(new Administrator(3,"Sakho","Assane","assane.sakho@htstore.com","adminadmin"));
        

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
        shopHighTech.addArticle("Clé USB SanDisk Ultra 3.0, 64 Go", "Sandisk", 14.99, cleUsbCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/NR/02/59/54/5527810/1540-1/tsp20131204130106/Cle-USB-SanDisk-Ultra-3-0-64-Go.jpg");
        shopHighTech.addArticle("Clé USB 2.0 Emtec Color Mix C410, 16 Go", "Emtec Basf", 9.99, cleUsbCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/NR/16/33/53/5452566/1540-1/tsp20130924180014/Cle-USB-2-0-Emtec-Color-Mix-C410-16-Go.jpg");
        shopHighTech.addArticle("Clé USB Philips Snow 2.0 16 Go Blanc et Bleu", "Philips", 12.99, cleUsbCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/08/88/e6/15108104/1540-1/tsp20200603113331/Cle-USB-Philips-Snow-2-0-16-Go-Blanc-et-Bleu.jpg");
        shopHighTech.addArticle("Clé USB 3.1 HP X796W 128 Go en métal -Or", "HP", 18.39, cleUsbCategory.get(), "https://static.fnac-static.com/multimedia/Images/08/08/70/ED/15560712-1505-1540-1/tsp20200916183610/Cle-USB-3-1-HP-X796W-128-Go-en-metal-Or-champagne.jpg#da28eac6-0130-44d5-98be-293facb26070");

        Optional<Category> disqueDurCategory = shopHighTech.getCategory("disque-dur");
        shopHighTech.addArticle("Disque dur externe Toshiba Canvio Basics 1 To Noir", "Toshiba", 50.99, disqueDurCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/85/52/7b/8082053/1540-1/tsp20210325181841/Disque-dur-externe-Toshiba-Canvio-Basics-1-To-Noir.jpg");
        shopHighTech.addArticle("Disque SSD Externe SanDisk Extreme Portable 1 To", "SanDisk", 139.99, disqueDurCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/a7/4e/83/8605351/1540-1/tsp20210325132858/Disque-D-Externe-SanDisk-Extreme-Portable-1-To.jpg");
        shopHighTech.addArticle("Disque Dur externe Seagate Expansion portable 5 To Edition Spéciale Fnac Noir", "Seagate", 109.99, disqueDurCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/25/01/bd/12386597/1541-2/tsp20210325181059/Disque-Dur-externe-Seagate-Expansion-portable-5-To-Edition-Speciale-Fnac-Noir.jpg");
        shopHighTech.addArticle("Disque dur interne Seagate IronWolf ST4000VNA008 4 To Argent", "Seagate", 149.99, disqueDurCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/b7/3e/d6/14040759/1540-1/tsp20210325131702/Disque-dur-interne-Seagate-IronWolf-ST4000VNA008-4-To-Argent.jpg");
               
        Optional<Category> accessoiresStockageCategory = shopHighTech.getCategory("accessoires-stockage");
        shopHighTech.addArticle("USB 3.0 à SATA - 22 Broches 2.5 Pouces HDD Externe", "VSHOP", 5.15, accessoiresStockageCategory.get(), "https://static.fnac-static.com/multimedia/Images/A0/A0/63/A9/11101088-3-1520-2/tsp20200130224934/USB-3-0-a-SATA-22-Broches-2-5-Pouces-HDD-Externe-D-Disque-dur-Connecteur-Adaptateur-Cable-SATA-Haute-Vitee-AC986.jpg");
        shopHighTech.addArticle("Adaptateur de Montage 2 x 2.5SSD/HDD + 1 x 3.5HDD. Support de Disque Dur pour baie 5,25", "VSHOP", 10.09, accessoiresStockageCategory.get(), "https://static.fnac-static.com/multimedia/Images/24/24/84/B1/11633700-1505-1540-1/tsp20200131010456/VSHOP-Adaptateur-de-Montage-2-x-2-5D-HDD-1-x-3-5HDD-Support-de-Disque-Dur-pour-baie-5-25-pour-PC.jpg#f51eee86-4fdb-4de2-8a09-e0f31817437d");
        shopHighTech.addArticle("Support de montage pour 2 disques durs de 2,5\" dans baie de 3,5", "VSHOP", 109.99, accessoiresStockageCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MC/03/8f/46/21401347/1540-1/tsp20170214160155/StarTech-com-Support-de-montage-pour-2-disques-durs-de-2-5-dans-baie-de-3-5-Adaptateur-de-montage-2x-HDD-D-SATA-adaptateur-pour-baie-de-stockage.jpg#9babbf96-cb7d-4033-8a88-d6d937a0fd09");
        
        Optional<Category> pcPortableCategory = shopHighTech.getCategory("pc-portable");
        shopHighTech.addArticle("PC Portable Gaming HP OMEN 15 15-ek0015nf 15,6\" Intel Core i7 16 Go RAM 512 Go SSD Noir céleste", "HP", 995.99, pcPortableCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/2e/76/e3/14906926/1540-1/tsp20201116103334/PC-Portable-Gaming-HP-OMEN-15-15-ek0015nf-15-6-Intel-Core-i7-16-Go-RAM-512-Go-D-Noir-celeste.jpg");
        shopHighTech.addArticle("PC Portable Gaming Lenovo Legion 7 15IMH05 15.6\" Intel Core i9 32 Go RAM 2 To SSD Gris", "Seagate", 1154.99, pcPortableCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/6e/42/e7/15155822/1540-1/tsp20210322081037/PC-Portable-Gaming-Lenovo-Legion-7-15IMH05-15-6-Intel-Core-i9-32-Go-RAM-2-To-D-Gris.jpg");
        shopHighTech.addArticle("PC Ultra-Portable Asus UX434FL-A5459T 14.0\" Intel Core i7 8 Go 512 Go SSD Bleu royal", "Asus", 889.35, pcPortableCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/ed/6b/d3/13855725/1520-1/tsp20210222182136/PC-Ultra-Portable-Asus-UX434FL-A5459T-14-0-Intel-Core-i7-8-Go-512-Go-D-Bleu-royal.jpg");
        shopHighTech.addArticle("PC Ultra-Portable Acer CB314-1HT-C6UF Ecran tactile 14\" Intel Celeron 8 Go RAM 64 Go eMMC Gris argent", "Acer", 359.30, pcPortableCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/34/91/fb/16486708/1541-1/tsp20210317081821/PC-Ultra-Portable-Acer-CB314-1HT-C6UF-Ecran-tactile-14-Intel-Celeron-8-Go-RAM-64-Go-eMMC-Gris-argent.jpg");
        shopHighTech.addArticle("PC Hybride Microsoft Surface Go 2 10,5’’ Tactile Intel Pentium Gold 4Go RAM 64Go eMMC Platine", "Microsoft", 479.99, pcPortableCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/81/04/e4/14943361/1541-4/tsp20210129174725/PC-Hybride-Microsoft-Surface-Go-2-10-5-Tactile-Intel-Pentium-Gold-4Go-RAM-64Go-eMMC-Platine.jpg");
        shopHighTech.addArticle("PC Portable Lenovo IdeaPad 3 15ADA05 15,6\" AMD Ryzen 5 8 Go RAM 128 Go SSD + 1 To SATA Gris platine", "Lenovo", 599.99, pcPortableCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/68/42/e7/15155816/1540-1/tsp20210225094437/PC-Portable-Lenovo-IdeaPad-3-15ADA05-15-6-AMD-Ryzen-5-8-Go-RAM-128-Go-D-1-To-SATA-Gris-platine.jpg");
        shopHighTech.addArticle("PC Portable Gaming Dell G3 15-3500 15.6\" Intel Core i5 8 Go RAM 256 Go SSD Noir éclipse", "Dell", 799.99, pcPortableCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/51/8d/eb/15437137/1540-1/tsp20210125170241/PC-Portable-Gaming-Dell-G3-15-3500-15-6-Intel-Core-i5-8-Go-RAM-256-Go-D-Noir-eclipse.jpg");
        shopHighTech.addArticle("PC Portable Gaming Millenium ML3 15,6\" Intel Core i7 16 Go RAM 512 Go SSD Noir", "Millenium", 1199.99, pcPortableCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/e6/c7/d5/14010342/1540-1/tsp20200717171012/PC-Portable-Gaming-Millenium-ML3-15-6-Intel-Core-i7-16-Go-RAM-512-Go-D-Noir.jpg");

        Optional<Category> pcBureauCategory = shopHighTech.getCategory("pc-de-bureau");
        shopHighTech.addArticle("Apple Mac Mini 512 Go SSD 16 Go RAM Puce M1 Nouveau", "Dell", 1259.99, pcBureauCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/34/24/e2/14820404/1540-1/tsp20210112172219/Apple-Mac-Mini-512-Go-D-16-Go-RAM-Puce-M1-Nouveau.jpg");
        shopHighTech.addArticle("PC Asus ExpertCenter D5 SFF D500SA 710700027R Intel Core i7 16 Go RAM 512 Go SSD Noir", "Asus", 929.99, pcBureauCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/a7/72/f2/15889063/1540-1/tsp20210311080923/PC-Asus-ExpertCenter-D5-SFF-D500SA-710700027R-Intel-Core-i7-16-Go-RAM-512-Go-D-Noir.jpg");
        shopHighTech.addArticle("PC HP Slim Desktop S01-aF0033nf Intel Pentium 4 Go RAM 1 To SATA Noir ébène", "HP", 399.99, pcBureauCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/f9/0b/ef/15666169/1540-1/tsp20210324114629/PC-HP-Slim-Desktop-S01-aF0033nf-Intel-Pentium-4-Go-RAM-1-To-SATA-Noir-ebene.jpg");
        shopHighTech.addArticle("PC Gaming MSI MEG Infinite X 10TD-883FR Intel Core i7 16 Go RAM 1 To SSD Noir", "Msi", 2201.99, pcBureauCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/24/a2/f2/15901220/1540-1/tsp20210311155043/PC-Gaming-MSI-MEG-Infinite-X-10TD-883FR-Intel-Core-i7-16-Go-RAM-1-To-D-Noir.jpg");
        shopHighTech.addArticle("PC Lenovo IdeaCentre 3 07ADA05 AMD Athlon 4 Go RAM 128 Go SSD + 1 To SATA Gris", "Lenovo", 389.99, pcBureauCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/b2/0f/f1/15798194/1540-1.jpg");
         
        Optional<Category> accessoiresOrdinateurCategory = shopHighTech.getCategory("accessoires-ordinateur");
        shopHighTech.addArticle("Support pour PC Portable ergonomique Port Designs Gris", "Port Designs", 24.99, accessoiresOrdinateurCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/0f/74/ef/15692815/1540-1/tsp20210308111212/Support-pour-PC-Portable-ergonomique-Port-Designs-Gris.jpg");
        shopHighTech.addArticle("Station d'accueil type C 100 Watts Port Designs Noir", "Port Designs", 47.99, accessoiresOrdinateurCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/be/56/7b/8083134/1520-2.jpg");

        Optional<Category> smartphoneCategory = shopHighTech.getCategory("smartphone");
        shopHighTech.addArticle("Apple iPhone 12 Pro 6,1\" 128 Go Double SIM 5G Bleu pacifique", "Apple", 1159.99, smartphoneCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/56/b2/bd/12431958/1540-1/tsp20210326181135/Apple-iPhone-12-Pro-6-1-128-Go-Double-SIM-5G-Bleu-pacifique.jpg");
        shopHighTech.addArticle("Smartphone Google Pixel 4a Simplement noir 128Go", "Google", 349.99, smartphoneCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/e6/3a/ec/15481574/1540-1/tsp20210208191336/Smartphone-Google-Pixel-4a-Simplement-noir-128Go.jpg");
        shopHighTech.addArticle("Smartphone Samsung Galaxy A52 6.5\" Double SIM 128 Go Noir", "Samsung", 379.99, smartphoneCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/10/3f/f9/16334608/1540-1/tsp20210326171024/Smartphone-Samsung-Galaxy-A52-6-5-Double-SIM-128-Go-Noir.jpg");
        shopHighTech.addArticle("Smartphone OnePlus 9 Pro 6,7\" 128 Go Double SIM 5G Noir étoile", "OnePlus", 919.99, smartphoneCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/c6/24/fd/16590022/1540-1/tsp20210331104555/Smartphone-OnePlus-9-Pro-6-7-128-Go-Double-SIM-5G-Noir-etoile.jpg");
        shopHighTech.addArticle("Smartphone Samsung Galaxy S21 Ultra 6,8\" 256 Go 5G Double SIM Noir", "Samsung", 1309.99, smartphoneCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/11/5e/f6/16145937/1540-1/tsp20210216100846/Smartphone-Samsung-Galaxy-S21-Ultra-6-8-256-Go-5G-Double-SIM-Noir.jpg");
        shopHighTech.addArticle("Smartphone Xiaomi Mi 11 Lite 6.55\" 128 Go Double SIM Noir", "Xiaomi", 345.99, smartphoneCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/c9/24/fd/16590025/1540-1/tsp20210331101748/Smartphone-Xiaomi-Mi-11-Lite-6-55-128-Go-Double-SIM-Noir.jpg");

        Optional<Category> telFixeCategory = shopHighTech.getCategory("tel-fixe");
        shopHighTech.addArticle("Téléphone fixe Alcatel E195 Blanc", "Alcatel", 19.99, telFixeCategory.get(), "https://www.fnac.com/Telephone-fixe-Alcatel-E195-Blanc/a13681479/w-4#omnsearchpos=3");
        shopHighTech.addArticle("Téléphone fixe sans fil Alcatel F890 Voice Noir", "Alcatel", 345.99, telFixeCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/27/9d/ee/15637799/1540-1.jpg");
        shopHighTech.addArticle("Téléphone sans fil Panasonic KX-TGH722 Noir", "Panasonic", 79.99, telFixeCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/37/29/b6/11938103/1540-1/tsp20201210190509/Telephone-sans-fil-Panasonic-KX-TGH722-Noir.jpg");

        Optional<Category> accessoiresTelephonieCategory = shopHighTech.getCategory("accessoires-telephonie");
        shopHighTech.addArticle("Objectif Pince 3 en 1 pour Smartphone Universel Macro Fisheye Grand Angle Metal Pochette Demontable", "NoName", 3.99, accessoiresTelephonieCategory.get(), "https://static.fnac-static.com/multimedia/Images/DC/DC/4F/49/4804572-1505-1540-1/tsp20181226205002/Objectif-Pince-3-en-1-pour-Smartphone-Universel-Macro-Fisheye-Grand-Angle-Metal-Pochette-Demontable.jpg#c57c962f-fbfb-425f-9508-8213512949cc");
        shopHighTech.addArticle("IZUKU Support Telephone Voiture à Grille d'aération avec [2 Pinces de Ventilation] Rotation 360° Universel Porte Téléphone Voiture", " IZUKU", 9.99, accessoiresTelephonieCategory.get(), "https://images-na.ssl-images-amazon.com/images/I/71zpYc6dScL._AC_SL1500_.jpg");
        shopHighTech.addArticle("Etui folio detachable Puro pour iPhone XS Max", "Puro", 10.24, accessoiresTelephonieCategory.get(), "https://static.fnac-static.com/multimedia/Images/FR/MDM/ee/4e/83/8605422/1540-1/tsp20190828131038/Etui-folio-detachable-Puro-pour-iPhone-XS-Max.jpg");
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
