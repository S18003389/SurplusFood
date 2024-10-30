package com.s18003389.surplusfoodfinder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.s18003389.surplusfoodapp.R;

public class GetLocation extends AppCompatActivity {
    private EditText editTextStart;
    private EditText editTextEnd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);

        editTextStart = findViewById(R.id.editTextStart);
        editTextEnd = findViewById(R.id.editTextEnd);
        Button btnGetPath = findViewById(R.id.btnGetPath);

        btnGetPath.setOnClickListener(v -> {
            String startingPoint = editTextStart.getText().toString();
            String endPoint = editTextEnd.getText().toString();
            if (startingPoint.equals("") || endPoint.equals("")) {
                Toast.makeText(this, "Please enter starting location & destination", Toast.LENGTH_SHORT).show();
            } else {
                openMapActivity(startingPoint, endPoint);
            }
        });
    }

    private void openMapActivity(String startingPoint, String endPoint) {
        Intent intent = new Intent(this, Map.class);
        intent.putExtra("starting_point", startingPoint);
        intent.putExtra("end_point", endPoint);
        startActivity(intent);
    }

    public void getPath(View view) {
        // This method is not needed but is here for completeness
    }
}
