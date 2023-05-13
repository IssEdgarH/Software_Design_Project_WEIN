package com.example.wein.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.wein.Merchandise;
import com.example.wein.User;

import java.util.List;

@Dao
public interface WeinDAO {
    @Insert
    void insert(User...users);

    @Delete
    void delete(User...users);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE)
    List<User> getAllUsers();

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mUsername = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mUserId = :userId")
    User getUserByUserId(int userId);

    @Insert
    void insert(Merchandise...merchandises);

    @Delete
    void delete(Merchandise...merchandises);

    @Query("SELECT * FROM " + AppDataBase.MERCHANDISE_TABLE)
    List<Merchandise> getAllMerchandise();

    @Query("SELECT * FROM " + AppDataBase.MERCHANDISE_TABLE + " WHERE mMerchandiseId = :merchandiseId")
    Merchandise getMerchandiseById(int merchandiseId);
}
