package com.example.android.news;

public class News {
    private String mTitle;
    private String mDescription;
    private  String mDate;
    private String mUrl;
    private String mUrlImage;

    public News(String title, String description, String date, String url, String urlImage) {
        mTitle = title;
        mDescription = description;
        mDate = date;
        mUrl = url;
        mUrlImage = urlImage;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getDescription(){
        return mDescription;
    }

    public String getDate(){
        return mDate;
    }

    public String getUrl(){
        return mUrl;
    }

    public String getUrlImage(){
        return mUrlImage;
    }


}
