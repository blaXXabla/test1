package com.example.louyotedouard.test;

import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//test.
public class FormulaireInscription extends ActionBarActivity {

    private         RadioButton             rb_Email;
    private         RadioButton             rb_NumTel;
    private         Button                  btnLaunch;
    private         EditText                et_info;
    private         AutoCompleteTextView    auto_textview;
    private         TextView                arobase;

    private Mailin http = new Mailin("https://api.sendinblue.com/v2.0","6Eg59DMZzB0yUG1s");
    private String str = http.create_sender("Douchet Loic","Douchet.loic55@gmail.com", new String [] {"1 2 3 4","5 6 7 8 mon domaine"});

    CountDownTimer countdown = new CountDownTimer(20000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {}

        @Override
        public void onFinish() {
            Intent browserIntent =new Intent(FormulaireInscription.this, veille.class);
            startActivity(browserIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_inscription);
        addListenerOnButton();
        countdown.start();
    }

    public void addListenerOnButton() {
        auto_textview           = (AutoCompleteTextView)    findViewById(R.id.auto_domaines);
        rb_Email                = (RadioButton)             findViewById(R.id.radio_email);
        rb_NumTel               = (RadioButton)             findViewById(R.id.radio_numtel);
        btnLaunch               = (Button)                  findViewById(R.id.btn_ValideFormulaireIns);
        et_info                 = (EditText)                findViewById(R.id.et_info);
        arobase                 = (TextView)                findViewById(R.id.tv_arobase);

        View.OnClickListener vue = new View.OnClickListener() {
            public void onClick(View v) {
                countdown.cancel();
                countdown.start();
            }
        };

        if (rb_Email.isChecked()) {
            countdown.cancel();
            countdown.start();
        }

        rb_Email.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                et_info.setText("");
                auto_textview.setVisibility(View.VISIBLE);
                arobase.setVisibility(View.VISIBLE);
                countdown.cancel();
                countdown.start();
            }
        });

        rb_NumTel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                et_info.setText("");
                auto_textview.setVisibility(View.GONE);
                arobase.setVisibility(View.GONE);
                countdown.cancel();
                countdown.start();
            }
        });


        btnLaunch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(rb_Email.isChecked())
                {
                    if(isValidEmail(et_info.getText().toString()+arobase.getText().toString()+auto_textview.getText().toString())) {
                        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
                        String idMag=globalVariable.getIdMagasin();
                        String adr_mail=et_info.getText().toString();
                        String typeEnvoi="mail";
                        new Connexion().execute(typeEnvoi,adr_mail,idMag);

                        Map< String, String > to = new HashMap< String, String >();
                        to.put(adr_mail, "to whom");
                        Map < String, String > cc = new HashMap < String, String > ();
                        cc.put("cyril.borderichard@gmail.com", "cc whom");
                        Map < String, String > bcc = new HashMap < String, String > ();
                        bcc.put("bcc@example.net", "bcc whom");
                        Map < String, String > headers = new HashMap < String, String > ();
                        headers.put("Content-Type", "text/html; charset=iso-8859-1");
                        headers.put("X-param1", "value1");
                        headers.put("X-param2", "value2");
                        headers.put("X-Mailin-custom", "my custom value");
                        headers.put("X-Mailin-IP", "102.102.1.2");
                        headers.put("X-Mailin-Tag", "My tag");
                        String str = http.send_email(to,"My subject",new String [] {"from@email.com","from email"},"This is the <h1>HTML</h1>","This is the text",cc,bcc,new String [] {"replyto@email.com","reply to"},new String [] {},headers);

                        Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG);
                        //http.send_email(str,"sujet",new String[]{"from"},"","text","cc","bcc",new String[]{"replyto"},"","");


                        countdown.cancel();
                        countdown.start();
                        toast.show();

                    }
                    else
                    {
                        countdown.cancel();
                        countdown.start();
                        Toast toast = Toast.makeText(getApplicationContext(), "Veuillez saisir un email correct", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                else
                {
                    if(isValidPhoneNumber(et_info.getText().toString())) {
                        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
                        String idMag=globalVariable.getIdMagasin();
                        String num_sms=et_info.getText().toString();
                        String typeEnvoi="sms";
                        new Connexion().execute(typeEnvoi,num_sms,idMag);

                        countdown.cancel();
                        countdown.start();
                    }
                    else
                    {
                        countdown.cancel();
                        countdown.start();
                        Toast toast = Toast.makeText(getApplicationContext(), "Veuillez saisir un numéro correct", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });

        fillACTextView();
    }

    /**
     * Rempli le auto complete text view avec les noms de domaine
     */
    public void fillACTextView()
    {
        String[] DOMAINES={"gmail.com","sfr.fr","neuf.fr","yahoo.fr","orange.fr","hotmail.fr"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DOMAINES);
        auto_textview.setAdapter(adapter);
    }

    /**
     *
     * @param email email a verifier
     * @return  true si adresse correcte, false sinon
     */
    public final static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     *
     * @param num numero a verifier
     * @return true si num correct, false sinon
     */
    public final static boolean isValidPhoneNumber(String num){
        return android.util.Patterns.PHONE.matcher(num).matches();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulaire_inscription, menu);
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
