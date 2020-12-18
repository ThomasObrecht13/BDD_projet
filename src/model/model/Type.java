package model.model;

import model.Entity;

public class Type extends Entity {
    private int idType;
    private String libelléType;

    public Type() {
        this(0, "");
    }

    public Type(int idType) {
        this.idType = idType;
    }

    public Type(int idType, String libelléType) {
        super();
        this.idType = idType;
        this.libelléType = libelléType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public int getIdType() {
        return idType;
    }

    public void setLibelléType(String libelléType) {
        this.libelléType = libelléType;
    }

    public String getLibelléType() {
        return libelléType;
    }
}
