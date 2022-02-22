package com.example.myapplication.splash;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telecom.TelecomManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.example.myapplication.R;
import com.example.myapplication.home.HomePage;
import com.example.myapplication.login_register.MainActivity;
import com.parse.ParseUser;



public class SplashActivity extends AppCompatActivity {

    ImageView hearts;
    TextView youMe;

    CharSequence charSequence;
    int index;
    long delay = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        hearts = findViewById(R.id.hearts);
        youMe = findViewById(R.id.txYouMe);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                hearts,
                PropertyValuesHolder.ofFloat("scaleX", 1.1f),
                PropertyValuesHolder.ofFloat("scaleY", 1.1f)
        );

        objectAnimator.setDuration(500);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);

        objectAnimator.start();

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_splash);
        youMe.setAnimation(animation);

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
