package com.example.daniel.fitkeeper;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.daniel.fitkeeper.utils.Constants;
import com.example.daniel.fitkeeper.utils.Controller;
import com.example.daniel.fitkeeper.utils.RequestHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
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
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
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

        forgotPassBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent it = new Intent(LoginActivity.this,ResetPasswordActivity.class);
                startActivity(it);
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
//                if(checkCredentials(email,password)) {
//                    Intent it = new Intent(LoginActivity.this, HomeActivity.class);
//                    startActivity(it);
//                }
//               else{
//                   errorText.setText(R.string.error_incorrect_password);
//                }
            }
            else if(email.equals(""))
                errorText.setText(R.string.error_invalid_email);
            else if(password.equals(""))
                errorText.setText(R.string.error_invalid_password);
    }

    public boolean checkCredentials(String email, String password){
        try {
            JSONObject response = Controller.getJSONObjectFromURL(
                    RequestHelper.composeUrlPathWithParam("Persons","username", email), Constants.GET_REQUEST);
            System.out.println(response);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setUI(){
        emailText = (TextView) findViewById(R.id.editTextMail);
        passwordtText = (TextView) findViewById(R.id.editTextPassword);
        forgotPassBtn = (ImageButton) findViewById(R.id.forgotPassBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        errorText = (TextView) findViewById(R.id.errorTxt);
    }



}
