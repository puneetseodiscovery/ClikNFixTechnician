package com.cliknfix.technician.homeScreen.bottomFragments.model;

public class BeanEarnings {

    String status,category,date,earning;
    int userImg;

    public BeanEarnings(String status,String category,String date ,String earning, int userImg) {
        this.status = status;
        this.category = category;
        this.date = date;
        this.earning = earning;
        this.userImg = userImg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserImg() {
        return userImg;
    }

    public void setUserImg(int userImg) {
        this.userImg = userImg;
    }

    public String getEarning() {
        return earning;
    }

    public void setEarning(String earning) {
        this.earning = earning;
    }

}
