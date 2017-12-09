package edu.fullerton.justin.mymoviereviews.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.fullerton.justin.mymoviereviews.db.MovieRepository;

/**
 * Created by justin on 11/30/17.
 */
@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {
    private final MovieRepository repository;

    @Inject
    public ViewModelFactory(MovieRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EditViewModel.class))
            return (T) new EditViewModel(repository);

        else if (modelClass.isAssignableFrom(RecentViewModel.class))
            return (T) new RecentViewModel(repository);

        else {
            throw new IllegalArgumentException("ViewModel not found");
        }
        //return null;
    }
}
