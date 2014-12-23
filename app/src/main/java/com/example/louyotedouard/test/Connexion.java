package com.example.louyotedouard.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by PC Xavier on 23/12/2014.
 */
public class Connexion extends AsyncTask<String, Integer, Double>{

    private String link,result;
    private Context c;
    private InputStream is;

    public Connexion(Context context)
    {
        c= context;
        this.link="http://louyotedouard.fr/autre/test.php";
    }

    public void insert()
    {
        int i1=10;
        int i2=57;
        int i3=125;
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("positif",Integer.toString(i1)));
        nameValuePairs.add(new BasicNameValuePair("negatif",Integer.toString(i2)));
        nameValuePairs.add(new BasicNameValuePair("id_magasin",Integer.toString(i3)));

        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://louyotedouard.fr/autre/test.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        }
        catch(Exception e)
        {
            Toast.makeText(c, e.toString(),
                    Toast.LENGTH_LONG).show();
            System.out.print(e.toString());
        }

        try
        {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line="";
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            System.out.print(result);
            Log.e("pass 2", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {
            JSONObject json_data = new JSONObject(result);
            int code;
            code=(json_data.getInt("code"));

            if(code==1)
            {
                Toast.makeText(c, "1",
                        Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(c, "Else",
                        Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
    }

    @Override
    protected Double doInBackground(String... params) {
        // TODO Auto-generated method stub
        insert();
        return null;
    }

    protected void onPostExecute(Double result){
        //pb.setVisibility(View.GONE);
        //Toast.makeText(getApplicationContext(), "command sent", Toast.LENGTH_LONG).show();
    }

    protected void onProgressUpdate(Integer... progress){
        //pb.setProgress(progress[0]);
    }
}
