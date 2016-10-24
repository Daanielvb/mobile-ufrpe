package com.example.daniel.fitkeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private Button homeBtn;
    private Button workoutBtn;
    private Button subscriptionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeBtn = (Button) findViewById(R.id.btnHome);
        workoutBtn = (Button) findViewById(R.id.btnWorkout);
        subscriptionBtn = (Button) findViewById(R.id.btnSubscription);

    }
}
