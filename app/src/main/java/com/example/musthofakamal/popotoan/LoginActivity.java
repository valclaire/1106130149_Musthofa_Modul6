package com.example.musthofakamal.popotoan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText email, pass;
    Button daftar, masuk;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Popotoan");

        email = (EditText) findViewById(R.id.edtEmail);
        pass = (EditText) findViewById(R.id.edtPass);
        masuk = (Button) findViewById(R.id.btnLogin);
        daftar = (Button) findViewById(R.id.btnRegist);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            Intent pindah = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(pindah);
            finish();
        }
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(pindah);
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
            }
        });
    }
    public void userLogin(){
        String mEmail = email.getText().toString().trim();
        String mPass = pass.getText().toString().trim();

        if(TextUtils.isEmpty(mEmail)){
            Toast.makeText(LoginActivity.this, "Masukkan Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(mPass)){
            Toast.makeText(LoginActivity.this, "Masukkan Password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(mEmail, mPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login berhasil", Toast.LENGTH_SHORT).show();
                    Intent pindah = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(pindah);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });

    }
}