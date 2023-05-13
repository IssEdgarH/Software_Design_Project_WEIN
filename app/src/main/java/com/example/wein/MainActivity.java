package com.example.wein;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wein.DB.AppDataBase;
import com.example.wein.DB.WeinDAO;
import com.example.wein.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY = "com.example.wein.MainActivity";
    private static final String USER_ID_KEY = "com.example.wein.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.wein.preferencesKey";

    Button mBrowseButton;
    Button mCartButton;
    Button mOrderButton;
    Button mOrdersButton;
    Button mAdminButton;
    Button mLogoutButton;
    TextView mWelcomeMessage;

    ActivityMainBinding mMainBinding;

    private WeinDAO mWeinDAO;
    private int mUserId = -1;
    private User mUser;

    private SharedPreferences mPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Database
        mWeinDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().WeinDAO();

        checkForUser();
        addUserToPreference(mUserId);

        // Logging in user
        mUser = mWeinDAO.getUserByUserId(mUserId);
//        Not Sure?
//        invalidateOptionsMenu();

        mMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mMainBinding.getRoot());

        mBrowseButton = mMainBinding.browseButton;
        mCartButton = mMainBinding.cartButton;
        mOrderButton = mMainBinding.orderButton;
        mOrdersButton = mMainBinding.ordersButton;
        mLogoutButton = mMainBinding.logoutButton;
        mWelcomeMessage = mMainBinding.welcomeMessage;

        mAdminButton = mMainBinding.adminButton;
        if(mUserId != -1) {
            StringBuilder message = new StringBuilder();
            message.append("Welcome, ");
            message.append(mUser.getUsername());
            message.append("!");
            mWelcomeMessage.setText(message);
            if (mUser.isAdmin()) {
                mAdminButton.setVisibility(View.VISIBLE);
            }
        }

        refreshDisplay();

        mBrowseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = BrowseActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        mCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CartActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        mOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = OrderActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        mOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = OrdersActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear user from intent
                getIntent().putExtra(USER_ID_KEY, -1);
                // Clear user from preferences
                addUserToPreference(-1);
                mUserId = -1;
                checkForUser();
            }
        });

        mAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AdminActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    private void addUserToPreference(int userId) {
        if(mPreferences == null) {
            getPrefs();
        }
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(USER_ID_KEY, userId);
        editor.apply();
    }

    private void checkForUser() {
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);
        if(mUserId != -1) {
            return;
        }
        getPrefs();
        if(mPreferences == null) {
            getPrefs();
        }
        mUserId = mPreferences.getInt(USER_ID_KEY, -1);
        if(mUserId != -1) {
            return;
        }
        List<User> users = mWeinDAO.getAllUsers();
        if(users.size() <= 0) {
            User adminUser = new User("admin", "password", true);
            Cart adminCart = new Cart(0);
            mWeinDAO.insert(adminUser);
        }
        Intent intent = LoginActivity.getIntent(getApplicationContext());
        startActivity(intent);
    }

    private void getPrefs() {
        mPreferences = this.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    private void refreshDisplay() {
        // do something
    }

    public static Intent getIntent(Context applicationContext, int userId) {
        Intent intent = new Intent(applicationContext, MainActivity.class);
        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }
}