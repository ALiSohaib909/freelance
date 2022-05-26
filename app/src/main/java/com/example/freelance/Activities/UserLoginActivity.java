package com.example.freelance.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.freelance.R;
import com.example.freelance.databinding.ActivityPreLoginBinding;
import com.example.freelance.databinding.ActivityUserLoginBinding;
import com.example.freelance.db.DatabaseHelper;

import java.io.IOException;

public class UserLoginActivity extends AppCompatActivity {
    ActivityUserLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnSignup.setOnClickListener(view -> validateUser());
    }

    private void validateUser() {
        DatabaseHelper db = new DatabaseHelper(UserLoginActivity.this);
        try {
            db.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            db.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        db.createJobTable(null);
        Cursor cursor = db.get("tbl_Users",
                null,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();

        while (cursor.moveToNext()) {

            if (binding.tvEnterEmail.getText().toString().equals(cursor.getString(2)) &&
                    binding.tvEnterName.getText().toString().equals(cursor.getString(3))) {
                startActivity(new Intent(this, MainActivity.class));
            } else binding.textView14.setText("Invalid Credentials");
        }
    }
}