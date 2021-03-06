package com.example.srava.myapplication;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
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

    protected String draw;
    protected Menu myPr;
    protected Menu myMenu;
    protected ArrayList<Produit> myProducts;

    //Get Global Controller Class object (see application tag in AndroidManifest.xml)
     Controller aController = (Controller) getContext();

    // Get Cart Size
   // final int cartSize = aController.getCommande().getCommandeSize();

    String showString = "";

    protected ListView _activityList;
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
        // Create the list fragment and add it as our sole content.




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


        activity = getActivity();


        MajView();

        //override de onItemClick pour l'adapter à la liste view
    /*    _activityList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {






                return true;

            }


        });*/
    }




    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Log.d(" open avant super ", " onCreateContextMenu ");
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.d(" open ", " onCreateContextMenu ");
        // Je  recupere l'element sur laquelle on a fait un appui long:
       // Menu myPr = aController.getProducts((int) position);
//        Log.d("Produit trouvé ", myPr.toString());
        Log.d("position trouvé ", menuInfo.toString());
        menu.add(android.view.Menu.NONE, R.id.a_item, android.view.Menu.NONE, "Annuler");
        menu.add(android.view.Menu.NONE, R.id.b_item, android.view.Menu.NONE, "Supprimer");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Log.d("enter ",item.toString());
        Log.d("info ", String.valueOf(info.id));
        switch(item.getItemId()) {
            case R.id.a_item:
                Log.i("ContextMenu", "Item Annuler was chosen");
                return true;
            case R.id.b_item:
                Log.i("ContextMenu", "Item Supprimer was chosen");
                aController.removeProducts(aController.getCommande().getProducts((int) info.id));
//                Log.d("Remove Menu ", (aController.getProducts((int) info.id)).toString());
                MajView();
                return true;


        }
        return super.onContextItemSelected(item);
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


    public void MajView(){
        aController = (Controller) getActivity().getApplicationContext();
        View view = getView();
        // Get Cart Size
        final int cartSize = aController.getCommande().getCommandeSize();
        // Récupération de la listeview
        _activityList = (ListView) view.findViewById(R.id.ListView_Menu);

        // Remplissage de la liste avec la HashMap
        ArrayList<HashMap<String,String>> appItemList = new ArrayList<HashMap<String,String>>();

        //On met le forpour tout récupérer
        // TODO : METTRE LE FOR
        if(cartSize >0)
        {

            for(int i=0;i<cartSize;i++)
            {
                //Get product details
                String pName = aController.getCommande().getProducts(i).getNameMenu();
                String pDesc = String.valueOf(aController.getCommande().getProducts(i).getComposantMenu());
                //  String pDisc   	= aController.getCommande().getProducts(i).getDrawable();
                if(aController.getCommande().getProducts(i).getNameMenu().equals("Formule Gourmand")) {
                    draw = String.valueOf(R.drawable.menugourmand);

                }
                else if(aController.getCommande().getProducts(i).getNameMenu().equals("Formule Gourmand +")) {
                    draw = String.valueOf(R.drawable.menuplus);

                }
                else if(aController.getCommande().getProducts(i).getNameMenu().equals("Formule Classique")) {
                    draw = String.valueOf(R.drawable.menuclassqiue);

                }
                else if(aController.getCommande().getProducts(i).getNameMenu().equals("Divers")) {
                    draw = String.valueOf(R.drawable.divers);

                }

                appItemList.add(fillHashMap(pName, pDesc, draw));
            }
        }
        else
            showString = "\n\nShopping cart is empty.\n\n";

        // Création d'un SimpleAdapter qui met en correspondance les items présents dans la list avec ceux de la vue
        SimpleAdapter itemsAdapter = new SimpleAdapter(this.getActivity(), appItemList, R.layout.app_item,
                new String[] {"TextAppTitle", "TextAppSummary", "App_icon"}, new int[] {R.id.TextAppTitle,
                R.id.TextAppSummary, R.id.App_icon});

        //instanciation des images dans la liste
        _activityList.setAdapter(itemsAdapter);
        registerForContextMenu(_activityList);
    }
}
