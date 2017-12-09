package edu.fullerton.justin.mymoviereviews.util;

import java.util.Comparator;

import edu.fullerton.justin.mymoviereviews.model.Movie;

/**
 * Created by justin on 11/26/17.
 */

public class MovieRatingComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie movie, Movie t1) {
        return (int) (movie.getRating() - t1.getRating());
    }
}
