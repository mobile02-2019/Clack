package br.com.digitalhouse.clackapp.model;

import android.widget.ImageView;

public class User {

    private int id;
    private String nameUser;
    private String emailUser;
    private ImageView imageUser;

    public User() {
    }

    public User(String nameUser, String emailUser) {
        this.nameUser = nameUser;
        this.emailUser = emailUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public ImageView getImageUser() {
        return imageUser;
    }

    public void setImageUser(ImageView imageUser) {
        this.imageUser = imageUser;
    }
}
