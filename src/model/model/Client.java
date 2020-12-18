package model.model;

import model.Entity;

public class Client extends Entity{
    private int idClient;
    private String nomClient;
    private String adresseClient;
    private int codePostalClient;
    private int idVille;

    public Client(){
        this(0,null,null,0,0);
    }
    public Client(int idClient){
        this(idClient,null,null,0,0);
    }
    public Client(int idClient,int codePostalClient){
        this(idClient,null,null,codePostalClient,0);
    }
    public Client(int idClient,int codePostalClient,int idVille){
        this(idClient,null,null,codePostalClient,idVille);
    }
    public Client(int idClient, String nomClient, String adresseClient, int codePostalClient, int idVille){
        super();
        this.idClient=idClient;
        this.nomClient=nomClient;
        this.adresseClient=adresseClient;
        this.codePostalClient=codePostalClient;
        this.idVille=idVille;
    }
    public void setIdClient(int idClient){
        this.idClient=idClient;
    }
    public int getIdClient(){
        return idClient;
    }

    public void setNomClient(String nomClient){
        this.nomClient=nomClient;
    }
    public String getNomClient(){
        return nomClient;
    }

    public void setAdresseClient(String adresseClient){
        this.adresseClient=adresseClient;
    }
    public String getAdresseClient(){
        return adresseClient;
    }

    public void setCodePostalClient(int codePostalClient){
        this.codePostalClient=codePostalClient;
    }
    public int getCodePostalClient(){
        return codePostalClient;
    }

    public void setIdVille(int idVille){
        this.idVille=idVille;
    }
    public int getIdVille(){
        return idVille;
    }


}
