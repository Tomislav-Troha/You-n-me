package com.example.myapplication.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.example.myapplication.R;
import com.example.myapplication.Setting;
import com.example.myapplication.home.HomePage;
import com.example.myapplication.login_register.MainActivity;
import com.parse.ParseUser;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ParseUser.getCurrentUser() != null) {
                     startActivity(new Intent(SplashActivity.this, HomePage.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }

            finish();}
        }, 2000);









    }
}
