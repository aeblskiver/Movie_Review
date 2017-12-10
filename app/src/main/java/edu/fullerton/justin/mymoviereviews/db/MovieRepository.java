package edu.fullerton.justin.mymoviereviews.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import edu.fullerton.justin.mymoviereviews.db.AppDatabase;
import edu.fullerton.justin.mymoviereviews.db.MovieDao;
import edu.fullerton.justin.mymoviereviews.model.Movie;

/**
 * Created by justin on 11/26/17.
 */

public class MovieRepository {
    private final MovieDao mMovieDao;

    @Inject
    public MovieRepository(MovieDao movieDao) {
        mMovieDao = movieDao;
    }

    public LiveData<List<Movie>> getListOfMovies() {
        return mMovieDao.getMoviesByDate();
    }

    public LiveData<Movie> getMovie(String name) {
        return mMovieDao.getMovie(name);
    }

    public void insertMovie(Movie movie) {
        mMovieDao.insertMovie(movie);
    }

    public void deleteMovie(Movie movie) {
        mMovieDao.deleteMovie(movie);
    }
}
