package com.cliknfix.tech.ratingsReview.model;

public class BeanRating {

    String techName,text;
    int techImg;
    float rating;

    public BeanRating(int techImg,String techName,float rating ,String text) {
        this.techImg = techImg;
        this.techName = techName;
        this.rating = rating;
        this.text = text;
    }

    public int getTechImg() {
        return techImg;
    }

    public void setTechImg(int techImg) {
        this.techImg = techImg;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
