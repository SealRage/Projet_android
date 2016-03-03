package com.example.srava.myapplication;

import android.app.Application;

import com.example.srava.myapplication.Database.Commande;
import com.example.srava.myapplication.Database.Menu;

import java.util.ArrayList;

/**
 * Created by guigardt on 03/03/2016.
 */
public class Controller extends Application {

    private ArrayList<Menu> myMenu = new ArrayList<Menu>();
    private Commande myCommande = new Commande();


    public Menu getProducts(int pPosition) {

        return myMenu.get(pPosition);
    }

    public void setProducts(Menu theMenu) {

        myMenu.add(theMenu);

    }

    public Commande getCommande() {

        return myCommande;

    }

    public int getProductsArraylistSize() {

        return myMenu.size();
    }

}
