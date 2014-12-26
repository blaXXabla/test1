package com.example.louyotedouard.test;

import android.app.AlertDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;


public class avisClient extends ActionBarActivity {

    private ImageButton btnThumbUp;
    private ImageButton btnThumbDown;
    private long timeLastVote=0;
    private int nbVote=0;
    private AlertDialog ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avis_client);
        addListenerOnButton();
    }

    /**
     * Gere les evenements sur les boutons
     */
    public void addListenerOnButton() {

        btnThumbUp = (ImageButton) findViewById(R.id.btnThumbUp);
        btnThumbDown = (ImageButton) findViewById(R.id.btnThumbDown);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Merci");
        builder1.setMessage("Avis pris en compte");
        this.ad = builder1.create();

        btnThumbUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
                int idMag=globalVariable.getIdMagasin();
                checkSpam();
                if(haveNetworkConnection())
                    new Connexion().execute("positif",Integer.toString(idMag));
                else
                    System.out.print("Pas de connexion");
                ad.show();
                closeAlertDialog(ad);
            }
        });

        btnThumbDown.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
                int idMag = globalVariable.getIdMagasin();
                checkSpam();
                if(haveNetworkConnection())
                    new Connexion().execute("negatif", Integer.toString(idMag));
                else
                    System.out.print("Pas de connexion");
                ad.show();
                closeAlertDialog(ad);
            }
        });
    }

    /**
     * Bloque l'application pendant 30s si
     * plus de 10 vote en 30s
     */
    public void checkSpam() {
        long timeCurVote=System.currentTimeMillis();
        if(timeLastVote==0)
            timeLastVote=System.currentTimeMillis();
        if(timeCurVote-timeLastVote<3000) {
            nbVote++;
            System.out.println(nbVote);
            if(nbVote==10)
            {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Application bloquée");
                builder1.setMessage("Trop de votes");
                builder1.setCancelable(false);
                final AlertDialog adBlock = builder1.create();
                adBlock.show();
                new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {}

                    @Override
                    public void onFinish() {
                        if(adBlock.isShowing())
                            adBlock.dismiss();
                    }
                }.start();
                nbVote=0;
            }
        }
        timeLastVote=System.currentTimeMillis();
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

    /**
     * Confirme la saisie via un AlertDialog
     * @param adg fenetre de dialogue
     */
    public void closeAlertDialog(AlertDialog adg) {
        final AlertDialog ad=adg;
        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                if(ad.isShowing())
                    ad.dismiss();
            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected void onDestroy() {
        closeAlertDialog(this.ad);
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
