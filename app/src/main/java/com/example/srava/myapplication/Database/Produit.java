package com.example.srava.myapplication.Database;

/**
 * Created by guigardt on 02/03/2016.
 */
public class Produit {

    protected int idProduit;
    protected String nameProduit;
    protected String typeProduit;
    protected double prix;


    public String getNameProduit() {
        return nameProduit;
    }

    public void setNameProduit(String nameProduit) {
        this.nameProduit = nameProduit;
    }

    public String getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(String typeProduit) {
        this.typeProduit = typeProduit;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public Produit(String typeProduit, String nameProduit, int idProduit, double prix) {
        this.typeProduit = typeProduit;
        this.nameProduit = nameProduit;
        this.idProduit = idProduit;
        this.prix = prix;
    }
}
