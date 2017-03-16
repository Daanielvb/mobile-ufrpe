package com.example.daniel.fitkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.daniel.fitkeeper.utils.Constants;
import com.example.daniel.fitkeeper.utils.Controller;
import com.example.daniel.fitkeeper.utils.RequestHelper;
import com.example.daniel.fitkeeper.utils.Session;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Person;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private TextView emailText;
    private TextView passwordtText;
    private TextView errorText;
    private String email;
    private String password;
    private ImageButton forgotPassBtn;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Session.getInstance().getUser() != null) {
            Intent it = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(it);
        }
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

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                salvaDados();
                login(email, password);
            }
        });

        forgotPassBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, ResetPasswordActivity.class);
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
        if (password != null)
            passwordtText.setText(password);
    }

    private void salvaDados() {
        email = emailText.getText().toString();
        password = passwordtText.getText().toString();
    }

    public void login(String email, String password) {
        if (!email.equals("") && !password.equals("")) {
            errorText.setText("");
            if (checkCredentials(email, password)) {
                Intent it = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(it);
            } else {
                errorText.setText(R.string.error_incorrect_password);
            }
        } else if (email.equals(""))
            errorText.setText(R.string.error_invalid_email);
        else if (password.equals(""))
            errorText.setText(R.string.error_invalid_password);
    }

    public boolean checkCredentials(String email, String password) {
        try {
            JSONObject response = Controller.getJSONObjectFromURL(
                    RequestHelper.composeUrlPathWithParam(Constants.PERSON_ENTITY, "username", email), Constants.GET_REQUEST).getJSONObject(0);
            if (response.getString("password").equals(password)) {
                List<String> workoutList = new ArrayList<String>();
                int[] workouts = new int[3];
                try {
                    JSONArray workoutsList = response.getJSONArray("workouts");
                    for (int i = 0; i < workoutsList.length(); i++) {
                        workouts[i] = Integer.valueOf(workoutsList.get(i).toString());
                    }
                } catch (JSONException ex) {
                    //ex.printStackTrace();
                }
                Person user = new Person(response.getInt("id"), response.getString("name"),
                        response.getString("username"), response.getString("password"),
                        response.getInt("membership"), response.getInt("workoutCounter"), response.getInt("age"),
                        response.getInt("height"), response.getInt("weight"));

                user.setWorkouts(workouts);
                Session.getInstance().setUser(user);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setUI() {
        emailText = (TextView) findViewById(R.id.editTextMail);
        passwordtText = (TextView) findViewById(R.id.editTextPassword);
        forgotPassBtn = (ImageButton) findViewById(R.id.forgotPassBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        errorText = (TextView) findViewById(R.id.errorTxt);
    }


}
