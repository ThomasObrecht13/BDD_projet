package model.model;

import model.Entity;

public class Facture extends Entity{
    private int idFacture;
    private int montant;
    private int idContrat;

    public Facture(){this(0,0,0);}
    public Facture(int idFacture){this.idFacture = idFacture;}
    public Facture(int idFacture, int montant){
        this.idFacture = idFacture;
        this.montant = montant;
    }
    public Facture(int idFacture, int montant, int idContrat){
        super();
        this.idFacture = idFacture;
        this.montant = montant;
        this.idContrat = idContrat;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getMontant() {
        return montant;
    }

    public void setIdContrat(int idContrat) {
        this.idContrat = idContrat;
    }

    public int getIdContrat() {
        return idContrat;
    }
}
