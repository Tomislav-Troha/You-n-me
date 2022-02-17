package com.example.myapplication.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;


import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.home.HomePage;
import com.parse.*;

public class JoinUsPage extends AppCompatActivity  {

    Button submitBtn;

    EditText nadimak;
    EditText email;
    EditText lozinka;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.myapplication.R.layout.email_page);


        submitBtn = findViewById(com.example.myapplication.R.id.potvrdiButton);

        nadimak =  findViewById(com.example.myapplication.R.id.editNadimak);
        email =  findViewById(com.example.myapplication.R.id.editEmail);
        lozinka =  findViewById(R.id.editLozinka);

        onClickSubmit();
    }


    public void onClickSubmit() {

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty(nadimak) && !isEmpty(email) && !isEmpty(lozinka)) {
                    ParseUser user = new ParseUser();
                    user.setUsername(nadimak.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setPassword(lozinka.getText().toString());

                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e == null) {
                                Toast.makeText(getApplicationContext(), getString(R.string.regUpsijesna), Toast.LENGTH_LONG).show();
                                startActivity(new Intent(JoinUsPage.this, Choose.class));
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

                if(isEmpty(nadimak)){
                    Toast t = Toast.makeText(JoinUsPage.this, getString(R.string.ispuniteSvaPolja), Toast.LENGTH_SHORT);
                    t.show();
                    nadimak.setError(getString(R.string.morasNadimak));
                }


                if(isEmpty(email)){
                    email.setError(getString(R.string.emailObavzan));
                }

                if(isEmpty(lozinka)){
                    lozinka.setError(getString(R.string.lozinkaObavezna));
                }
            }
        });

    }




    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

   /*void checkDataEntered() {
        if(isEmpty(nadimak)){
            Toast t = Toast.makeText(this, "Moras unijeti nadimak!", Toast.LENGTH_SHORT);
            t.show();
            nadimak.setError("Ime je obavezno");
        }


        if(isEmpty(email)){
            email.setError("Email je obavezan");
        }

        if(isEmpty(lozinka)){
            nadimak.setError("Lozinka je obavezna");
        }
        else {
            startActivity(new Intent(JoinUsPage.this, Choose.class));
        }
    }*/
}
