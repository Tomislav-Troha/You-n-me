package com.example.myapplication.home;

import android.graphics.Bitmap;
import com.parse.ParseObject;

public class Cards {

    private String ime;
    private Bitmap img;

    public Cards(String ime, Bitmap img) {
        this.ime = ime;
        this.img = img;

    }



    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }


}
