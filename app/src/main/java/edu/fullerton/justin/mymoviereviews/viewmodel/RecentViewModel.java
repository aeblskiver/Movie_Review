package edu.fullerton.justin.mymoviereviews.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import java.util.List;

import edu.fullerton.justin.mymoviereviews.db.AppDatabase;
import edu.fullerton.justin.mymoviereviews.db.MovieRepository;
import edu.fullerton.justin.mymoviereviews.model.Movie;

/**
 * Created by justin on 11/26/17.
 */

public class RecentViewModel extends ViewModel {
    private MutableLiveData<List<Movie>> movies;
    private MovieRepository movieRepository;
    public RecentViewModel(MovieRepository repository) {
        this.movieRepository = repository;
    }
    public LiveData<List<Movie>> getMovies() {
        return movieRepository.getListOfMovies();
    }

}
