package com.s18003389.surplusfoodfinder;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.s18003389.surplusfoodapp.R;

public class FoodItems extends AppCompatActivity {
    FoodItemsDatabaseHelper myDb;
    Button btnReadMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_items);  // Assuming your layout file is correct

        myDb = new FoodItemsDatabaseHelper(this);
        btnReadMore = findViewById(R.id.btnReadMore);  // Replace with your button ID

        btnReadMore.setOnClickListener(v -> {
            Cursor result = myDb.getAllData();  // Fetching all data
            if (result.getCount() == 0) {
                showMessage("Error Message", "No data found");
                return;
            }

            StringBuilder buffer = new StringBuilder();
            while (result.moveToNext()) {
                // Assuming the third column (index 2) contains the type of expense
                String FoodDescription = result.getString(2);

                // Check if the expense type is related to food items
                if (FoodDescription.equalsIgnoreCase("food") || FoodDescription.equalsIgnoreCase("restaurant")) {
                    buffer.append("Food Item Title ").append(result.getString(0)).append("\n");
                    buffer.append("Food Description ").append(FoodDescription).append("\n");
                    buffer.append("Amount: ").append(result.getString(1)).append("\n\n");

                }
            }

            showMessage("List of Food Items", buffer.toString());
        });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}
