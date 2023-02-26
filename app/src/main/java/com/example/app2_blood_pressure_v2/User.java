package com.example.app2_blood_pressure_v2;

import java.util.Date;

public class User {
    private Integer ID;
    private String username;
    private String password;
    private String name;
    private String birth_date;
    private String gender;

    public void setID(Integer ID) {
        this.ID = ID;
    }
    public Integer getID() {
        return ID;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setBirthDate(String birth_date) {
        this.birth_date = birth_date;
    }
    public String getBirthDate() {
        return birth_date;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }


}
