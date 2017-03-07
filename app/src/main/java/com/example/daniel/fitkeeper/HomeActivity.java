package com.example.daniel.fitkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button homeBtn;
    private Button workoutBtn;
    private Button subscriptionBtn;
    private ImageButton settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUI();

    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnHome:
                Intent it = new Intent(HomeActivity.this, InfoActivity.class);
                startActivity(it);
                break;
            case R.id.btnWorkout:
                Intent it2 = new Intent(HomeActivity.this, WorkoutActivity.class);
                startActivity(it2);
                break;
            case R.id.btnSubscription:
                Intent it3 = new Intent(HomeActivity.this, SubscriptionActivity.class);
                startActivity(it3);
                break;
            case R.id.settingsButton:
                Intent it4 = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(it4);
                break;
        }
    }

    public void setUI() {
        homeBtn = (Button) findViewById(R.id.btnHome);
        workoutBtn = (Button) findViewById(R.id.btnWorkout);
        subscriptionBtn = (Button) findViewById(R.id.btnSubscription);
        settingsBtn = (ImageButton) findViewById(R.id.settingsButton);
        homeBtn.setOnClickListener(this);
        workoutBtn.setOnClickListener(this);
        subscriptionBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
    }

}
