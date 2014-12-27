package com.example.louyotedouard.test;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class choixMode extends ActionBarActivity{

    ImageButton boutonChoixAvis,boutonChoixFormulaire, boutonPush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_mode);
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        boutonChoixAvis = (ImageButton) findViewById(R.id.btnChoixAvisClient);
        boutonPush = (ImageButton) findViewById(R.id.btnPush);
        boutonChoixFormulaire = (ImageButton) findViewById(R.id.btnChoixFormulaireInscription);

        boutonChoixAvis.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent browserIntent =
                        new Intent(choixMode.this, avisClient.class);
                startActivity(browserIntent);

            }

        });

        boutonChoixFormulaire.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent browserIntent =
                        new Intent(choixMode.this, FormulaireInscription.class);
                startActivity(browserIntent);


            }

        });

        /**
         * Insere dans la bdd les data stockees dans le fichier
         */
        boutonPush.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String FILENAME = "dbOffline.txt";
                File file = getApplicationContext().getFileStreamPath(FILENAME);
                if(haveNetworkConnection()) {
                    if (file.exists()) {
                        try {
                            FileInputStream in = openFileInput(FILENAME);
                            InputStreamReader inputStreamReader = new InputStreamReader(in);
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            String line;
                            while ((line = bufferedReader.readLine()) != null) {
                                String avis = line.substring(0, line.indexOf("%"));
                                String id = line.substring(line.indexOf("%") + 1);
                                new Connexion().execute(avis, id);
                            }
                        } catch (FileNotFoundException fnf) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Fichier inexistant", Toast.LENGTH_LONG);
                            toast.show();
                        } catch (Exception e) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Echec de l'ajout", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                    Toast toast = Toast.makeText(getApplicationContext(), "Opération terminée", Toast.LENGTH_LONG);
                    toast.show();
                    file.delete();
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Connexion internet requise", Toast.LENGTH_LONG);
                    toast.show();
                }
            }

        });

    }

    /**
     * Test si une connexion internet est disponible
     * @return true si une connexion est disponible, false sinon
     */
    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choix_mode, menu);
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
}
