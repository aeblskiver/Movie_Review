package edu.fullerton.justin.mymoviereviews.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
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
    private MovieRepository mMovieRepository;

    public EditViewModel(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
    }

    public EditViewModel(MovieRepository movieRepository, String movieName) {
        this(movieRepository);
        //mMovie.setValue(movieRepository.getMovie(movieName));
    }

    public void saveMovie(Movie movie) {
        SaveAsyncTask task = new SaveAsyncTask();
        task.execute(movie);
    }

    public void deleteMovie(Movie movie) {
        DeleteAsyncTask task = new DeleteAsyncTask();
        task.execute(movie);
    }

    public LiveData<Movie> getMovie(String name) {
        return mMovieRepository.getMovie(name);
    }

    private class SaveAsyncTask extends AsyncTask<Movie, Void, Void> {

        @Override
        protected Void doInBackground(Movie... movies) {
            Movie movie = movies[0];
            mMovieRepository.insertMovie(movie);
            return null;
        }
    }

    private class DeleteAsyncTask extends AsyncTask<Movie, Void, Void> {

        @Override
        protected Void doInBackground(Movie... movies) {
            mMovieRepository.deleteMovie(movies[0]);
            return null;
        }
    }
}