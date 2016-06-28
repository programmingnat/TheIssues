package com.imaginat.theissues.models;

/**
 * Created by nat on 6/26/16.
 */
public class Headline {
    private String mHeadline;
    private String mImage;
    private String mSnippet;
    private String mLink;

    public Headline(String headline, String image, String snippet, String link) {
        mHeadline = headline;
        mImage = image;
        mSnippet = snippet;
        mLink = link;
    }

    public String getHeadline() {
        return mHeadline;
    }

    public void setHeadline(String headline) {
        mHeadline = headline;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getSnippet() {
        return mSnippet;
    }

    public void setSnippet(String snippet) {
        mSnippet = snippet;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }
}
