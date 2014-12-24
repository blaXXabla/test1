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

    public void insert(String a,String id)
    {
        String avis=a;
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://louyotedouard.fr/autre/test.php");
        try
        {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("avis",avis));
            nameValuePairs.add(new BasicNameValuePair("id_magasin",id));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
        }
        catch(Exception e)
        {
        }
    }

    @Override
    protected Double doInBackground(String... params) {
        String avis = params[0];
        String id=params[1];
        insert(avis,id);
        return null;
    }

    protected void onPostExecute(Double result){

    }

    protected void onProgressUpdate(Integer... progress){
    }
}
