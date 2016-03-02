package com.example.srava.myapplication.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guigardt on 02/03/2016.
 */
public class Menu {

    protected int idMenu;
    protected String nameMenu;
    protected String typeMenu;
    protected List<Produit> composantMenu;
    protected double prix;

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNameMenu() {
        return nameMenu;
    }

    public void setNameMenu(String nameMenu) {
        this.nameMenu = nameMenu;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getTypeMenu() {
        return typeMenu;
    }

    public void setTypeMenu(String typeMenu) {
        this.typeMenu = typeMenu;
    }

    public List<Produit> getComposantMenu() {
        return composantMenu;
    }

    public void setComposantMenu(List<Produit> composantMenu) {
        this.composantMenu = composantMenu;
    }

    public Menu(int idMenu, String nameMenu, String typeMenu, double prix, ArrayList<Produit> composantMenu) {
        this.idMenu = idMenu;
        this.nameMenu = nameMenu;
        this.typeMenu = typeMenu;
        this.prix = prix;
        this.composantMenu = composantMenu;
    }
}
