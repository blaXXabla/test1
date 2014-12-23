package com.example.louyotedouard.test;

import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by PC Xavier on 23/12/2014.
 */
public class Connexion extends AsyncTask<String, Integer, Double>{


    public void insert()
    {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://louyotedouard.fr/autre/test.php");
        try
        {
            int i1=10;
            int i2=57;
            int i3=126;
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("positif",Integer.toString(i1)));
            nameValuePairs.add(new BasicNameValuePair("negatif",Integer.toString(i2)));
            nameValuePairs.add(new BasicNameValuePair("id_magasin",Integer.toString(i3)));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
        }
        catch(Exception e)
        {
//            Toast.makeText(c, e.toString(),
//                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected Double doInBackground(String... params) {
        insert();
        return null;
    }

    protected void onPostExecute(Double result){

    }

    protected void onProgressUpdate(Integer... progress){
    }
}
