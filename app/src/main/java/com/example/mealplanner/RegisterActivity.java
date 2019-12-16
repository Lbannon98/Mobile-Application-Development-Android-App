package com.example.mealplanner;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//This activity handles the User Registration to Firebase

public class RegisterActivity extends AppCompatActivity {

    private EditText name, email, password;
    private Button registerBtn;
    private TextView loginNow;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.register_name);
        email = (EditText) findViewById(R.id.register_email);
        password = (EditText) findViewById(R.id.register_password);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        loginNow = findViewById(R.id.login_to_account);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    public void registerUser() {
        String registerEmail = email.getText().toString().trim();
        String registerPassword = password.getText().toString().trim();

        if(TextUtils.isEmpty(registerEmail)) {
            email.setError(getString(R.string.email_error));
            return;
        }

        if(TextUtils.isEmpty(registerPassword)) {
            password.setError(getString(R.string.password_error));
            return;
        }

        if(password.length() < 6) {
            password.setError(getString(R.string.password_length_error));
            return;
        }

        //Firebase Registration
        firebaseAuth.createUserWithEmailAndPassword(registerEmail, registerPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, R.string.register_successful, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(RegisterActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

}
