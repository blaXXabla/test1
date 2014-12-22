package com.example.louyotedouard.test;

import android.app.AlertDialog;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
                checkSpam();
                ad.show();
                closeAlertDialog(ad);
            }
        });

        btnThumbDown.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                ad.show();
                closeAlertDialog(ad);
            }
        });
    }

    /**
     *
     * @return false si application bloquee a cause de spam, false sinon
     */
    public boolean checkSpam() {
        boolean block=false;
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
        return block;
    }

    /**
     *
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_avis_client, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        closeAlertDialog(this.ad);
        super.onDestroy();
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
