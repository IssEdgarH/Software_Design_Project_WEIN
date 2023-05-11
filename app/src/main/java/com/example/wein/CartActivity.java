package com.example.wein;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wein.DB.AppDataBase;
import com.example.wein.DB.WeinDAO;
import com.example.wein.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.wein.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.wein.preferencesKey";

    private Button cartToHomeButton;
    private ActivityCartBinding mCartBinding;

    private WeinDAO mWeinDAO;
    private int mUserId;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mCartBinding = ActivityCartBinding.inflate(getLayoutInflater());
        View view = mCartBinding.getRoot();
        setContentView(view);

        mPreferences = this.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        mUserId = mPreferences.getInt(USER_ID_KEY, -1);

        // Get Database
        mWeinDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().WeinDAO();


        cartToHomeButton = mCartBinding.cartToHomeButton;

        cartToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.getIntent(getApplicationContext(), mUserId);
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context applicationContext) {
        return new Intent(applicationContext, CartActivity.class);
    }
}