package edu.fullerton.justin.mymoviereviews.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.fullerton.justin.mymoviereviews.model.Movie;

/**
 * Created by justin on 11/26/17.
 */
@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movie movie);

    @Update
    void updateMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);

    @Query("SELECT * FROM Movie ORDER BY date")
    LiveData<List<Movie>> getMoviesByDate();

    @Query("SELECT * FROM Movie ORDER BY rating")
    LiveData<List<Movie>> getMoviesByRating();

    @Query("SELECT * FROM Movie WHERE movieName = :movieName")
    LiveData<Movie> getMovie(String movieName);
}
