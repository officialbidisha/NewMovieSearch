package com.example.moviesearchingapp;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class MovieShortDetails {
    private String title;
    private Bitmap poster;
    private String description;

    public MovieShortDetails(String title, Bitmap poster, String description) {
        this.title = title;
        this.poster = poster;
        this.description = description;
    }
    public MovieShortDetails() {

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getPoster() {
        return poster;
    }

    public void setPoster(Bitmap poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
