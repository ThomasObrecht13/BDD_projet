package model;

public class Marque extends Entity{
    private int idMarque;
    private String nomMarque;

    public Marque(){
        this(0,null);
    }
    public Marque(int idMarque){
        this(idMarque,null);
    }
    public Marque(int idMarque,String nomMarque){
        super();
        this.idMarque=idMarque;
        this.nomMarque=nomMarque;
    }
    public void setIdMarque(int idMarque){
        this.idMarque=idMarque;
    }
    public int getIdMarque(){
        return idMarque;
    }
    public void setNomMarque(String nomMarque){
        this.nomMarque=nomMarque;
    }
    public String getNomMarque(){
        return nomMarque;
    }
}
