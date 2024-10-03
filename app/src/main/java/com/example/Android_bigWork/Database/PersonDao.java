package com.example.Android_bigWork.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.Android_bigWork.Entity.Person;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM person_table")
    List<Person> getAll();

    @Insert
    void insert(Person person);

    @Delete
    void delete(Person person);


    @Query("SELECT * FROM person_table WHERE username = :username and password = :password ")
    Person checkLogin(String username, String password);


    @Query("SELECT * FROM person_table WHERE phoneNumber = :phoneNumber and password = :password ")
    Person checkLoginByPhoneNumber(long phoneNumber, String password);


    @Query("SELECT * FROM person_table WHERE username = :username")
    Person checkUsername(String username);


    @Query("SELECT * FROM person_table WHERE phoneNumber = :phoneNumber")
    Person checkPhoneNumber(long phoneNumber);


    @Query("UPDATE person_table SET password = :newPassword WHERE password  = :oldPassword and username = :username")
    void changePassword(String oldPassword, String newPassword, String username);

    //查询用户
    @Query("SELECT * FROM person_table WHERE username = :username")
    Person queryPerson(String username);

    //查询用户的支付密码
    @Query("SELECT payPassword FROM person_table WHERE username = :username")
    int queryPayPassword(String username);

}
