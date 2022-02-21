package com.example.myapplication.login_register;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.home.HomePage;
import com.example.myapplication.R;
import com.parse.*;

import java.util.Calendar;
import java.util.Locale;

public class Choose extends AppCompatActivity {

    ImageButton youFemale;
    ImageButton youMale;

    ImageButton lookFemale;
    ImageButton lookMale;

    Button dalje;

    TextView txtiSi;
    TextView txTrazis;

    EditText datumRod;

    private  String ID_tiSi_spol, ID_trazis_spol;

    private String tisi, trazis, datumRodenja;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);

        youFemale =  findViewById(R.id.youAreFemale);
        youMale =  findViewById(R.id.youAreMale);

        lookFemale =  findViewById(R.id.lookForFemale);
        lookMale =  findViewById(R.id.lookForMale);

        txtiSi =  findViewById(R.id.tiSi);
        txTrazis =  findViewById(R.id.trazis);

        dalje =  findViewById(R.id.buttonDalje);

        datumRod = findViewById(R.id.txDatumRodenja);


        youFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtiSi.setText(getString(R.string.tiSiZena));
                ID_tiSi_spol = "0";
            }
        });

        youMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtiSi.setText(getString(R.string.tiSiMuskarac));
                ID_tiSi_spol = "1";
            }
        });

        lookFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txTrazis.setText(getString(R.string.trazimZenu));
                ID_trazis_spol = "0";
            }
        });

        lookMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txTrazis.setText(getString(R.string.trazimMuskarca));
                ID_trazis_spol = "1";
            }
        });

        openPopupEditBirthdate();


        dalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tisi = txtiSi.getText().toString();
                trazis = txTrazis.getText().toString();
                datumRodenja = datumRod.getText().toString();

                if(isEmpty(datumRod)){
                    Toast t = Toast.makeText(Choose.this, getString(R.string.ispuniteSvaPolja), Toast.LENGTH_SHORT);
                    t.show();
                    datumRod.setError("Required");
                }


                if(isEmptyText(txtiSi)){
                    txtiSi.setError("Required");
                }

                if(isEmptyText(txTrazis)){
                    txTrazis.setError("Required");
                }

                addToDatabase(tisi, trazis, datumRodenja, ID_tiSi_spol, ID_trazis_spol);


            }
        });
    }

    public void addToDatabase (String tisi, String trazis, String datumRodenja, String id_tiSi_spol, String id_trazis_spol) {
        ParseUser user_profile = ParseUser.getCurrentUser();

        String pUser = ParseUser.getCurrentUser().getEmail();

        Log.w("state", txtiSi.toString());


        if(!isEmptyText(txtiSi) && !isEmptyText(txTrazis) && !isEmpty(datumRod)){

            user_profile.put("User_spol", tisi);
            user_profile.put("User_traziSpol", trazis);
            user_profile.put("Datum_rodenja", datumRodenja);
            user_profile.put("id_spol", id_tiSi_spol);
            user_profile.put("id_trazis_spol", id_trazis_spol);
            user_profile.saveInBackground(new SaveCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        // your object is successfully created.
                        Toast.makeText(getApplicationContext(), getString(R.string.podaciSpremljeni), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Choose.this, HomePage.class));
                        finish();
                    } else {
                        //error occurred
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmptyText(TextView text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    private int mYear, mMonth, mDay;
    public void openPopupEditBirthdate() {
        datumRod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == datumRod) {
                    final Calendar calendar = Calendar.getInstance();
                    mYear = calendar.get(Calendar.YEAR);
                    mMonth = calendar.get(Calendar.MONTH);
                    mDay = calendar.get(Calendar.DAY_OF_MONTH);

                    //show dialog
                    DatePickerDialog datePickerDialog = new DatePickerDialog(Choose.this, (view, year, month, dayOfMonth) -> {
                        datumRod.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        // datumRodenja = openPopupDatum.getText().toString();
                        //  editBirthDateOfUser(datumRodenja);
                    }, mYear, mMonth, mDay);

                    datePickerDialog.show();
                }
            }
        });

    }
}
