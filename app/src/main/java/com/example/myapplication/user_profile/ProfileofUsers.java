package com.example.myapplication.user_profile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.parse.*;

import java.util.ArrayList;

public class ProfileofUsers extends AppCompatActivity {

    TextView userIme;
    ImageView profileImg;
    TextView txAboutYou;
    TextView txAboutPartner;
    TextView txTrazimSpol;
    TextView godineOD;
    TextView godineDO;
    TextView txgodine;

    Button mark;

    String objectId = "";
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

       // mark = findViewById(R.id.addToMessageChat);



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
            objectId = extras.getString("objectId");
        }

        userIme.setText(username);
        profileImg.setImageBitmap(profileImage);
        txAboutYou.setText(aboutYou);
        txAboutPartner.setText(aboutPartner);
        txTrazimSpol.setText(trazimSpol);
        godineDO.setText(godine_do);
        godineOD.setText(godine_od);
        txgodine.setText(godine);
       //addToChat();


       // mark.setOnClickListener(view -> addToChat());


    }


    public void addToChat () {

        ParseQuery < ParseUser > query = ParseUser.getQuery();
        query.whereEqualTo("email", ParseUser.getCurrentUser().getEmail());

       query.findInBackground((objects, e) -> {
           if (e == null) {
               for (ParseObject object:objects){
                  // Toast.makeText(ProfileofUsers.this, object.getObjectId(), Toast.LENGTH_SHORT).show();
                   query.getInBackground(object.getObjectId(), (object1, e1) -> {
                       if(e1 == null) {

                               ArrayList<String> list = new ArrayList<>();
                               list.add(objectId);
                               object1.add("marked_for_chat", list);

                               object1.saveInBackground(new SaveCallback() {
                                   @Override
                                   public void done(ParseException e) {
                                       if (e == null) {
                                           Toast.makeText(ProfileofUsers.this, "Request sent", Toast.LENGTH_SHORT).show();
                                       }else {
                                           Toast.makeText(ProfileofUsers.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                       }
                                   }
                               });
                       }else {
                           Toast.makeText(ProfileofUsers.this, e1.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                       }
                   });
               }
           }else {
               Toast.makeText(ProfileofUsers.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
           }
       });





    }
}
