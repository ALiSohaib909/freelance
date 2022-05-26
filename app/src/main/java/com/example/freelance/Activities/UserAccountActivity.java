package com.example.freelance.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;

import com.example.freelance.databinding.ActivityUserAccountBinding;
import com.example.freelance.db.DatabaseHelper;

import java.io.IOException;

public class UserAccountActivity extends AppCompatActivity {

    ActivityUserAccountBinding binding;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.ibAddIntersets.setOnClickListener(view -> {addInterest();});
        define();
        logout();

    }

    private void logout() {
        binding.btnLogout.setOnClickListener(view -> {
            startActivity(new Intent(this, PreLoginActivity.class));
        });
    }

    private void addInterest() {

    }

    private void define() {
        DatabaseHelper db = new DatabaseHelper(UserAccountActivity.this);
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
      cursor =  db.get("tbl_loginUser", null, null, null,
                null, null, null);
        cursor.moveToFirst();
        String name = cursor.getString(1);
        String email = cursor.getString(2);
        String type = cursor.getString(3);
        binding.tvAccountName.setText("Name:  "+name);
        binding.tvAccountEmail.setText("Email:  "+email);
        binding.tvAccountType.setText("Type:  "+type);

    }
}