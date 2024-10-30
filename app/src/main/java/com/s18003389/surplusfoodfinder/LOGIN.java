package com.s18003389.surplusfoodfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.s18003389.surplusfoodapp.R;

public class LOGIN extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;
    RegistrationDatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        DB = new RegistrationDatabaseHelper(this);

        // Set a click listener for the login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve entered username and password
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LOGIN.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean checkUserPass = DB.checkUsernamePassword(username, password);
                    if (checkUserPass) {
                        // Successful login
                        Toast.makeText(LOGIN.this, "Login successful", Toast.LENGTH_SHORT).show();
                        // Navigate to Dashboard activity
                        Intent intent = new Intent(LOGIN.this, Dashboard.class);
                        startActivity(intent);
                        finish(); // Close current activity
                    } else {
                        // Failed login
                        Toast.makeText(LOGIN.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
