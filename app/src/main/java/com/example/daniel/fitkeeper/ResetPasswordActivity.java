package com.example.daniel.fitkeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton backBtn;
    private TextView emailTxt;
    private TextView resultTxt;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.editTextPassword:
                email = emailTxt.getText().toString();
                resetPassword(email);
                break;
            case R.id.backBtn:
                finish();
                break;
        }
    }

    public void resetPassword(String email){
        if(email.equals("") || email == null)
            resultTxt.setText(getResources().getString(R.string.reset_mail_error));
        else
            resultTxt.setText(getResources().getString(R.string.reset_mail_success));
    }

    public void setUI(){
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        //ajustar id
        emailTxt = (TextView) findViewById(R.id.emailEdtTxt);
        //resultTxt = (TextView) findViewById(R.id.resultTxt);
    }


}
