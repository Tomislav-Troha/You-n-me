package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.login_register.Choose;
import com.example.myapplication.login_register.JoinUsPage;
import com.example.myapplication.login_register.MainActivity;
import com.parse.ParseUser;

public class Setting extends AppCompatActivity {


    RelativeLayout logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        logOut = (RelativeLayout) findViewById(R.id.onClickLogOut);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOutUser();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }


    public void logOutUser() {
        if (ParseUser.getCurrentUser() != null) {
            ParseUser.logOut();
            Toast.makeText(Setting.this, "Odjavljeni ste", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Setting.this, MainActivity.class));
                } else { // user is out
                }

    }

}
