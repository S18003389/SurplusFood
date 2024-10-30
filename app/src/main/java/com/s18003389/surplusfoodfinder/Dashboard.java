package com.s18003389.surplusfoodfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.s18003389.surplusfoodapp.R;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        // Initialize UI elements
        Button btnRegisteredRestaurants = findViewById(R.id.btnRegisteredRestaurants);
        Button btnFoodItems = findViewById(R.id.btnFoodItems);
        Button btnGetLocation = findViewById(R.id.btnGetLocation);
        Button btnViewMap = findViewById(R.id.btnViewMap);
        Button btnLogout = findViewById(R.id.btnLogout);

        // Set click listeners for each button
        btnRegisteredRestaurants.setOnClickListener(view -> {
            Intent intent = new Intent(Dashboard.this, RegisteredRestaurants.class);
            startActivity(intent);
        });

        btnFoodItems.setOnClickListener(view -> {
            Intent intent = new Intent(Dashboard.this, FoodItems.class);
            startActivity(intent);
        });

        btnGetLocation.setOnClickListener(view -> {
            Intent intent = new Intent(Dashboard.this, GetLocation.class);
            startActivity(intent);
        });

        btnViewMap.setOnClickListener(view -> {
            Intent intent = new Intent(Dashboard.this, Map.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(view -> {
            Intent intent = new Intent(Dashboard.this, LOGIN.class);
            startActivity(intent);
        });

        // Add any other initialization code or listeners as needed
    }
}
