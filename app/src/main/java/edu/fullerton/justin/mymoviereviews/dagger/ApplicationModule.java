package edu.fullerton.justin.mymoviereviews.dagger;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import edu.fullerton.justin.mymoviereviews.MovieApplication;
import edu.fullerton.justin.mymoviereviews.model.Movie;

/**
 * Created by justin on 11/30/17.
 */
@Module
public class ApplicationModule {
    private final MovieApplication mMovieApplication;

    public ApplicationModule(MovieApplication application) {
        mMovieApplication = application;
    }

    @Provides
    MovieApplication provideMovieApplication() {
        return mMovieApplication;
    }

    @Provides
    Application provideApplication() {
        return mMovieApplication;
    }
}
