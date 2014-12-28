package com.example.louyotedouard.test;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private     Button      btn_launch;
    private     EditText    et_codemagasin;
    private     Button      btn_effacer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_magasins);
        addListenerOnButton();
    }
    public void addListenerOnButton() {

        btn_launch = (Button) findViewById(R.id.btn_magasins);
        btn_effacer = (Button) findViewById(R.id.btn_effacer);
        et_codemagasin = (EditText) findViewById(R.id.et_codeMagasins);
        Button bt0 = (Button) findViewById(R.id.btn_code0);
        Button bt1 = (Button) findViewById(R.id.btn_code1);
        Button bt2 = (Button) findViewById(R.id.btn_code2);
        Button bt3 = (Button) findViewById(R.id.btn_code3);
        Button bt4 = (Button) findViewById(R.id.btn_code4);
        Button bt5 = (Button) findViewById(R.id.btn_code5);
        Button bt6 = (Button) findViewById(R.id.btn_code6);
        Button bt7 = (Button) findViewById(R.id.btn_code7);
        Button bt8 = (Button) findViewById(R.id.btn_code8);
        Button bt9 = (Button) findViewById(R.id.btn_code9);

        btn_launch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
                if(et_codemagasin.length()>0) {
                    globalVariable.setIdMagasin(Integer.parseInt(et_codemagasin.getText().toString()));
                    Intent browserIntent =
                            new Intent(MainActivity.this, choixMode.class);
                    startActivity(browserIntent);
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Veuillez saisir un code", Toast.LENGTH_LONG);
                    toast.show();
                }

            }

        });

        btn_effacer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (et_codemagasin.length()>0){ // test si l' édit text est vide, si il n' est pas vide on efface un caractère sinon on ne fait rien ( cause : crash de l' application )
                    et_codemagasin.setText(et_codemagasin.getText().subSequence(0,et_codemagasin.length()-1));
                }
            }
        });

        bt0.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                et_codemagasin.setText(et_codemagasin.getText()+"0");
            }

        });

        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                et_codemagasin.setText(et_codemagasin.getText()+"1");
            }

        });

        bt2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                et_codemagasin.setText(et_codemagasin.getText()+"2");
            }

        });

        bt3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                et_codemagasin.setText(et_codemagasin.getText()+"3");
            }

        });

        bt4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                et_codemagasin.setText(et_codemagasin.getText()+"4");
            }

        });

        bt5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                et_codemagasin.setText(et_codemagasin.getText()+"5");
            }

        });

        bt6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                et_codemagasin.setText(et_codemagasin.getText()+"6");
            }

        });

        bt7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                et_codemagasin.setText(et_codemagasin.getText()+"7");
            }

        });

        bt8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                et_codemagasin.setText(et_codemagasin.getText()+"8");
            }

        });

        bt9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                et_codemagasin.setText(et_codemagasin.getText()+"9");
            }

        });
    }

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
