package com.example.nopikun.Model;

import java.io.Serializable;

public class Password implements Serializable {

    private String title;
    private String username;
    private String password;
    private int id;

    public Password(String title, String username, String password) {
        this.title = title;
        this.username = username;
        this.password = password;
    }

    public Password(String title, String username, String password, int id) {
        this.title = title;
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
