package com.example.daniel.fitkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import model.Person;

/**
 * Created by Rodrigo on 25/10/2016.
 */
public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    Person p = new Person("Rodrigo", "rodrigocunha@gmail.com", "123456");

    private String password;
    private String email;

    private Button logoutBtn;
    private Button saveBtn;
    private ImageButton backBtn;
    private TextView emailText;
    private TextView passwordText;
    private TextView newPasswordText;
    private TextView confirmPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setUi();
        setPerson(p);
        if (savedInstanceState != null) {
            email = savedInstanceState.getString("email");
            password = savedInstanceState.getString("password");
            atualizaDados();
        }
    }


    public void setUi(){
        saveBtn = (Button) findViewById(R.id.saveBtn);
        logoutBtn = (Button) findViewById(R.id.logoutBtn);
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        emailText = (TextView) findViewById(R.id.emailEdtTxt);
        passwordText = (TextView) findViewById(R.id.crtPassEdtTxt);
        backBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.backBtn:
                finish();
                break;
            case R.id.logoutBtn:
                Intent it = new Intent(SettingsActivity.this,LoginActivity.class);
                startActivity(it);
                break;
        }
    }

    public void setPerson(Person p){
        emailText.setText(p.getEmail());
        passwordText.setText(p.getPassword());
    }

    private void atualizaDados() {
        if (email != null)
            emailText.setText(email);
        if(password != null)
            passwordText.setText(password);

    }

    private void salvaDados(){
        email = emailText.getText().toString();
        password = passwordText.getText().toString();
    }
}
