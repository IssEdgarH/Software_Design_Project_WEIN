package com.example.wein;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.wein.DB.AppDataBase;

@Entity(tableName = AppDataBase.CART_TABLE)
public class Cart {
    @PrimaryKey(autoGenerate = true)
    private int cartId;

    private int itemsCount;

    public Cart(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }
}
