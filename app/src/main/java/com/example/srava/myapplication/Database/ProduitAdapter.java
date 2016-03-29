package com.example.srava.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.List;

/**
 * Created by nocentic on 10/03/2016.
 */
public class ProduitAdapter extends ProduitHelper{


    private static final String DATABASE_NAME = "Lunch";
    private static final int DATABASE_VERSION = 1;
    private ProduitHelper dbHelper; // r?f?rence vers le Helper de gestion de la base
    private SQLiteDatabase produitDB; // reference vers une base de donn?es


    // constructeur
    public ProduitAdapter(Context context) {
        super(context, "Lunch.db", null, 1);
        dbHelper = new ProduitHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLiteException {
        try{
            produitDB=dbHelper.getWritableDatabase();
            // LogCat message
            Log.i("MyShotsAdapter", "Base ouverte en ecriture " + produitDB);
        }catch (SQLiteException e){
            produitDB=dbHelper.getReadableDatabase();
            Log.i("MyShotsAdapter", "Base ouverte en lecture " + produitDB);
        }
    }
    public void close(){
        Log.i("MyShotsAdapter", "close: demande de fermeture de la base");
        dbHelper.close();
    }


    public long insertProduit(String nameProduit, String typeProduit, double prix){
        ContentValues newValue = new ContentValues();
        newValue.put(ProduitHelper.KEY_NAME, nameProduit);
        newValue.put(ProduitHelper.KEY_TYPE, typeProduit);
        newValue.put(ProduitHelper.KEY_PRIX, prix);

        return produitDB.insert(ProduitHelper.NOM_TABLE, null, newValue);
    }


    public boolean updateProduit(int ligneID,String nameProduit, String typeProduit, double prix){
        ContentValues newValue = new ContentValues();
        newValue.put(dbHelper.KEY_NAME, nameProduit);
        newValue.put(dbHelper.KEY_TYPE, typeProduit);
        newValue.put(dbHelper.KEY_PRIX, prix);

        return produitDB.update(ProduitHelper.NOM_TABLE, newValue,
                ProduitHelper.KEY_ID + " = " + ligneID, null) > 0;
    }

    public boolean removeProduit(long ligneID){
        return produitDB.delete(ProduitHelper.NOM_TABLE, ProduitHelper.KEY_ID + " = " + ligneID,
                null)>0;
    }

    public Cursor getAllData(){ // select *
        return produitDB.query(dbHelper.NOM_TABLE, new String[]{ProduitHelper.KEY_ID,
                ProduitHelper.KEY_NAME, ProduitHelper.KEY_TYPE, ProduitHelper.KEY_PRIX}, null, null, null, null, null); }

    public Cursor getSingleProduit(long ligneID){
        Cursor reponse = produitDB.query(ProduitHelper .NOM_TABLE, new String[]{
                        ProduitHelper.KEY_ID, ProduitHelper.KEY_NAME, ProduitHelper.KEY_TYPE,
                        ProduitHelper.KEY_PRIX}, ProduitHelper.KEY_ID + " = " + ligneID, null, null,
                null, null);
        return reponse;
    }

    public Cursor getAllProduitOfAtype(String type_produit){
        Cursor reponse = produitDB.query(ProduitHelper .NOM_TABLE, new String[]{
                        ProduitHelper.KEY_ID, ProduitHelper.KEY_NAME, ProduitHelper.KEY_TYPE,
                        ProduitHelper.KEY_PRIX}, ProduitHelper.KEY_TYPE + " = " + type_produit, null, null,
                null, null);
        return reponse;
    }
}



