package com.example.app2_blood_pressure_v2;

import java.util.Date;

public class PressureRead {
    private int id;
    private Integer userID;
    private int systolic_pressure;
    private int diastolic_pressure;
    private int heart_rate;
    private String time_read;


    public void setPressureReadId(int id) {
        this.id = id;
    }
    public int getPressureReadId() {
        return id;
    }

    public void setPressureReadUserID(Integer userID) {
        this.userID = userID;
    }
    public int getPressureReadUserID() {
        return userID;
    }

    public void setSystolicPressure(int systolic_pressure) {
        this.systolic_pressure = systolic_pressure;
    }
    public int getSystolicPressure() {
        return systolic_pressure;
    }

    public void setDiastolicPressure(int diastolic_pressure) {
        this.diastolic_pressure = diastolic_pressure;
    }
    public int getDiastolicPressure() {
        return diastolic_pressure;
    }

    public void setHeartRate(int heart_rate) {
        this.heart_rate = heart_rate;
    }
    public int getHeartRate() {
        return heart_rate;
    }

    public void setTimeRead(String time_read) {
        this.time_read = time_read;
    }
    public String getTimeRead() {
        return time_read;
    }


}
