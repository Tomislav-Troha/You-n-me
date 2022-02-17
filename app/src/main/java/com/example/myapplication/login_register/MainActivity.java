package com.example.myapplication.login_register;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;


public class MainActivity extends AppCompatActivity {



    private Button mybtn;

    private TextView btnPrijaviSe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mybtn =  findViewById(R.id.JoinButton);

        btnPrijaviSe = findViewById(R.id.txPrijava);

        onbtnClick();

        Prijava();



    }


    public void Prijava(){
        btnPrijaviSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginPage.class));
            }
        });
    }



    public void onbtnClick() {
        mybtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, JoinUsPage.class));
            }
        });
    }




}