package model.impl;

import model.api.IComposite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Category extends ComponentImpl implements IComposite<Category> {
    private Optional<Category> categorieParent;
    private List<Category> sousCategories;

    public Category(int id, String libelle) {
        super(id, libelle);
        this.categorieParent = Optional.empty();
        this.sousCategories = new ArrayList<>();
    }

    public Category(int id, String libelle, Optional<Category> categorieParent) {
        super(id, libelle);
        this.categorieParent = categorieParent;
        this.sousCategories = new ArrayList<>();
    }

    public Category(int id, String libelle, List<Category> sousCategories) {
        super(id, libelle);
        this.categorieParent = Optional.empty();
        this.sousCategories = sousCategories;
    }

    public Category(int id, String libelle, Optional<Category> categorieParent, List<Category> sousCategories) {
        super(id, libelle);
        this.categorieParent = categorieParent;
        this.sousCategories = sousCategories;
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
        sousCategories.addAll(children);
    }

    @Override
    public void addChild(Category child) {
        sousCategories.add(child);
    }
    
    @Override
    public String toString()
    {
    	String p = categorieParent.isPresent() ? " (parent "+ categorieParent.toString()  + ")": "";
    	return libelle +  p;
				
    }
}
