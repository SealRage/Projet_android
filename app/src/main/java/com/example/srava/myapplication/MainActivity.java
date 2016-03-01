package com.example.srava.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity  {

    private ListView _activityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération de la listeview
        _activityList = (ListView) findViewById(R.id.ListView_app);

        // Remplissage de la liste avec la HashMap
        ArrayList<HashMap<String,String>> appItemList = new ArrayList<HashMap<String,String>>();
        appItemList.add(fillHashMap("Commander", "Accéder aux différents menus", String.valueOf(R.drawable.telephone)));
        appItemList.add(fillHashMap("Récupérer un sandwich", "Génère un Qr code", String.valueOf(R.drawable.telephone)));
        appItemList.add(fillHashMap("Annuler une commande", "Vous pouvez annuler avant 11h", String.valueOf(R.drawable.telephone)));


        // Création d'un SimpleAdapter qui met en correspondance les items présents dans la list avec ceux de la vue
        SimpleAdapter itemsAdapter = new SimpleAdapter(this.getBaseContext(), appItemList, R.layout.app_item,
                new String[] {"TextAppTitle", "TextAppSummary", "App_icon"}, new int[] {R.id.TextAppTitle,
                R.id.TextAppSummary, R.id.App_icon});

        //instanciation des images dans la liste
        _activityList.setAdapter(itemsAdapter);

        //override de onItemClick pour l'adapter à la liste view
        _activityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:

                        Log.d(" ok "," Menus ");
                        Intent intentMenu = new Intent(MainActivity.this , MenuList.class);
                        startActivity(intentMenu);

                        break;

                    case 1:

                        break;

                    case 2:


                    default:

                        break;

                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private HashMap<String, String> fillHashMap(String Title, String summary, String icon){
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("TextAppTitle", Title);
        item.put("TextAppSummary", summary);
        item.put("App_icon", icon);
        return item;
    }
}
