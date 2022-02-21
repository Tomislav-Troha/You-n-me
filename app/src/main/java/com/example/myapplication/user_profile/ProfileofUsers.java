package com.example.myapplication.user_profile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

import java.nio.charset.StandardCharsets;

public class ProfileofUsers extends AppCompatActivity {

    TextView userIme;
    ImageView profileImg;
    TextView txAboutYou;
    TextView txAboutPartner;
    TextView txTrazimSpol;
    TextView godineOD;
    TextView godineDO;
    TextView txgodine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        userIme = findViewById(R.id.userIme);
        profileImg = findViewById(R.id.profileImage);

        txAboutYou = findViewById(R.id.txAboutYou);
        txAboutPartner = findViewById(R.id.txAboutPartner);

        txTrazimSpol = findViewById(R.id.trazimSpol);

        godineDO = findViewById(R.id.txGodine_do);
        godineOD = findViewById(R.id.txGodine_od);

        txgodine = findViewById(R.id.txUserGodina);



        String username = "not set";
        Bitmap profileImage = null;

        String aboutPartner = "not set";
        String aboutYou = "not set";

        String trazimSpol = "not set";

        String godine_od = "not set";
        String godine_do = "not set";

        String godine = "not set";

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            username = extras.getString("username");
            profileImage = (Bitmap) extras.get("profileImage");
            aboutPartner = extras.getString("About_partner");
            aboutYou = extras.getString("About_you");
            trazimSpol = extras.getString("User_traziSpol");
            godine_od = extras.getString("godine_od");
            godine_do = extras.getString("godine_do");
            godine = extras.getString("godinaRodenja");
        }

        userIme.setText(username);
        profileImg.setImageBitmap(profileImage);
        txAboutYou.setText(aboutYou);
        txAboutPartner.setText(aboutPartner);
        txTrazimSpol.setText(trazimSpol);
        godineDO.setText(godine_do);
        godineOD.setText(godine_od);
        txgodine.setText(godine);





    }
}
