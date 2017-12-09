package edu.fullerton.justin.mymoviereviews.util;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by justin on 11/26/17.
 */

public class DateTypeConverter {
    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }
}
