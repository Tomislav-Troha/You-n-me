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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.myapplication.EditProfile;
import com.example.myapplication.R;
import com.example.myapplication.Setting;
import com.example.myapplication.login_register.Choose;
import com.parse.*;

import java.time.Year;
import java.util.Calendar;

public class ThirdFragment extends Fragment {

    public ThirdFragment(){
        // require a empty public constructor
    }


    ImageView goToEdit;

    TextView Profile;


    RelativeLayout pretplata;

    RelativeLayout setting;

    TextView godine;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_third, container, false);


        //logOuTButton = (Button) view.findViewById(R.id.logOutBtn);
        Profile = view.findViewById(R.id.txProfilenadimak);

        ParseUser user = ParseUser.getCurrentUser();
        Profile.setText(user.getUsername());
        user.saveInBackground();


        pretplata = view.findViewById(R.id.onClickPretplata);

        godine = view.findViewById(R.id.txGodine);

        setting = view.findViewById(R.id.onClickSetting);

        setting.setOnClickListener(view1 -> startActivity(new Intent(ThirdFragment.this.getActivity(), Setting.class)));


        goToEdit = view.findViewById(R.id.onClickGoToProfileEdit);

        goToEdit.setOnClickListener(view12 -> startActivity(new Intent(ThirdFragment.this.getActivity(), EditProfile.class)));

        getProfileImage();

        return view;
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
                    Toast.makeText(ThirdFragment.this.getActivity(), "opet nis od slike", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Log.e("ma nis", "slika nije stavljena");
        }
    }



}
