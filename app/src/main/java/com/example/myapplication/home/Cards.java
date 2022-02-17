package com.example.myapplication.home;

import android.graphics.Bitmap;
import com.parse.ParseObject;

public class Cards {

    private String ime;
    private Bitmap img;
    private String username;
    private String objectId;
    private String aboutPartner;
    private String aboutYou;

    public Cards(String username, Bitmap img) {
        this.img = img;
        this.username = username;
    }

  /*  public Cards (String aboutPartner, String aboutYou) {
        this.aboutPartner = aboutPartner;
        this.aboutYou = aboutYou;
    }*/


    public String getAboutYou() {
        return aboutYou;
    }
    public void setAboutYou(String aboutYou) {
        this.aboutYou = aboutYou;
    }


    public String getAboutPartner() {
        return aboutPartner;
    }
    public void setAboutPartner(String aboutPartner) {
        this.aboutPartner = aboutPartner;
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
