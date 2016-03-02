package com.example.srava.myapplication.Webservice;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Contacts;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.srava.myapplication.Database.Menu;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by guigardt on 02/03/2016.
 */
public class HttpRequestTaskManager extends AsyncTask<Menu, Integer, JSONObject> {
    // Url du serveur
    private static final String LOGIN_URL = "http://eternity.lightning-sphere.com/index.php";
    private static final String FLAG_SUCCESS = "success";
    private static final String FLAG_MESSAGE = "message";
    private ProgressBar connectionStatus;
    private TextView progressBarC;


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected JSONObject doInBackground(Menu... params) {


        JSONObject jsonResponse = new JSONObject();

        try {
            //Communication avec le script php sur le serveur pour traduire le message en sql
            URL url = new URL(LOGIN_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Log.d("1er try doInBck", "Work in progress");
            connection.setRequestMethod("POST");

            String urlParameters = "Get credentials for menu";
            //encodage
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            connection.setRequestProperty("Content-Length", "" + postData.length);
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {

                Log.d("DataOutputStream", "Work in progress");
                wr.write(postData);
            }


            Log.d("HttpRequestTaskManager", "ready to send request...");
            connection.connect();
            //Récupération du retour du script php en JSon et décriptage de celui ci
            InputStream in = new BufferedInputStream(connection.getInputStream());
            jsonResponse = new JSONObject(convertStreamToString(in));

        } catch (IOException e) {
            Log.e("IOException", String.valueOf(e));
        } catch (JSONException e) {
            Log.e("JSONException",String.valueOf(e));
        }

        return jsonResponse;
    }



    protected void onPostExecute(JSONObject response){
        Log.d("OnPostExecute","2eme para");
        try {
            //Si le script renvoie quelque chose et qu'on le décode
            int loginOK = response.getInt(FLAG_SUCCESS);
            //mise a jour du textview

            //connectionStatus.setText(response.getString(FLAG_MESSAGE));
            Log.d("YOlooooloooo","2eme para");
            progressBarC.setVisibility(View.GONE);
            if (loginOK != 0) {

            } else {

                // TODO : Lancer l'application avec les list view
            }
        }catch (JSONException e) {
            Log.e("JSONException", String.valueOf(e));
        }
    }

    protected void onProgressUpdate(Integer... progress){
        //progressBarC.setProgress(progress[0]);
    }

    public String convertStreamToString(java.io.InputStream is){
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
