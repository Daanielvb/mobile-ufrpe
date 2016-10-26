package com.example.daniel.fitkeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkoutActivity extends AppCompatActivity implements View.OnClickListener{
    private String[] days;
    private ImageButton backBtn;
    private TextView currentDayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        setUI();

        List<String> spinnerArray =  new ArrayList<String>();

        days = getResources().getStringArray(R.array.week);
        spinnerArray.addAll(Arrays.asList(days));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerWeek);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                currentDayText.setText(getString(R.string.current_day) + " " + days[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.backBtn:
                finish();
                break;
        }
    }

    public void setUI() {
        currentDayText = (TextView) findViewById(R.id.currentDayTxt);
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);
    }
}
