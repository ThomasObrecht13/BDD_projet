package model;

public class Agence extends Entity{
    private int idAgence;
    private int nbEmployés;
    private int idVille;

    public Agence(){
        this(0,0,0);
    }
    public Agence(int idAgence){
        this(idAgence,0,0);
    }
    public Agence(int idAgence,int idVille){
        this(idAgence,0,idVille);
    }
    public Agence(int idAgence, int nbEmployés, int idVille){
        super();
        this.idAgence=idAgence;
        this.nbEmployés=nbEmployés;
        this.idVille=idVille;
    }

    public void setIdAgence(int idAgence){
        this.idAgence=idAgence;
    }
    public int getIdAgence(){
        return idAgence;
    }

    public void setNbEmployés(int nbEmployés){
        this.nbEmployés=nbEmployés;
    }
    public int getNbEmployés(){
        return nbEmployés;
    }

    public void setIdVille(int idVille){
        this.idVille=idVille;
    }
    public int getIdVille(){
        return idVille;
    }
}
