package com.example.wein;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.wein.DB.AppDataBase;
import com.example.wein.DB.WeinDAO;
import com.example.wein.databinding.ActivityBrowseBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrowseActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.wein.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.wein.preferencesKey";

    private ActivityBrowseBinding mBrowseBinding;

    private WeinDAO mWeinDAO;
    private Cart mUserCart;
    private List<Merchandise> mMerchandises;

    private SharedPreferences mPreferences;
    private int mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        mBrowseBinding = ActivityBrowseBinding.inflate(getLayoutInflater());
        View view = mBrowseBinding.getRoot();
        setContentView(view);

        mPreferences = getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        mUserId = mPreferences.getInt(USER_ID_KEY, -1);

        // Get Database
        mWeinDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().WeinDAO();
        mUserCart = mWeinDAO.getCartByUserId(mUserId);
        ListView mBrowseItems = (ListView) findViewById(R.id.browse_items);
        mMerchandises = mWeinDAO.getAllMerchandise();

        ArrayAdapter<Merchandise> arrayAdapter = new ArrayAdapter<Merchandise>(this, android.R.layout.simple_list_item_1, mMerchandises);
        mBrowseItems.setAdapter(arrayAdapter);
        mBrowseItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mUserCart.setItemsCount(mUserCart.getItemsCount() + 1);
            }
        });
    }

    public static Intent getIntent(Context applicationContext) {
        return new Intent(applicationContext, BrowseActivity.class);
    }
}