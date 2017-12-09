package edu.fullerton.justin.mymoviereviews.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import edu.fullerton.justin.mymoviereviews.model.Movie;
import edu.fullerton.justin.mymoviereviews.util.DateTypeConverter;

/**
 * Created by justin on 11/26/17.
 */
@TypeConverters(DateTypeConverter.class)
@Database(entities = {Movie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

//    private static AppDatabase INSTANCE;
//    public static AppDatabase getDatabase(Context context) {
//        if (INSTANCE == null) {
//            INSTANCE =
//                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "AppDatabase.db")
//                            .build();
//        }
//        return INSTANCE;
//    }
    abstract public MovieDao movieDao();
}
