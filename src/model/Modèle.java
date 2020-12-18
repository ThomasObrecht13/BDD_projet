package model;

import model.Entity;

public class Modèle extends Entity{
    private int idModèle;
    private String dénomination;
    private int puissanceFiscale;

    public Modèle(){this(0,"",0);}
    public Modèle(int idModèle){this.idModèle = idModèle;}
    public Modèle(int idModèle, String dénomination){
        this.idModèle = idModèle;
        this.dénomination = dénomination;
    }
    public Modèle(int idModèle, String dénomination, int puissanceFiscale){
        super();
        this.idModèle = idModèle;
        this.dénomination = dénomination;
        this.puissanceFiscale = puissanceFiscale;
    }

    public void setIdModèle(int idModèle) {
        this.idModèle = idModèle;
    }

    public int getIdModèle() {
        return idModèle;
    }

    public void setDénomination(String dénomination) {
        this.dénomination = dénomination;
    }

    public String getDénomination() {
        return dénomination;
    }

    public void setPuissanceFiscale(int puissanceFiscale) {
        this.puissanceFiscale = puissanceFiscale;
    }

    public int getPuissanceFiscale() {
        return puissanceFiscale;
    }
}
