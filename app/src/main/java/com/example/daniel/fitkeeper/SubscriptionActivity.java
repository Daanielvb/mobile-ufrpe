package com.example.daniel.fitkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SubscriptionActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton backBtn;
    private String type;
    private String expiration;

    private TextView membershipType;
    private TextView membershipExpiration;

    Membership m = new Membership(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        setUi();
        setMembership(m);
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.backBtn:
                finish();
                break;
        }
    }

    public void setUi(){
        membershipExpiration = (TextView) findViewById(R.id.membershipLstDayEdtTxt);
        membershipType = (TextView) findViewById(R.id.membershipEdtTxt);
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);
    }

    public void setMembership(Membership m){
        membershipExpiration.setText(m.getExpirationAt());
        membershipType.setText(getResources().getStringArray(R.array.plans)[m.getType()].toString());
    }
}

