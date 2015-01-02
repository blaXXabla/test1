package com.example.louyotedouard.test;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

//test
public class FormulaireInscription extends ActionBarActivity {

    private         TextView            textView;
    private         RadioButton         rb_Email;
    private         RadioButton         rb_NumTel;
    private         Button              btnLaunch;
    private         EditText            et_info;

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
        textView = (TextView) findViewById(R.id.textViewFormulaire);
        rb_Email = (RadioButton) findViewById(R.id.radio_email);
        rb_NumTel = (RadioButton) findViewById(R.id.radio_numtel);
        btnLaunch = (Button) findViewById(R.id.btn_ValideFormulaireIns);
        et_info = (EditText) findViewById(R.id.et_info);

        View.OnClickListener vue = new View.OnClickListener() {
            public void onClick(View v) {
                countdown.cancel();
                countdown.start();
            }
        };

        if (rb_Email.isChecked()) {
            textView.setText(R.string.radioEmail);
            countdown.cancel();
            countdown.start();
        }else
            textView.setText(R.string.radioNumTel);

        rb_Email.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                textView.setText(R.string.radioEmail);
                et_info.setText("");
                textView.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                countdown.cancel();
                countdown.start();
            }
        });

        rb_NumTel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                textView.setText(R.string.radioNumTel);
                et_info.setText("");
                textView.setInputType(InputType.TYPE_CLASS_PHONE);
                countdown.cancel();
                countdown.start();
            }
        });

        btnLaunch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(rb_Email.isChecked())
                {
                    if(isValidEmail(et_info.getText().toString())) {

                        Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG);
                        http.send_email(str,"sujet",new String[]{"from"},"","text","cc","bcc",new String[]{"replyto"},"","");
                        countdown.cancel();
                        countdown.start();
                        toast.show();
                    }
                    else
                    {
                        countdown.cancel();
                        countdown.start();
                        Toast toast = Toast.makeText(getApplicationContext(), "Veuillez saisir un email correcte", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                else
                {
                    if(isValidPhoneNumber(et_info.getText().toString())) {
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
