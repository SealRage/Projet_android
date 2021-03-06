package com.example.srava.myapplication.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guigardt on 02/03/2016.
 */
public class Commande {

    protected int idCommande;
    protected List<Menu> RecapChoix = new ArrayList<Menu>();
    protected String dateCommande;

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public List<Menu> getRecapChoix() {
        return RecapChoix;
    }

    public void setRecapChoix(List<Menu> recapChoix) {
        RecapChoix = recapChoix;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }


    public Commande( ArrayList<Menu> recapChoix, String dateCommande) {

        RecapChoix = recapChoix;
        this.dateCommande = dateCommande;
    }

    public Commande(){

    }

    public boolean checkProductInCart(Menu aProduct) {

        return RecapChoix.contains(aProduct);

    }

    public void setProducts(Menu Products) {

        RecapChoix.add(Products);

    }
    public int getCommandeSize() {

        return RecapChoix.size();

    }

    public Menu getProducts(int pPosition) {

        return RecapChoix.get(pPosition);
    }


}
