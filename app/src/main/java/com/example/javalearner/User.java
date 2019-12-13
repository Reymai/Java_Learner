package com.example.javalearner;

public class User {
    private String email;
    private String username;
    private String language;
    private int XP;
    private int complete_quest;

    public User(String email, String username, String language, int XP, int complete_quest, String password) {
        this.email = email;
        this.username = username;
        this.language = language;
        this.XP = XP;
        this.complete_quest = complete_quest;
        this.password = password;
    }

    public User(){
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getComplete_quest() {
        return complete_quest;
    }

    public void setComplete_quest(int complete_quest) {
        this.complete_quest = complete_quest;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
}
