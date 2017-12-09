package edu.fullerton.justin.mymoviereviews;

import android.app.Application;

import edu.fullerton.justin.mymoviereviews.dagger.ApplicationComponent;
import edu.fullerton.justin.mymoviereviews.dagger.ApplicationModule;
import edu.fullerton.justin.mymoviereviews.dagger.DaggerApplicationComponent;
import edu.fullerton.justin.mymoviereviews.dagger.RoomModule;

/**
 * Created by justin on 11/30/17.
 */

public class MovieApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(this ))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
