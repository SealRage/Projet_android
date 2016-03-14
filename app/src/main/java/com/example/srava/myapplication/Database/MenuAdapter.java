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
public class MenuAdapter extends MenuHelper{


        private static final String DATABASE_NAME = "cafet";
        private static final int DATABASE_VERSION = 1;
        private MenuHelper dbHelper; // r?f?rence vers le Helper de gestion de la base
        private SQLiteDatabase shotsDB; // reference vers une base de donn?es


        // constructeur
        public MenuAdapter(Context context) {
            super(context, "cafet.db", null, 1);
            dbHelper = new MenuHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
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


        public long insertShot(String nameMenu, int typeMenu, double prix, List<Produit> composantMenu){
            ContentValues newValue = new ContentValues();
            newValue.put(dbHelper.KEY_NAME, nameMenu);
            newValue.put(dbHelper.KEY_TYPE, typeMenu);
            newValue.put(dbHelper.KEY_PRIX, prix);
            newValue.put(dbHelper.KEY_COMP, String.valueOf(composantMenu));

            return shotsDB.insert(MenuHelper.NOM_TABLE, null, newValue);
        }


        public boolean updateShot(int ligneID, String nameMenu, int typeMenu, double prix, List<Produit> composantMenu){
            ContentValues newValue = new ContentValues();
            newValue.put(dbHelper.KEY_NAME, nameMenu);
            newValue.put(dbHelper.KEY_TYPE, typeMenu);
            newValue.put(dbHelper.KEY_PRIX, prix);
            newValue.put(dbHelper.KEY_COMP, String.valueOf(composantMenu));

            return shotsDB.update(MenuHelper.NOM_TABLE, newValue,
                    MenuHelper.KEY_ID + " = " + ligneID, null) > 0;
        }

        public boolean removeShot(long ligneID){
            return shotsDB.delete(MenuHelper.NOM_TABLE, MenuHelper.KEY_ID + " = " + ligneID,
                    null)>0;
        }

        public Cursor getAllData(){ // select *
            return shotsDB.query(dbHelper.NOM_TABLE, new String[]{ MenuHelper.KEY_ID,
                    MenuHelper.KEY_NAME, MenuHelper.KEY_TYPE, MenuHelper.KEY_PRIX, MenuHelper.KEY_COMP },null, null, null, null, null, null); }

        public Cursor getSingleShot(long ligneID){
            Cursor reponse = shotsDB.query(MenuHelper .NOM_TABLE, new String[]{
                            MenuHelper.KEY_ID, MenuHelper.KEY_NAME, MenuHelper.KEY_TYPE,
                            MenuHelper.KEY_PRIX,MenuHelper.KEY_COMP }, MenuHelper.KEY_ID + " = " + ligneID, null, null, null,
                    null, null);
            return reponse;
        }

        public Cursor getAllShotsOfAtype(String type_menu){
            Cursor reponse = shotsDB.query(MenuHelper .NOM_TABLE, new String[]{
                            MenuHelper.KEY_ID, MenuHelper.KEY_NAME, MenuHelper.KEY_TYPE,
                            MenuHelper.KEY_PRIX, MenuHelper.KEY_COMP }, MenuHelper.KEY_TYPE + " = " + type_menu, null, null,
                    null, null, null);
            return reponse;
        }


    }



