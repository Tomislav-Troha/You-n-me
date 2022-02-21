package com.example.myapplication.home;

import android.graphics.Bitmap;

public class Cards {

    private String ime;
    private Bitmap img;
    private String username;
    private String godinaRodenja;
    private String aboutPartner;
    private String aboutYou;
    private String traziSpol;
    private String godine_do;
    private String godine_od;

    public Cards(String username, Bitmap img, String aboutPartner, String aboutYou, String traziSpol, String godine_od, String godine_do, String godinaRodenja) {
        this.img = img;
        this.username = username;
        this.aboutPartner = aboutPartner;
        this.aboutYou = aboutYou;
        this.traziSpol = traziSpol;
        this.godine_od = godine_od;
        this.godine_do = godine_do;
        this.godinaRodenja = godinaRodenja;
    }

    public String getGodinaRodenja() {
        return godinaRodenja;
    }
    public void setGodinaRodenja(String godinaRodenja) {
        this.godinaRodenja = godinaRodenja;
    }

    public String getGodine_do() {
        return godine_do;
    }
    public void setGodine_do(String godine_do) {
        this.godine_do = godine_do;
    }

    public String getGodine_od() {
        return godine_od;
    }
    public void setGodine_od(String godine_od) {
        this.godine_od = godine_od;
    }

    public String getTraziSpol() {
        return traziSpol;
    }
    public void setTraziSpol(String traziSpol) {
        this.traziSpol = traziSpol;
    }

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