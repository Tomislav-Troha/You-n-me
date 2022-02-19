package com.example.myapplication.home;


import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.parse.*;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

public class FirstFragment extends Fragment {

    public FirstFragment(){
        // require a empty public constructor
    }

    RecyclerView listView;
    ArrayList<Cards> arrayList = new ArrayList<>();
    Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);


        listView = view.findViewById(R.id.recycler_view);
        getCards();






        return view;
    }

    String username;
    Bitmap bitmap;
    public void getCards() {

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("email", ParseUser.getCurrentUser().getEmail());



        arrayList = new ArrayList<>();
        query.findInBackground((objects, e) -> {


            for(ParseObject object:objects){

                ParseFile imageFile = (ParseFile) object.get("Profil_image");
                if (imageFile != null) {

                    imageFile.getDataInBackground((data, e1) -> {

                        if(e1 == null) {
                            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                            username = object.get("username").toString();

                           // Toast.makeText(FirstFragment.this.getActivity(), username, Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(FirstFragment.this.getActivity(), getString(R.string.dosloJeDoGreske), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(FirstFragment.this.getActivity(), getString(R.string.profilNijePronaden), Toast.LENGTH_SHORT).show();
                }

                ParseRelation<ParseObject> relation = object.getRelation("email");
                ParseQuery<ParseObject> query1 = relation.getQuery();
                query1.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects1, ParseException e) {
                        for(ParseObject object1:objects1){
                            Log.e(TAG, object1.get("username").toString());
                        }
                    }
                });

            }



        });

        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("editProfile");
        query1.whereNotEqualTo("User_email", ParseUser.getCurrentUser().getEmail());

        query1.findInBackground((objects, e) -> {

            String aboutYou  = "";
            String aboutPartner = "";
            String userTraziSpol = "";
            String godine_od = "";
            String godine_do = "";

            for (ParseObject object:objects){
                // Log.e(object1.get("About_you").toString(), "bruh");

                //Toast.makeText(FirstFragment.this.getActivity(),object1.getObjectId(), Toast.LENGTH_SHORT).show();

                if(e == null) {
                    if(object.get("About_you") == null) {
                        aboutYou = "";
                    }
                    else {
                        aboutYou = object.get("About_partner").toString();
                    }

                    if(object.get("About_partner") == null){
                        aboutPartner = "";
                    } else {
                        aboutPartner = object.get("About_you").toString();
                    }

                    userTraziSpol = object.get("User_traziSpol").toString();


                    if(object.get("Godine_od") == null) {
                        godine_od = "";
                    }
                    else {
                        godine_od = object.get("Godine_od").toString();
                    }

                    if(object.get("Godine_do") == null) {
                        godine_do = "";
                    }
                    else {
                        godine_do = object.get("Godine_do").toString();
                    }

                }
                else {
                    Toast.makeText(FirstFragment.this.getActivity(),e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

                // Log.e("s", aboutYou);
                Cards cards =  new Cards(username, bitmap, aboutPartner, aboutYou, userTraziSpol, godine_od, godine_do );
               arrayList.add(cards);
            }

            listView.setLayoutManager(new LinearLayoutManager(FirstFragment.this.getActivity()));
            adapter = new Adapter(FirstFragment.this.getActivity(), arrayList);
            listView.setAdapter(adapter);
            //Toast.makeText(FirstFragment.this.getActivity(),username, Toast.LENGTH_SHORT).show();

        });
    }
}
