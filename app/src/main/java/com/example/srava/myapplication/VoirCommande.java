package com.example.srava.myapplication;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.srava.myapplication.Database.Menu;
import com.example.srava.myapplication.Database.Produit;

import java.util.ArrayList;
import java.util.HashMap;


public class VoirCommande extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    protected Menu myMenu;
    protected ArrayList<Produit> myProducts;

    private ListView _activityList;
    Activity activity = getActivity();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    // TODO: Rename and change types and number of parameters
    public static VoirCommande newInstance(String param1, String param2) {
        VoirCommande fragment = new VoirCommande();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    public VoirCommande() {
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

        View myView = inflater.inflate(R.layout.fragment_voir_commande, container, false);



        // Inflate the layout for this fragment
        return myView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        // R�cup�ration de la listeview
        _activityList = (ListView) view.findViewById(R.id.ListView_Menu);

        // Remplissage de la liste avec la HashMap
        ArrayList<HashMap<String,String>> appItemList = new ArrayList<HashMap<String,String>>();
        appItemList.add(fillHashMap("Formule Classique", "1 Sandwich + 2 parmi chips, eau, fruit", String.valueOf(R.drawable.telephone)));
        appItemList.add(fillHashMap("Formule Gourmand", "1 Sdch Sp� + 2 parmi chips, eau, fruit", String.valueOf(R.drawable.telephone)));
        appItemList.add(fillHashMap("Formule Gourmand +", "Formule gourmand + 1 canette", String.valueOf(R.drawable.telephone)));
        appItemList.add(fillHashMap("Divers", "Vente au d�tail", String.valueOf(R.drawable.telephone)));

        // Cr�ation d'un SimpleAdapter qui met en correspondance les items pr�sents dans la list avec ceux de la vue
        SimpleAdapter itemsAdapter = new SimpleAdapter(this.getActivity(), appItemList, R.layout.app_item,
                new String[] {"TextAppTitle", "TextAppSummary", "App_icon"}, new int[] {R.id.TextAppTitle,
                R.id.TextAppSummary, R.id.App_icon});

        //instanciation des images dans la liste
        _activityList.setAdapter(itemsAdapter);

        //override de onItemClick pour l'adapter � la liste view
        _activityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:


                        Log.d(" ok ", " Menus ");
                        myMenu = new Menu("Classique",1,0.00,myProducts);
                        break;

                    case 1:

                        Log.d(" ok ", " Gourmand ");
                        myMenu = new Menu("Gourmand",2,0.00,myProducts);
                        break;

                    case 2:

                        Log.d(" ok ", " Gourmand + ");
                        myMenu = new Menu("Gourmand +",3,0.00,myProducts);
                        break;

                    case 3:

                        Log.d(" ok ", " Divers ");
                        myMenu = new Menu("Divers",0,0.00,myProducts);
                        break;

                    default:

                        Log.d(" ok ", " Erreur ");
                        Toast.makeText(getActivity(), "Erreur dans la matrice", Toast.LENGTH_SHORT).show();
                        break;

                }

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

}