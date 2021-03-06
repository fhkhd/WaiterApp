package com.example.waiterapp.database.dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.waiterapp.model.User;

@Dao
public interface UserDao {

    @Query("Select * from user where name = :name AND password= :password   limit 1 ")
    User getUser(String name , String password);


    @Insert
    void insertUser(User user);
    @Update
    void updateUser(User user);
    @Delete
    void deleteUser(User user);

}