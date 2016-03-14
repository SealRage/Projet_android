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


    private static final String DATABASE_NAME = "cafet";
    private static final int DATABASE_VERSION = 1;
    private ProduitHelper dbHelper; // r?f?rence vers le Helper de gestion de la base
    private SQLiteDatabase shotsDB; // reference vers une base de donn?es


    // constructeur
    public ProduitAdapter(Context context) {
        super(context, "cafet.db", null, 1);
        dbHelper = new ProduitHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLiteException {
        try{
            shotsDB=dbHelper.getWritableDatabase();
            // LogCat message
            Log.i("MyShotsAdapter", "Base ouverte en ecriture " + shotsDB);
        }catch (SQLiteException e){
            shotsDB=dbHelper.getReadableDatabase();
            Log.i("MyShotsAdapter", "Base ouverte en lecture " + shotsDB);
        }
    }
    public void close(){
        Log.i("MyShotsAdapter", "close: demande de fermeture de la base");
        dbHelper.close();
    }


    public long insertShot(String nameProduit, String typeProduit, double prix){
        ContentValues newValue = new ContentValues();
        newValue.put(ProduitHelper.KEY_NAME, nameProduit);
        newValue.put(ProduitHelper.KEY_TYPE, typeProduit);
        newValue.put(ProduitHelper.KEY_PRIX, prix);

        return shotsDB.insert(ProduitHelper.NOM_TABLE, null, newValue);
    }


    public boolean updateShot(int ligneID,String nameProduit, String typeProduit, double prix){
        ContentValues newValue = new ContentValues();
        newValue.put(dbHelper.KEY_NAME, nameProduit);
        newValue.put(dbHelper.KEY_TYPE, typeProduit);
        newValue.put(dbHelper.KEY_PRIX, prix);

        return shotsDB.update(ProduitHelper.NOM_TABLE, newValue,
                ProduitHelper.KEY_ID + " = " + ligneID, null) > 0;
    }

    public boolean removeShot(long ligneID){
        return shotsDB.delete(ProduitHelper.NOM_TABLE, ProduitHelper.KEY_ID + " = " + ligneID,
                null)>0;
    }

    public Cursor getAllData(){ // select *
        return shotsDB.query(dbHelper.NOM_TABLE, new String[]{ ProduitHelper.KEY_ID,
                ProduitHelper.KEY_NAME, ProduitHelper.KEY_TYPE, ProduitHelper.KEY_PRIX}, null, null, null, null, null); }

    public Cursor getSingleShot(long ligneID){
        Cursor reponse = shotsDB.query(ProduitHelper .NOM_TABLE, new String[]{
                        ProduitHelper.KEY_ID, ProduitHelper.KEY_NAME, ProduitHelper.KEY_TYPE,
                        ProduitHelper.KEY_PRIX}, ProduitHelper.KEY_ID + " = " + ligneID, null, null,
                null, null);
        return reponse;
    }

    public Cursor getAllShotsOfAtype(String type_produit){
        Cursor reponse = shotsDB.query(ProduitHelper .NOM_TABLE, new String[]{
                        ProduitHelper.KEY_ID, ProduitHelper.KEY_NAME, ProduitHelper.KEY_TYPE,
                        ProduitHelper.KEY_PRIX}, ProduitHelper.KEY_TYPE + " = " + type_produit, null, null,
                null, null);
        return reponse;
    }


}



