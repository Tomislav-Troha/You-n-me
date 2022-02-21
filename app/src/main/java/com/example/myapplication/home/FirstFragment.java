package com.example.myapplication.home;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.parse.*;

public class FirstFragment extends Fragment {



    public FirstFragment() {
        // require a empty public constructor
    }

    RecyclerView listView;
    ArrayList < Cards > arrayList = new ArrayList < > ();
    Adapter adapter;

    String getGendre = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        listView = view.findViewById(R.id.recycler_view);
        getCards();

        return view;
    }

    String aboutYou = "";
    String aboutPartner = "";
    String userTraziSpol = "";
    String godine_od = "";
    String godine_do = "";
    String godinaRodenja = "";
    String birthday = "";
    Bitmap bitmap = null;
    String username = "";






    public void getCards() {
        ParseQuery < ParseUser > query1 = ParseUser.getQuery();
        query1.whereEqualTo("email", ParseUser.getCurrentUser().getEmail());

        query1.getFirstInBackground((object1, e2) -> {
            if (e2 == null) {
                getGendre = object1.getString("id_trazis_spol");

                ParseQuery < ParseUser > query = ParseUser.getQuery();
                query.whereNotEqualTo("email", ParseUser.getCurrentUser().getEmail());
                //Toast.makeText(FirstFragment.this.getActivity(), getGendre, Toast.LENGTH_SHORT).show();
                query.whereNotEqualTo("id_trazis_spol", getGendre);

                query.findInBackground((objects, e) -> {
                    arrayList = new ArrayList < > ();

                    if(e == null) {

                        for (ParseObject object: objects) {

                            ParseFile imageFile = (ParseFile) object.get("Profil_image");
                            if (imageFile != null) {

                                imageFile.getDataInBackground((data, e1) -> {


                                    if (e1 == null) {


                                        bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                                        if (object.get("about_partner") == null) {
                                            aboutPartner = "";
                                        } else {
                                            aboutPartner = object.get("about_partner").toString();
                                        }

                                        if (object.get("about_you") == null) {
                                            aboutYou = "";
                                        } else {
                                            aboutYou = object.get("about_you").toString();
                                        }

                                        if (object.get("User_traziSpol") == null) {
                                            userTraziSpol = "";
                                        } else {
                                            userTraziSpol = object.get("User_traziSpol").toString();
                                        }

                                        if (object.get("godina_od") == null) {
                                            godine_od = "";
                                        } else {
                                            godine_od = object.get("godina_od").toString();
                                        }

                                        if (object.get("godina_do") == null) {
                                            godine_do = "";
                                        } else {
                                            godine_do = object.get("godina_do").toString();
                                        }


                                        godinaRodenja = object.get("Datum_rodenja").toString();
                                        String[] parts = godinaRodenja.split("/");
                                        int year = Calendar.getInstance().get(Calendar.YEAR);
                                        int date = Integer.parseInt(parts[2]);
                                        int intBirthday = year - date;
                                        birthday = String.valueOf(intBirthday);

                                        username = (object.get("username").toString());

                                        //Toast.makeText(FirstFragment.this.getActivity(),aboutPartner, Toast.LENGTH_SHORT).show();
                                        Cards cards = new Cards(username, bitmap, aboutPartner, aboutYou, userTraziSpol, godine_od, godine_do, birthday);
                                        arrayList.add(cards);
                                        listView.setLayoutManager(new LinearLayoutManager(FirstFragment.this.getActivity()));
                                        adapter = new Adapter(FirstFragment.this.getActivity(), arrayList);
                                        listView.setAdapter(adapter);

                                    } else {
                                        Toast.makeText(FirstFragment.this.getActivity(), e1.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                });
                            } else {
                                Toast.makeText(FirstFragment.this.getActivity(), getString(R.string.dosloJeDoGreske), Toast.LENGTH_SHORT).show();
                            }

                        }
                    } else {
                        Toast.makeText(FirstFragment.this.getActivity(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                Toast.makeText(FirstFragment.this.getActivity(), e2.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

        });


    }
}