package com.s18003389.surplusfoodfinder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.s18003389.surplusfoodapp.R;

public class RegisteredRestaurants extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered_restaraunts);

        // Initialize UI elements
        Button btnRegisteredRestaurants = findViewById(R.id.btnRegisteredRestaurants);

        // Set click listener for btnRegisteredRestaurants
        btnRegisteredRestaurants.setOnClickListener(view -> {
            // Navigate to Dashboard activity (if intended to navigate back to Dashboard)
            Intent intent = new Intent(RegisteredRestaurants.this, Dashboard.class);
            startActivity(intent);
        });

        // Add any other initialization code or listeners as needed
    }
}
