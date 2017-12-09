package edu.fullerton.justin.mymoviereviews.util;

import java.util.Comparator;

import edu.fullerton.justin.mymoviereviews.model.Movie;

/**
 * Created by justin on 11/26/17.
 */

public class MovieDateComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie movie1, Movie movie2) {
        return -(movie1.getDate().compareTo(movie2.getDate()));
    }

}
