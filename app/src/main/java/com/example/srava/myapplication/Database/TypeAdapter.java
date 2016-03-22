package com.example.srava.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * Created by nocentic on 14/03/2016.
 */
public class TypeAdapter extends TypeHelper{


    private static final String DATABASE_NAME = "cafet";
    private static final int DATABASE_VERSION = 1;
    private TypeHelper dbHelper; // r?f?rence vers le Helper de gestion de la base
    private SQLiteDatabase typeDB; // reference vers une base de donn?es


    // constructeur
    public TypeAdapter(Context context) {
        super(context, "cafet.db", null, 1);
        dbHelper = new TypeHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLiteException {
        try{
            typeDB=dbHelper.getWritableDatabase();
            // LogCat message
            Log.i("MyShotsAdapter", "Base ouverte en ecriture " + typeDB);
        }catch (SQLiteException e){
            typeDB=dbHelper.getReadableDatabase();
            Log.i("MyShotsAdapter", "Base ouverte en lecture " + typeDB);
        }
    }
    public void close(){
        Log.i("MyShotsAdapter", "close: demande de fermeture de la base");
        dbHelper.close();
    }


    public long insertType(String nameType){
        ContentValues newValue = new ContentValues();
        newValue.put(dbHelper.KEY_NAME, nameType);

        return typeDB.insert(TypeHelper.NOM_TABLE, null, newValue);

    }


    public boolean updateType(int ligneID, String nameType){
        ContentValues newValue = new ContentValues();
        newValue.put(dbHelper.KEY_NAME, nameType);

        return typeDB.update(TypeHelper.NOM_TABLE, newValue,
                TypeHelper.KEY_ID + " = " + ligneID, null) > 0;
    }

    public boolean removeType(long ligneID){
        return typeDB.delete(TypeHelper.NOM_TABLE, TypeHelper.KEY_ID + " = " + ligneID,
                null)>0;
    }

    public Cursor getAllData(){ // select *
        return typeDB.query(TypeHelper.NOM_TABLE, new String[]{ TypeHelper.KEY_ID,
                TypeHelper.KEY_NAME}, null, null, null, null, null); }

    public Cursor getSingleType(long ligneID){
        Cursor reponse = typeDB.query(TypeHelper .NOM_TABLE, new String[]{
                        TypeHelper.KEY_ID, TypeHelper.KEY_NAME}, TypeHelper.KEY_ID + " = " + ligneID, null,null,null,null);
        return reponse;
    }

}