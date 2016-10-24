package com.example.daniel.fitkeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private TextView emailText;
    private TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        emailText = (TextView) findViewById(R.id.editTextMail);
        password = (TextView) findViewById(R.id.editTextPassword);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                login(emailText.getText().toString(),password.getText().toString());
            }
        });
    }


    public void login(String email, String password){
            if(!email.equals("") && !password.equals("")){
                Intent it = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(it);
            }
    }


}
