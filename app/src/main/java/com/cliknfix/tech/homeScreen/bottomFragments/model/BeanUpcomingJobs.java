package com.cliknfix.tech.homeScreen.bottomFragments.model;

public class BeanUpcomingJobs {

    String status,category,date;
    int userImg;

    public BeanUpcomingJobs(String status,String category,String date ,int userImg) {
        this.status = status;
        this.category = category;
        this.date = date;
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
}
