package com.minkim.llnx;

import java.io.*;

public class Llnx implements Serializable {

    private String name;
    private String username;
    private String nickname;
    private boolean selected;

    public Llnx(String name, String username, String nickname){
        this.name = name;
        this.username = username;
        this.nickname = nickname;
        this.selected = false;
    }

    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    public String getNickname() {
        return nickname;
    }
    public boolean isSelected() {
        return selected;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void set() {
        this.selected = true;
    }
    public void unset() {
        this.selected = false;
    }
}
