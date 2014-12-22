package com.example.louyotedouard.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;

public class choixMode extends ActionBarActivity{

    ImageButton boutonChoixAvis,boutonChoixFormulaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_mode);
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        boutonChoixAvis = (ImageButton) findViewById(R.id.btnChoixAvisClient);
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
