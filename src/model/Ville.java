package model;

public class Ville extends Entity{
    private int idVille;
    private String nomVille;
    private int nombreHabitants;

    public Ville() {
        this(0,null,0);
    }
    public Ville(int idVille){
        this(idVille,null,0);
    }
    public Ville(int idVille,String nomVille){
        this(idVille,nomVille,0);
    }
    public Ville(int idVille, String nomVille, int nombreHabitants) {
        super();
        this.idVille=idVille;
        this.nomVille=nomVille;
        this.nombreHabitants=nombreHabitants;
    }



    public void setIdVille(int idVille){
        this.idVille=idVille;
    }
    public int getIdVille(){
        return idVille;
    }
    public void setNomVille(String nomVille){
        this.nomVille=nomVille;
    }
    public String getNomVille(){
        return nomVille;
    }
    public void setNombreHabitants(int nombreHabitants){
        this.nombreHabitants=nombreHabitants;
    }
    public int getNombreHabitsants(){
        return nombreHabitants;
    }
}
