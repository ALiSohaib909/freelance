package com.example.freelance.Activities;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.freelance.db.Api;
import com.example.freelance.R;
import com.example.freelance.db.DatabaseHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserRegistrationActivity extends AppCompatActivity {

    EditText editTextUserName;
    EditText editTextUserEmail;
    EditText editTextUserPassword;
    EditText editTextUserPassRetype;
    EditText editTextUserType;
    ProgressDialog dialog;
    Button btnRegister;
    ContentValues contentValues = new ContentValues();
    ContentValues cv = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();
        dialog = new ProgressDialog(this);
        btnRegister.setOnClickListener(view -> addNewUser());
    }

    private void addNewUser() {
        DatabaseHelper db = new DatabaseHelper(UserRegistrationActivity.this);
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
        dialog.setTitle("Creating account");
        dialog.setMessage("Please wait");
        dialog.show();
        final String name = editTextUserName.getText().toString().trim();
        final String email = editTextUserEmail.getText().toString().trim();
        final String password = editTextUserPassword.getText().toString().trim();
        final String location = editTextUserPassRetype.getText().toString().trim();
        final String userType = editTextUserType.getText().toString().trim();

        if (name.equals("") && email.equals("") && password.equals("")) {
            dialog.dismiss();
            Toast.makeText(getApplicationContext(), "Please enter correct details", Toast.LENGTH_SHORT).show();
        } else {
            contentValues.put("UserName", name);
            contentValues.put("UserEmail", email);
            contentValues.put("UserPassword", password);
            contentValues.put("Role", userType);
            contentValues.put("Location", location);
            cv.put("id", "1");
            cv.put("name", name);
            cv.put("email", email);
            cv.put("type", userType);
            db.insertIntoTable("tbl_Users", contentValues);
            db.createUserInterestTable(null);
            db.createJobTable(null);
            db.createLoginTable(null);
            db.insertIntoTable("tbl_loginUser",cv);
            db.update("tbl_loginUser",cv,"1");
            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    private void init() {
        editTextUserName = findViewById(R.id.tv_enterName);
        editTextUserEmail = findViewById(R.id.tv_enterEmail);
        editTextUserPassword = findViewById(R.id.tv_enterPassword);
        editTextUserPassRetype = findViewById(R.id.tv_enterPasswordConfirm);
        editTextUserType = findViewById(R.id.tv_enterType);
        btnRegister = findViewById(R.id.btnRegister);
    }

    public void registerUser() {
        dialog.setTitle("Creating account");
        dialog.setMessage("Please wait");
        //   dialog.show();
        final String name = editTextUserName.getText().toString().trim();
        final String email = editTextUserEmail.getText().toString().trim();
        final String password = editTextUserPassword.getText().toString().trim();
        final String passConfirm = editTextUserPassRetype.getText().toString().trim();
        final String userType = editTextUserType.getText().toString().trim();

        if (name.equals("") && email.equals("") && password.equals("")) {
            dialog.dismiss();
            Toast.makeText(getApplicationContext(), "Please enter correct details", Toast.LENGTH_SHORT).show();
        } else {
            //     doStuffUser();
            StringRequest request = new StringRequest(
                    Request.Method.POST, Api.url_insert, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    Log.d("qwe", response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("qwe", error.toString());
                }
            }
            ) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("name", name);
                    map.put("email", email);
                    map.put("password", password);
                    map.put("confirm_pass", passConfirm);
                    map.put("user_type", userType);
                    return map;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(request);
        }

    }
}





