package com.example.wein;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.wein.DB.AppDataBase;

@Entity(tableName = AppDataBase.MERCHANDISE_TABLE)
public class Merchandise {
    @PrimaryKey(autoGenerate = true)
    private int mMerchandiseId;

    private String mName;
    private String mDescription;
    private double mPrice;
    private int mCartId;

    public Merchandise(String name, String description, double price, int cartId) {
        mName = name;
        mDescription = description;
        mPrice = price;
        mCartId = cartId;
    }

    public int getMerchandiseId() {
        return mMerchandiseId;
    }

    public void setMerchandiseId(int merchandiseId) {
        mMerchandiseId = merchandiseId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public int getCartId() {
        return mCartId;
    }

    public void setCartId(int cartId) {
        mCartId = cartId;
    }

    @Override
    public String toString() {
        return mMerchandiseId + ". " + mName;
    }
}
