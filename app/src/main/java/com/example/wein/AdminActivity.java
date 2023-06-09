package com.example.wein;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wein.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.wein.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.wein.preferencesKey";

    Button mAdminToHomeButton;
    Button mAddMerchandiseButton;
    Button mDeleteMerchandiseButton;
    ActivityAdminBinding mAdminBinding;

    private SharedPreferences mPreferences;
    private int mUserId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        mAdminBinding = ActivityAdminBinding.inflate(getLayoutInflater());
        View view = mAdminBinding.getRoot();
        setContentView(view);

        mPreferences = getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        mUserId = mPreferences.getInt(USER_ID_KEY, -1);

        mAdminToHomeButton = mAdminBinding.adminToHomeButton;
        mAddMerchandiseButton = mAdminBinding.addMerchandiseButton;
        mDeleteMerchandiseButton = mAdminBinding.deleteMerchandiseButton;

        mAdminToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.getIntent(getApplicationContext(), mUserId);
                startActivity(intent);
            }
        });

        mAddMerchandiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddMerchandiseActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        mDeleteMerchandiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = DeleteMerchandiseActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

    }

    public static Intent getIntent(Context applicationContext) {
        return new Intent(applicationContext, AdminActivity.class);
    }
}
