package com.example.srava.myapplication.Database;

/**
 * Created by guigardt on 02/03/2016.
 */
public class Type {

    protected int idType;
    protected String nameType;

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }


    public Type(int idType, String nameType) {
        this.idType = idType;
        this.nameType = nameType;
    }
}
