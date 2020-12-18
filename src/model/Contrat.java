package model;

import model.Entity;

public class Contrat extends Entity{
    private int idContrat;
    private String dateRetrait;
    private String dateRetour;
    private int kmRetrait;
    private int kmRetour;
    private int idClient;
    private int immatriculation;
    private int idAgenceRetour;

    public Contrat(){this(0,"","",0,0
            ,0,0,0);}
    public Contrat(int idContrat){
        this.idContrat = idContrat;
    }
    public Contrat(int idContrat, String dateRetrait){
        this.idContrat = idContrat;
        this.dateRetrait = dateRetrait;
    }
    public Contrat(int idContrat, String dateRetrait, String dateRetour){
        this.idContrat = idContrat;
        this.dateRetrait = dateRetrait;
        this.dateRetour = dateRetour;
    }
    public Contrat(int idContrat, String dateRetrait, String dateRetour, int kmRetrait){
        this.idContrat = idContrat;
        this.dateRetrait = dateRetrait;
        this.dateRetour = dateRetour;
        this.kmRetrait = kmRetrait;
    }
    public Contrat(int idContrat, String dateRetrait, String dateRetour, int kmRetrait, int kmRetour){
        this.idContrat = idContrat;
        this.dateRetrait = dateRetrait;
        this.dateRetour = dateRetour;
        this.kmRetrait = kmRetrait;
        this.kmRetour = kmRetour;
    }
    public Contrat(int idContrat, String dateRetrait, String dateRetour, int kmRetrait, int kmRetour,
                   int idClient){
        this.idContrat = idContrat;
        this.dateRetrait = dateRetrait;
        this.dateRetour = dateRetour;
        this.kmRetrait = kmRetrait;
        this.kmRetour = kmRetour;
        this.idClient = idClient;
    }
    public Contrat(int idContrat, String dateRetrait, String dateRetour, int kmRetrait, int kmRetour,
                   int idClient, int immatriculation){
        this.idContrat = idContrat;
        this.dateRetrait = dateRetrait;
        this.dateRetour = dateRetour;
        this.kmRetrait = kmRetrait;
        this.kmRetour = kmRetour;
        this.idClient = idClient;
        this.immatriculation = immatriculation;
    }
    public Contrat(int idContrat, String dateRetrait, String dateRetour, int kmRetrait, int kmRetour,
                   int idClient, int immatriculation, int idAgenceRetour ){
        this.idContrat = idContrat;
        this.dateRetrait = dateRetrait;
        this.dateRetour = dateRetour;
        this.kmRetrait = kmRetrait;
        this.kmRetour = kmRetour;
        this.idClient = idClient;
        this.immatriculation = immatriculation;
        this.idAgenceRetour = idAgenceRetour;
    }

    public void setIdContrat(int idContrat) {
        this.idContrat = idContrat;
    }

    public int getIdContrat() {
        return idContrat;
    }

    public void setDateRetrait(String dateRetrait) {
        this.dateRetrait = dateRetrait;
    }

    public String getDateRetrait() {
        return dateRetrait;
    }

    public void setDateRetour(String dateRetour) {
        this.dateRetour = dateRetour;
    }

    public String getDateRetour() {
        return dateRetour;
    }

    public void setKmRetrait(int kmRetrait) {
        this.kmRetrait = kmRetrait;
    }

    public int getKmRetrait() {
        return kmRetrait;
    }

    public void setKmRetour(int kmRetour) {
        this.kmRetour = kmRetour;
    }

    public int getKmRetour() {
        return kmRetour;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setImmatriculation(int immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getImmatriculation() {
        return immatriculation;
    }

    public void setIdAgenceRetour(int idAgenceRetour) {
        this.idAgenceRetour = idAgenceRetour;
    }

    public int getIdAgenceRetour() {
        return idAgenceRetour;
    }
}
