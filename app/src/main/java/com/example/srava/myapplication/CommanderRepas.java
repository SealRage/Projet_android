package com.example.srava.myapplication;

import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.srava.myapplication.Database.Menu;
import com.example.srava.myapplication.Database.MenuAdapter;
import com.example.srava.myapplication.Database.MenuHelper;
import com.example.srava.myapplication.Database.Produit;
import com.example.srava.myapplication.Database.ProduitAdapter;
import com.example.srava.myapplication.Database.ProduitHelper;
import com.example.srava.myapplication.Database.TypeAdapter;
import com.example.srava.myapplication.Database.TypeHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CommanderRepas extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    protected  Menu myMenu ;

    MenuHelper helperMenu;
    ProduitHelper helperProduit;
    TypeHelper helperType;
    SQLiteOpenHelper myHelper;
    TypeAdapter insertTypeDB;
    MenuAdapter insertMenuDB;
    ProduitAdapter insertProd;
    //Get Global Controller Class object (see application tag in AndroidManifest.xml)
    final Controller aController = (Controller) getContext();

    protected ArrayList<Produit> myProducts;

    private ListView _activityList;
    Activity activity = getActivity();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    // TODO: Rename and change types and number of parameters
    public static CommanderRepas newInstance(String param1, String param2) {
        CommanderRepas fragment = new CommanderRepas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    public CommanderRepas() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                    if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);


            }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_commander_repas, container, false);



        // Inflate the layout for this fragment
        return myView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        insertMenuDB = new MenuAdapter( getActivity().getApplicationContext());
        insertTypeDB = new TypeAdapter( getActivity().getApplicationContext());
        insertProd = new ProduitAdapter( getActivity().getApplicationContext());

        insertMenuDB.open();
        insertMenuDB.close();

        insertMenuDB.open();

        // Récupération de la listeview
        _activityList = (ListView) view.findViewById(R.id.ListView_Menu);

        _activityList.setAdapter(populate());
        insertMenuDB.close();

        final Controller aController = (Controller) getActivity().getApplicationContext();



        //override de onItemClick pour l'adapter à la liste view
        _activityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(" ok ", " switch ");

                switch (position) {
                    case 0:

                        //TODO : METTRE UNE POP UP POUR LA SUPRESSION OU LA CONSULTATION
                        Log.d(" position ", " Menus ");
                        myMenu = new Menu(0,"Formule Classique",1,0.00,myProducts);
                        break;

                    case 1:

                        Log.d(" position ", " Gourmand ");
                        myMenu = new Menu(1,"Formule Gourmand",2,0.00,myProducts);
                        break;

                    case 2:

                        Log.d(" position ", " Gourmand + ");
                        myMenu = new Menu(2,"Formule Gourmand +",3,0.00,myProducts);
                        break;

                    case 3:

                        Log.d(" position ", " Divers ");
                        myMenu = new Menu(3,"Divers",0,0.00,myProducts);
                        break;

                    default:

                        Log.d(" ok ", " Erreur ");
                        Toast.makeText(getActivity(), "Erreur dans la matrice", Toast.LENGTH_SHORT).show();
                        break;

                }

               // Log.d("val controller",String.valueOf(aController));
               // Log.d("val menu",String.valueOf(myMenu));
                //store product object to arraylist in controller
              //  aController.setProducts(myMenu);
              //  Log.d(" val position ", String.valueOf(position));
                // Get product instance for index
               // Menu tempProductObject = aController.getProducts(position);

                //Check Product already exist in Cart or Not
           //     if (!aController.getCommande().checkProductInCart(tempProductObject)) {
                    Toast.makeText(getActivity(), "Added", Toast.LENGTH_LONG);


                    // Product not Exist in cart so add product to
                    // Cart product arraylist
                    aController.getCommande().setProducts(myMenu);

                    Toast.makeText(getActivity(), "Now Cart size: " + aController.getCommande().getCommandeSize(),
                            Toast.LENGTH_SHORT).show();


            }


        });

    }

    private HashMap<String, String> fillHashMap(String Title, String summary, String icon){
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("TextAppTitle", Title);
        item.put("TextAppSummary", summary);
        item.put("App_icon", icon);
        return item;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        // store data in the bundle
    }



    private ListAdapter populate(){
        ListAdapter adapter = new SimpleCursorAdapter( getActivity().getApplicationContext(),
                R.layout.app_item, insertMenuDB.getAllData(),
                new String[] {MenuHelper.KEY_NAME, MenuHelper.KEY_PRIX},
                new int[] {R.id.TextAppTitle, R.id.TextAppSummary});
        // Bind to our new adapter.
        return adapter;

    }
}
