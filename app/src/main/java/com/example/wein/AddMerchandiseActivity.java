package com.example.wein;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.wein.DB.AppDataBase;
import com.example.wein.DB.WeinDAO;
import com.example.wein.databinding.ActivityAddMerchandiseBinding;

public class AddMerchandiseActivity extends AppCompatActivity {

    Button mAddMerchandiseButton;
    Button mAddMerchandiseToAdminButton;
    EditText mEnteredMerchandiseName;
    EditText mEnteredMerchandiseDescription;
    EditText mEnteredMerchandisePrice;
    ActivityAddMerchandiseBinding mAddMerchandiseBinding;

    private WeinDAO mWeinDAO;
    private Merchandise mMerchandise;
    private String mMerchandiseName;
    private String mMerchandiseDescription;
    private double mMerchandisePrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_merchandise);
        mAddMerchandiseBinding = ActivityAddMerchandiseBinding.inflate(getLayoutInflater());
        View view = mAddMerchandiseBinding.getRoot();
        setContentView(view);

        mWeinDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().WeinDAO();

        mEnteredMerchandiseName = mAddMerchandiseBinding.merchandiseNameEditText;
        mEnteredMerchandiseDescription = mAddMerchandiseBinding.merchandiseDescriptionEditText;
        mEnteredMerchandisePrice = mAddMerchandiseBinding.enteredMerchandisePrice;

        mAddMerchandiseButton = mAddMerchandiseBinding.addMerchandise;
        mAddMerchandiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMerchandiseName = mEnteredMerchandiseName.getText().toString();
                mMerchandiseDescription = mEnteredMerchandiseDescription.getText().toString();
                if(!mEnteredMerchandisePrice.getText().toString().equals("")) {
                    mMerchandisePrice = Double.parseDouble(mEnteredMerchandisePrice.getText().toString());
                }
                if(mMerchandiseName.equals("") || mMerchandiseDescription.equals("") || mEnteredMerchandisePrice.getText().toString().equals("")) {
                    Toast.makeText(AddMerchandiseActivity.this, "There Is An Empty Field", Toast.LENGTH_SHORT).show();
                }
                else {
                    mMerchandise = new Merchandise(mMerchandiseName, mMerchandiseDescription, mMerchandisePrice, -1);
                    mWeinDAO.insert(mMerchandise);
                    Toast.makeText(AddMerchandiseActivity.this, "Merchandise Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mAddMerchandiseToAdminButton = mAddMerchandiseBinding.addMerchandiseToAdminButton;
        mAddMerchandiseToAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AdminActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context applicationContext) {
        return new Intent(applicationContext, AddMerchandiseActivity.class);
    }
}
