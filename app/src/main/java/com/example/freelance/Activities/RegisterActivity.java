package com.example.freelance.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.freelance.db.Api;
import com.example.freelance.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               requestData();
            }
        });
    }

    void requestData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Api.url + "connection.php", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            boolean bol = response.getBoolean("sucess1");
                            if (bol) {
                                Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
                                Log.e("aaa", "aaa");
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                                Log.e("sss", "aasssa");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Failedd", Toast.LENGTH_SHORT).show();
                Log.e("add", error.toString());
            }
        }
        );
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);
    }
}