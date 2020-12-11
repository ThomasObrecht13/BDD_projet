package model;

import java.util.Date;

public class Vehicule extends Entity {
    private int immatriculation;
    private String dateMiseEnCirculation;
    private String état;
    private int nbKilomètres;
    private int prixParJourDeLocation;
    private int idMarque;
    private int idModèle;
    private int idCatégorie;
    private int idType;
    private int idAgence;

    public Vehicule(){
        this(0,null,null,0,0,0,0,0,0,0);
    }
    public Vehicule(int immatriculation){
        this(immatriculation,null,null,0,0,0,0,0,0,0);
    }
    public Vehicule(int immatriculation, int idMarque, int idModèle, int idCatégorie, int idType, int idAgence){
        this(immatriculation,null,null,0,0,idMarque,idModèle,idCatégorie,idType,idAgence);
    }
    public Vehicule(int immatriculation, String dateMiseEnCirculation, String état, int nbKilomètres, int prixParJourDeLocation, int idMarque, int idModèle, int idCatégorie, int idType, int idAgence){
        super();

        this.immatriculation=immatriculation;
        this.dateMiseEnCirculation=dateMiseEnCirculation;
        this.état=état;
        this.nbKilomètres=nbKilomètres;
        this.prixParJourDeLocation=prixParJourDeLocation;
        this.idMarque=idMarque;
        this.idModèle=idModèle;
        this.idCatégorie=idCatégorie;
        this.idType=idType;
        this.idAgence=idAgence;
    }
    public void setImmatriculation(int immatriculation){
        this.immatriculation=immatriculation;
    }
    public int getImmatriculation() {
        return immatriculation;
    }

    public void setDateMiseEnCirculation(String dateMiseEnCirculation) {
        this.dateMiseEnCirculation = dateMiseEnCirculation;
    }
    public String getDateMiseEnCirculation() {
        return dateMiseEnCirculation;
    }

    public void setÉtat(String état) {
        this.état = état;
    }
    public String getÉtat() {
        return état;
    }

    public void setNbKilomètres(int nbKilomètres) {
        this.nbKilomètres = nbKilomètres;
    }
    public int getNbKilomètres() {
        return nbKilomètres;
    }

    public void setPrixParJourDeLocation(int prixParJourDeLocation) {
        this.prixParJourDeLocation = prixParJourDeLocation;
    }
    public int getPrixParJourDeLocation() {
        return prixParJourDeLocation;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }
    public int getIdMarque() {
        return idMarque;
    }

    public void setIdModèle(int idModèle) {
        this.idModèle = idModèle;
    }
    public int getIdModèle() {
        return idModèle;
    }

    public void setIdCatégorie(int idCatégorie) {
        this.idCatégorie = idCatégorie;
    }
    public int getIdCatégorie() {
        return idCatégorie;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }
    public int getIdType() {
        return idType;
    }

    public void setIdAgence(int idAgence) {
        this.idAgence = idAgence;
    }
    public int getIdAgence() {
        return idAgence;
    }

}
