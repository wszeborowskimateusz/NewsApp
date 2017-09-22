package com.example.android.newsapp;

/**
 * Created by Mateusz on 22/09/2017.
 * Class to represent a sing news in an app
 */

public class News {

    //The title of the article
    private String mTitle;

    //The section name where the article is
    private String mSectionName;

    //The url to see the whole article
    private String mUrl;

    /**
     * @param mTitle is a title of the article
     * @param mSectionName is the name of the section where the article is
     * @param mUrl is the URL to see the whole article
     */
    public News(String mTitle, String mSectionName, String mUrl) {
        this.mTitle = mTitle;
        this.mSectionName = mSectionName;
        this.mUrl = mUrl;
    }

    /**
     * @return the title of the article
     */
    public String getmTitle() {
        return mTitle;
    }

    /**
     * @return the name of the section
     */
    public String getmSectionName() {
        return mSectionName;
    }

    /**
     * @return the URL of the particular Article
     */
    public String getmUrl() {
        return mUrl;
    }
}
