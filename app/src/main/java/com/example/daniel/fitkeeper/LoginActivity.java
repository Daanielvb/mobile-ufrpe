package com.example.daniel.fitkeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private TextView emailText;
    private TextView passwordtText;
    private TextView errorText;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (savedInstanceState != null) {
            email = savedInstanceState.getString("email");
            password = savedInstanceState.getString("password");
            atualizaDados();
        }
        loginBtn = (Button) findViewById(R.id.loginBtn);
        emailText = (TextView) findViewById(R.id.editTextMail);
        passwordtText = (TextView) findViewById(R.id.editTextPassword);
        errorText = (TextView) findViewById(R.id.errorTxt);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                salvaDados();
                login(email,password);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("email", email);
        outState.putString("password", password);
    }

    private void atualizaDados() {
        if (email != null)
            emailText.setText(email);
        if(password != null)
            passwordtText.setText(password);
    }

    private void salvaDados(){
        email = emailText.getText().toString();
        password = passwordtText.getText().toString();
    }

    public void login(String email, String password){
            if(!email.equals("") && !password.equals("")){
                errorText.setText("");
                Intent it = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(it);
            }
            else if(email.equals(""))
                errorText.setText(R.string.error_invalid_email);
            else if(password.equals(""))
                errorText.setText(R.string.error_invalid_password);
    }


}
