package com.example.freelance.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.freelance.R;
import com.example.freelance.databinding.ActivityPreLoginBinding;

public class PreLoginActivity extends AppCompatActivity {

    ActivityPreLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPreLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnSignup.setOnClickListener(view -> {
            Intent intent = new Intent(this, UserRegistrationActivity.class);
            startActivity(intent);
        });
        binding.btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, UserLoginActivity.class);
            startActivity(intent);
        });
        onClicks();
    }
    private void onClicks() {


    }
}