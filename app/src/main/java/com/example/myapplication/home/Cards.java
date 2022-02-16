package com.example.myapplication.home;

import android.graphics.Bitmap;
import com.parse.ParseObject;

public class Cards {

    private String ime;
    private Bitmap img;
    private String username;
    private String objectId;

    public Cards(String username, Bitmap img) {
        this.img = img;
        this.username = username;
       // this.objectId = objectID;

    }


    public String getObjectID() {
        return objectId;
    }

    public void getObjectID(String objectId) {
        this.objectId = objectId;
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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
