package com.example.louyotedouard.test;

import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;

/**
 * Created by PC Xavier on 23/12/2014.
 */
public class Connexion extends AsyncTask<String, Integer, Double>{

    /**
     * Insert des donnees dans la DB
     * @param act activité en cours : avis ou inscrption
     * @param a avis du client
     * @param id id du magasin
     */
    //TODO adapter la methode pour inserer avis ou inscritpion
    public void insert(String act,String a,String id)
    {
        if(act.equals("avis")) {
            String avis = a;
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://louyotedouard.fr/autre/test.php");
            try {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair(act, avis));
                nameValuePairs.add(new BasicNameValuePair("id_magasin", id));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
            } catch (Exception e) {
            }
        }
        else
        {

        }

    }

    @Override
    protected Double doInBackground(String... params) {
        String activity=params[0];
        String avis = params[1];
        String id=params[2];
        insert(activity,avis,id);
        return null;
    }

    protected void onPostExecute(Double result){

    }

    protected void onProgressUpdate(Integer... progress){
    }
}
