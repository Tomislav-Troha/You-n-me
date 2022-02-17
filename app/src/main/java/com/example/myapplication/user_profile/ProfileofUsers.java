package com.example.myapplication.user_profile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class ProfileofUsers extends AppCompatActivity {

    TextView id;
    ImageView profileImg;
    TextView txAboutYou;
    TextView txAboutPartner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        id = findViewById(R.id.userIme);
        profileImg = findViewById(R.id.profileImage);



        String username = "not set";
        Bitmap profileImage = null;

        String aboutPartner = "not set";
        String aboutYou = "not set";


        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            username = extras.getString("username");
            profileImage = (Bitmap) extras.get("profileImage");
           // aboutPartner = extras.getString("About_partner");
           // aboutYou = extras.getString("About_you");
        }

        id.setText(username);
        profileImg.setImageBitmap(profileImage);
        //txAboutYou.setText(aboutYou);
        //txAboutPartner.setText(aboutPartner);




    }
}
