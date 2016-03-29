package com.example.srava.myapplication.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nocentic on 10/03/2016.
 */
public class MenuHelper extends SQLiteOpenHelper {

    // membres public permettant de dfinir les champs de la base
    public static final String NOM_TABLE = "Menu";
    public static final String KEY_ID = "id_menu";
    public static final String KEY_NAME = "nom_menu";
    public static final String KEY_TYPE = "type_menu";
    public static final String KEY_PRIX = "prix_menu";
    public static final String KEY_COMP = "composant_menu";


    // String permettant la creation de la table
    private static final String DATABASE_CREATE = "create table " + NOM_TABLE +
            " (" + KEY_ID + " integer primary key autoincrement, " +
            KEY_NAME + " text not null, " +
            KEY_TYPE + " text not null, " +
            KEY_PRIX + " text not null, " +
            KEY_COMP + " text );";
    public MenuHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                         int version) {
        super(context, name, factory, version);
        Log.i("ShotsDBhelper", "Helper construit");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
// creation de la table
        db.execSQL(DATABASE_CREATE);
        Log.i("ShotsDBhelper", "Database created with instruction : " +
                DATABASE_CREATE);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// destruction de la base et recreation
        db.execSQL("DROP TABLE IF EXISTS " + NOM_TABLE);
        Log.i("ShotsDBhelper", "Base mise a jour !!!");
        onCreate(db);
    }
}
