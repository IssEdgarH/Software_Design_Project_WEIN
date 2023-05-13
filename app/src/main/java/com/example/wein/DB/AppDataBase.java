package com.example.wein.DB;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.wein.Cart;
import com.example.wein.Merchandise;
import com.example.wein.User;
//autoMigrations = {@AutoMigration(from = 1, to = 2)}
@Database(entities = {User.class, Merchandise.class, Cart.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "Wein.db";
    public static final String USER_TABLE = "user_table";
    public static final String MERCHANDISE_TABLE = "merchandise_table";
    public static final String CART_TABLE = "cart_table";

    private static volatile AppDataBase instance;
    private static final Object LOCK = new Object();

    public abstract WeinDAO WeinDAO();

    public static AppDataBase getInstance(Context context) {
        if(instance == null) {
            synchronized (LOCK) {
                if(instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
