package com.example.daniel.fitkeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginBtn;
    private TextView emailText;
    private TextView passwordtText;
    private TextView errorText;
    private String email;
    private String password;
    private ImageButton forgotPassBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUI();
        if (savedInstanceState != null) {
            email = savedInstanceState.getString("email");
            password = savedInstanceState.getString("password");
            atualizaDados();
        }

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

    public void setUI(){
        emailText = (TextView) findViewById(R.id.editTextMail);
        passwordtText = (TextView) findViewById(R.id.editTextPassword);
        forgotPassBtn = (ImageButton) findViewById(R.id.forgotPassBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        errorText = (TextView) findViewById(R.id.errorTxt);
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.loginBtn:
                salvaDados();
                login(email,password);
                break;
            case R.id.forgotPassBtn:
                Intent it = new Intent(LoginActivity.this,ResetPasswordActivity.class);
                startActivity(it);
                break;
        }
    }


}
