package model.impl;

import model.api.IComponent;

public abstract class ComponentImpl implements IComponent {
    protected final int id;
    protected String libelle;

    protected ComponentImpl(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Integer getId()
    {
        return id;
    }

    public String getLibelle()
    {
        return libelle;
    }
}
