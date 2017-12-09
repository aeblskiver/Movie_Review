package edu.fullerton.justin.mymoviereviews.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import java.util.Date;

import edu.fullerton.justin.mymoviereviews.db.MovieRepository;
import edu.fullerton.justin.mymoviereviews.model.Movie;
import edu.fullerton.justin.mymoviereviews.util.DateTypeConverter;

/**
 * Created by justin on 11/29/17.
 */

public class EditViewModel extends ViewModel {
    LiveData<Movie> mMovie;
    private MovieRepository mMovieRepository;

    public EditViewModel(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
    }

    public EditViewModel(MovieRepository movieRepository, String movieName) {
        this(movieRepository);
        mMovie = movieRepository.getMovie(movieName);
    }

    public void saveMovie(Movie movie) {
        SaveAsyncTask task = new SaveAsyncTask();
        task.execute(movie);
    }

    private class SaveAsyncTask extends AsyncTask<Movie, Void, Void> {

        @Override
        protected Void doInBackground(Movie... movies) {
            Movie movie = movies[0];
            mMovieRepository.insertMovie(movie);
            return null;
        }
    }
}