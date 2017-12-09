package edu.fullerton.justin.mymoviereviews.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import edu.fullerton.justin.mymoviereviews.db.MovieDao;
import edu.fullerton.justin.mymoviereviews.view.EditMovie;
import edu.fullerton.justin.mymoviereviews.view.MostRecentListFragment;
import edu.fullerton.justin.mymoviereviews.viewmodel.RecentViewModel;

/**
 * Created by justin on 11/30/17.
 */
@Singleton
@Component(modules = {ApplicationModule.class, RoomModule.class})
public interface ApplicationComponent {
    void inject(MovieDao movieDao);
    void inject(EditMovie editMovie);
    void inject(RecentViewModel recentViewModel);
    void inject(MostRecentListFragment fragment);

    Application application();
}
