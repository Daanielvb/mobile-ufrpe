package com.example.daniel.fitkeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkoutActivity extends AppCompatActivity {
    private String[] weeks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);


        List<String> spinnerArray =  new ArrayList<String>();

        weeks = getResources().getStringArray(R.array.week);
        spinnerArray.addAll(Arrays.asList(weeks));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.snipperWeek);
        sItems.setAdapter(adapter);
    }
}
