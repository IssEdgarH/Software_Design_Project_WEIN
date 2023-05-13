package com.example.wein;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.wein.DB.AppDataBase;
import com.example.wein.DB.WeinDAO;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class DeleteMerchandiseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_merchandise);

        WeinDAO weinDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().WeinDAO();

        RecyclerView recyclerView = findViewById(R.id.merchandise_rv);
        List<Merchandise> mMerchandise = weinDAO.getAllMerchandise();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MerchandiseAdapter(getApplicationContext(), mMerchandise));
    }

    public static Intent getIntent(Context applicationContext) {
        return new Intent(applicationContext, DeleteMerchandiseActivity.class);
    }
}