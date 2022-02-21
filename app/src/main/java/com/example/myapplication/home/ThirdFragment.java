package com.example.myapplication.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.myapplication.settings.EditProfile;
import com.example.myapplication.R;
import com.example.myapplication.settings.Setting;
import com.parse.*;

import java.util.Calendar;

public class ThirdFragment extends Fragment {

    public ThirdFragment(){
        // require a empty public constructor
    }


    ImageView goToEdit;

    TextView profileUsername;


    RelativeLayout pretplata;

    RelativeLayout setting;

    TextView godine;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_third, container, false);




        //logOuTButton = (Button) view.findViewById(R.id.logOutBtn);
        profileUsername = view.findViewById(R.id.txProfilenadimak);



        pretplata = view.findViewById(R.id.onClickPretplata);

        godine = view.findViewById(R.id.txGodine);


        setting = view.findViewById(R.id.onClickSetting);

        setting.setOnClickListener(view1 -> startActivity(new Intent(ThirdFragment.this.getActivity(), Setting.class)));


        goToEdit = view.findViewById(R.id.onClickGoToProfileEdit);

        goToEdit.setOnClickListener(view12 -> startActivity(new Intent(ThirdFragment.this.getActivity(), EditProfile.class)));

        getProfileImage();
        getYears();

        return view;
    }


    String rodenje = "";
    String username = "";
    public void getYears() {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("email", ParseUser.getCurrentUser().getEmail());

        query.findInBackground((objects, e) -> {
            if (e == null) {
                for (ParseObject object : objects) {

                    if(object.get("Datum_rodenja") == null) {
                        rodenje = "/";
                    }

                    rodenje = object.get("Datum_rodenja").toString();
                    String[] parts = rodenje.split("/");

                    int year = Calendar.getInstance().get(Calendar.YEAR);
                    int date = Integer.parseInt(parts[2]);
                    int intBirthday = year - date;
                    String birthday = String.valueOf(intBirthday);
                    godine.setText(birthday);

                    //Toast.makeText(ThirdFragment.this.getActivity(), String.valueOf(year) + " " + String.valueOf(date), Toast.LENGTH_SHORT).show();

                    if(object.get("username") == null) {
                        username = "";
                    }
                    username = object.get("username").toString();
                    profileUsername.setText(username);
                }
            }else {
                Log.e(getString(R.string.dosloJeDoGreske), " " + e.getLocalizedMessage());
            }
        });
    }


    public void getProfileImage() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseFile imageFile = (ParseFile) currentUser.get("Profil_image");

        if (imageFile != null) {
            imageFile.getDataInBackground((data, e) -> {
                if(e == null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                    goToEdit.setImageBitmap(bitmap);
                }
                else {
                    Toast.makeText(ThirdFragment.this.getActivity(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Log.e("ma nis", "slika nije stavljena");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.profil));
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("You 'n Me");
    }



}
