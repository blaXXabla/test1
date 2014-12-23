package com.example.louyotedouard.test;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;



public class CodeMagasins extends ActionBarActivity {

    Button btn_launch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_magasins);
        addListenerOnButton();
    }
    public void addListenerOnButton() {

        btn_launch = (Button) findViewById(R.id.btn_magasins);

        btn_launch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent browserIntent =
                        new Intent(CodeMagasins.this, choixMode.class);
                startActivity(browserIntent);

            }

        });
    }

    /*public void actionPerformed(MotionEvent arg0) {
        //Lorsque l'on clique sur le bouton, on met à jour le JLabel
        this.compteur++;
        label.setText("Vous avez cliqué " + this.compteur + " fois");
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_code_magasins, menu);
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
