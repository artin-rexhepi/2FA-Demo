package com.example.twofactordemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private EditText emailField, passwordField;
    private String hardcodedEmail = "artinrexhepi03@gmail.com";
    private String hardcodedPassword = "mobile123";
    private String generatedOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(v -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();

            if (email.equals(hardcodedEmail) && password.equals(hardcodedPassword)) {
                generatedOTP = EmailSender.generateOTP();
                EmailSender.sendEmailInBackground(email, generatedOTP);
                System.out.println("Generated OTP: " + generatedOTP);
                Log.d("GeneratedOTP", "OTP: " + generatedOTP);
                Toast.makeText(MainActivity.this, "OTP sent to " + email, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
                intent.putExtra("OTP", generatedOTP);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
