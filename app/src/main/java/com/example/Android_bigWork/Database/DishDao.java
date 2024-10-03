package com.example.Android_bigWork.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.Android_bigWork.Entity.Dish;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DishDao {

    @Query("SELECT * FROM dish_table ORDER BY CID")
    List<Dish> getAllDish();


    @Query("DELETE FROM dish_table")
    void deleteAllDish();


    @Insert
    void insert(Dish dish);

    @Update
    void update(Dish dish);

    @Delete
    void delete(Dish Dish);


    @Query("SELECT * FROM dish_table WHERE name = :name")
    Dish getDishByName(String name);

    @Query("SELECT * FROM dish_table WHERE category = :category")
    List<Dish> getDishByCategory(String category);

    @Query("SELECT COUNT(*) FROM dish_table")
    int getDishCount();

}
