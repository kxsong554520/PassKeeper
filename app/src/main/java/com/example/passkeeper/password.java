package com.example.passkeeper;

public class password {

    private String entiti;
    private String username;
    private String password;
    private String description;
    private int ID;



    public password(String entiti, String username, String password, String description, int ID) {
        this.entiti = entiti;
        this.username = username;
        this.password = password;
        this.description = description;
        this.ID = ID;
    }

    public password(String entiti, String username, String password, String description) {
        this.entiti = entiti;
        this.username = username;
        this.password = password;
        this.description = description;
    }

    public String getEntiti() {
        return entiti;
    }

    public void setEntiti(String entiti) {
        this.entiti = entiti;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
