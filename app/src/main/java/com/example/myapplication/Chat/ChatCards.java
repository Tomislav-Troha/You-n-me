package com.example.myapplication.Chat;


import android.graphics.Bitmap;

public class ChatCards {

    private String chatUsername;
    private Bitmap chatImg;
    private String chatGodina;


    public ChatCards(String chatUsername, Bitmap chatImg, String chatGodina) {
       this.chatUsername = chatUsername;
       this.chatImg = chatImg;
       this.chatGodina = chatGodina;
    }

    public String getChatUsername() {return  chatUsername;}
    public void setChatUsername(String chatUsername){this.chatUsername = chatUsername;}

    public Bitmap getChatImg() {return  chatImg;}

    public String getChatGodina() {return  chatGodina;}

}