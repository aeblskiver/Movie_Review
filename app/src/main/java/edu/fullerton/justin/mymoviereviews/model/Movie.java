package edu.fullerton.justin.mymoviereviews.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by justin on 11/26/17.
 */
@Entity
public class Movie {
    @NonNull
    @PrimaryKey
    private String movieName;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "rating")
    private float rating;

    public Movie(@NonNull String movieName, Date date, float rating) {
        this.movieName = movieName;
        this.date = date;
        this.rating = rating;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
