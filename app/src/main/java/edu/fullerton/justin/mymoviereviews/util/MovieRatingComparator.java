package edu.fullerton.justin.mymoviereviews.util;

import android.util.Log;

import java.util.Comparator;

import edu.fullerton.justin.mymoviereviews.model.Movie;

/**
 * Created by justin on 11/26/17.
 */

public class MovieRatingComparator implements Comparator<Movie> {
    private static final String TAG = "Comparator";

    @Override
    public int compare(Movie movie1, Movie movie2) {
        if (movie1.getRating() < movie2.getRating()) return 1;
        if (movie1.getRating() > movie2.getRating()) return -1;
        return 0;

//        return (int) Math.ceil(movie1.getRating() - movie2.getRating());
    }
}
