package com.example.Android_bigWork.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.Android_bigWork.Entity.Dish;

@Database(entities = {Dish.class}, version = 2, exportSchema = false)
public abstract class DishDatabase extends RoomDatabase {
    private static final String DB_NAME = "dish.db";
    private static DishDatabase INSTANCE;


    public static synchronized DishDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DishDatabase.class,
                            DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public abstract DishDao getDishDao();


}


