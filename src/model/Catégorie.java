package model;

import model.Entity;

public class Catégorie extends Entity{
    private int idCatégorie;
    private String libelléCatégorie;

    public Catégorie(){this(0,"");}
    public Catégorie(int idCatégorie){this.idCatégorie = idCatégorie;}
    public Catégorie(int idCatégorie, String libelléCatégorie){
        super();
        this.idCatégorie = idCatégorie;
        this.libelléCatégorie = libelléCatégorie;
    }

    public void setIdCatégorie(int idCatégorie){this.idCatégorie = idCatégorie;}
    public int getIdCatégorie(){return this.idCatégorie;}

    public void setLibelléCatégorie(String libelléCatégorie){this.libelléCatégorie = libelléCatégorie;}
    public String getLibelléCatégorie(){return this.libelléCatégorie;}
}
