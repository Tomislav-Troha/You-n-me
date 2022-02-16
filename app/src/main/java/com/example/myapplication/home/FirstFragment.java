package com.example.myapplication.home;


import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.parse.*;

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



    public void getCards() {

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("email", ParseUser.getCurrentUser().getEmail());
        query.findInBackground((objects, e) -> {
            arrayList = new ArrayList<>();

            for(ParseObject object:objects){

                ParseFile imageFile = (ParseFile) object.get("Profil_image");

                if (imageFile != null) {
                    imageFile.getDataInBackground((data, e1) -> {
                        if(e1 == null) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                            Cards cards =  new Cards(object.get("username").toString(), bitmap);
                            arrayList.add(cards);

                            //Toast.makeText(FirstFragment.this.getActivity(), "dolazi al dzabe", Toast.LENGTH_LONG).show();

                            listView.setLayoutManager(new LinearLayoutManager(FirstFragment.this.getActivity()));
                            adapter = new Adapter(FirstFragment.this.getActivity(), arrayList);
                            listView.setAdapter(adapter);
                        }
                        else {
                            Toast.makeText(FirstFragment.this.getActivity(), "Doslo je do greske", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(FirstFragment.this.getActivity(), "Profili nisu pronadeni", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

}
