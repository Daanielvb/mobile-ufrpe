package com.example.daniel.fitkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.daniel.fitkeeper.utils.Controller;
import com.example.daniel.fitkeeper.utils.Session;

import model.Person;

/**
 * Created by Rodrigo on 25/10/2016.
 */
public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    Person p = Session.getInstance().getUser();

    private String password;
    private String email;
    private String newPassword;
    private String confirmPassword;
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
            newPassword = savedInstanceState.getString("newPassword");
            confirmPassword = savedInstanceState.getString("confirmPassword");
            atualizaDados();
        }
    }


    public void setUi() {
        saveBtn = (Button) findViewById(R.id.saveBtn);
        logoutBtn = (Button) findViewById(R.id.logoutBtn);
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        emailText = (TextView) findViewById(R.id.emailEdtTxt);
        passwordText = (TextView) findViewById(R.id.crtPassEdtTxt);
        newPasswordText = (TextView) findViewById(R.id.newPassEdtTxt);
        confirmPasswordText = (TextView) findViewById(R.id.rptPassEdtText);
        backBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
    }

    public void updateUI() {
        passwordText.setText(Session.getInstance().getUser().getPassword());
        newPasswordText.setText("");
        confirmPasswordText.setText("");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backBtn:
                finish();
                break;
            case R.id.logoutBtn:
                Session.getInstance().setUser(null);
                Intent it = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(it);
                break;
            case R.id.saveBtn:
                changePassword();
                break;
        }
    }

    public void changePassword() {
        if (!newPasswordText.getText().toString().equals(confirmPasswordText.getText().toString())
                || newPasswordText.getText().toString().equals("")) {
            System.out.println("Mostrar error");
        } else {
            Session.getInstance().getUser().setPassword(newPasswordText.getText().toString());
            Session.getInstance().getUser().setUsername(emailText.getText().toString());
            String jsonStringPerson = Controller.gson.toJson(Session.getInstance().getUser());
            if (Controller.checkCredentials(jsonStringPerson)) {
                System.out.println("Senha alterada com sucesso");
                updateUI();
            } else
                System.out.println("Erro ao alterar a senha");
        }

    }


    public void setPerson(Person p) {
        emailText.setText(p.getUsername());
        passwordText.setText(p.getPassword());

    }

    private void atualizaDados() {
        if (email != null)
            emailText.setText(email);
        if (password != null)
            passwordText.setText(password);
        if (newPassword != null)
            newPasswordText.setText(newPassword);
        if (confirmPassword != null)
            confirmPasswordText.setText(confirmPassword);

    }

    private void salvaDados() {
        email = emailText.getText().toString();
        password = passwordText.getText().toString();
    }
}
