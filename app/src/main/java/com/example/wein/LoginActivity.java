package com.example.wein;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.wein.DB.AppDataBase;
import com.example.wein.DB.WeinDAO;

public class LoginActivity extends AppCompatActivity{

    private EditText mUsernameField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private Button mSignUpButton;

    private WeinDAO mWeinDAO;

    private String mUsername;
    private String mPassword;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get database
        mWeinDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().WeinDAO();

        mUsernameField = findViewById(R.id.username_edit_text);
        mPasswordField = findViewById(R.id.password_edit_text);
        mLoginButton = findViewById(R.id.login_button);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername = mUsernameField.getText().toString();
                mPassword = mPasswordField.getText().toString();

                if(mUsername.equals("") || mPassword.equals("")) {
                    Toast.makeText(LoginActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                }
                else if(checkForUserInDatabase()) {
                    if(!(mUser.getPassword().equals(mPassword))) {
                        Toast.makeText(LoginActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = MainActivity.getIntent(getApplicationContext(),mUser.getUserId());
                        startActivity(intent);
                    }
                }
            }
        });

        mSignUpButton = findViewById(R.id.login_to_signup_button);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = SignupActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    private boolean checkForUserInDatabase() {
        mUser = mWeinDAO.getUserByUsername(mUsername);
        if(mUser == null) {
            Toast.makeText(this, "No user " + mUsername + " found", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static Intent getIntent(Context applicationContext) {
        return new Intent(applicationContext, LoginActivity.class);
    }
}
