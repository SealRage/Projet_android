package com.example.srava.myapplication;

import android.app.Application;
import android.util.Log;

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

    public void removeProducts(Menu theMenu) {
        Log.d("Remove Menu ",theMenu.toString());
        myCommande.getRecapChoix().remove(theMenu);

    }


    public Commande getCommande() {

        return myCommande;

    }

    public int getProductsArraylistSize() {

        return myMenu.size();
    }


    @Override
    public String toString() {
        return "Controller{" +
                "myCommande=" + myCommande +
                ", myMenu=" + myMenu +
                '}';
    }
}
