package edu.fullerton.justin.mymoviereviews.dagger;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.fullerton.justin.mymoviereviews.MovieApplication;
import edu.fullerton.justin.mymoviereviews.db.MovieRepository;
import edu.fullerton.justin.mymoviereviews.db.AppDatabase;
import edu.fullerton.justin.mymoviereviews.db.MovieDao;
import edu.fullerton.justin.mymoviereviews.viewmodel.ViewModelFactory;

/**
 * Created by justin on 11/30/17.
 */
@Module
public class RoomModule {
    private final AppDatabase appDatabase;

    public RoomModule(Application application) {
        this.appDatabase = Room.databaseBuilder(
                application,
                AppDatabase.class,
                "AppDatabase.db"
        ).build();
    }

    @Provides
    @Singleton
    MovieRepository provideMovieRepository(MovieDao movieDao) {
        return new MovieRepository(movieDao);
    }

    @Provides
    @Singleton
    MovieDao provideMovieDao(AppDatabase appDatabase) {
        return appDatabase.movieDao();
    }

    @Provides
    @Singleton
    AppDatabase provideDatabase(Application application) {
        return appDatabase;
    }

    //Custom view model factory maybe
    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(MovieRepository movieRepository) {
        return new ViewModelFactory(movieRepository);
    }
}
