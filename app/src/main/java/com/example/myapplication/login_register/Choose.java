package com.example.myapplication.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.home.HomePage;
import com.example.myapplication.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Locale;

public class Choose extends AppCompatActivity {

    ImageButton youFemale;
    ImageButton youMale;

    ImageButton lookFemale;
    ImageButton lookMale;

    Button dalje;

    TextView txtiSi;
    TextView txTrazis;

    private String tisi, trazis;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);

        youFemale = (ImageButton) findViewById(R.id.youAreFemale);
        youMale = (ImageButton) findViewById(R.id.youAreMale);

        lookFemale = (ImageButton) findViewById(R.id.lookForFemale);
        lookMale = (ImageButton) findViewById(R.id.lookForMale);

        txtiSi = (TextView) findViewById(R.id.tiSi);
        txTrazis = (TextView) findViewById(R.id.trazis);

        dalje = (Button) findViewById(R.id.buttonDalje);



        youFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtiSi.setText("Žena".toString());
            }
        });

        youMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtiSi.setText("Muškarac".toString());
            }
        });

        lookFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txTrazis.setText("Ženu".toString());
            }
        });

        lookMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txTrazis.setText("Muškarca".toString());
            }
        });


        dalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tisi = txtiSi.getText().toString();
                trazis = txTrazis.getText().toString();

                addToDatabase(tisi, trazis);


            }
        });
    }

    public void addToDatabase (String tisi, String trazis) {
        ParseObject editProfile = new ParseObject("editProfile");

        String pUser = ParseUser.getCurrentUser().getEmail();

        Log.w("state", txtiSi.toString());


        editProfile.put("User_email", pUser);
        editProfile.put("User_spol", tisi);
        editProfile.put("User_traziSpol", trazis);
        editProfile.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // your object is successfully created.
                    Toast.makeText(getApplicationContext(), "Podaci spremljeni", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Choose.this, HomePage.class));
                } else {
                    //error occurred
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
