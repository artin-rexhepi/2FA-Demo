package com.example.twofactordemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.twofactordemo.WelcomeActivity;

public class VerificationActivity extends AppCompatActivity {
    private EditText otpField;
    private String receivedOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        otpField = findViewById(R.id.otp_field);
        Button verifyButton = findViewById(R.id.verify_button);

        
        receivedOTP = getIntent().getStringExtra("OTP");

        verifyButton.setOnClickListener(v -> {
            String enteredOTP = otpField.getText().toString();

            if (enteredOTP.equals(receivedOTP)) {
                Intent intent = new Intent(VerificationActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(VerificationActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
