package com.example.wein;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.wein.DB.AppDataBase;
import com.example.wein.DB.WeinDAO;
import com.example.wein.databinding.ActivitySignupBinding;

import java.util.List;

public class SignupActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.wein.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.wein.preferencesKey";

    private Button signupButton;
    private EditText enteredUsername;
    private EditText enteredPassword;
    private ActivitySignupBinding mSignupBinding;

    private WeinDAO mWeinDAO;
    private User mUser;

    public static Intent getIntent(Context applicationContext) {
        return new Intent(applicationContext, SignupActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mSignupBinding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = mSignupBinding.getRoot();
        setContentView(view);

        // Get Database
        mWeinDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().WeinDAO();

        signupButton = mSignupBinding.signupButton;
        enteredUsername = mSignupBinding.suUsernameEditText;
        enteredPassword = mSignupBinding.suPasswordEditText;

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> users = mWeinDAO.getAllUsers();
                for (User user : users) {
                    if(user.getUsername().equals(enteredUsername.getText().toString())) {
                        Toast.makeText(SignupActivity.this, "Username Taken", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                mUser = new User(enteredUsername.getText().toString(), enteredPassword.getText().toString(), false);
                mWeinDAO.insert(mUser);
                Intent intent = LoginActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

    }

}