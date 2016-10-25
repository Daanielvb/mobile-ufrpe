package com.example.daniel.fitkeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InfoActivity extends AppCompatActivity {
    public String name;
    public String age;
    public String height;
    public String weight;
    public  String imc;

    private String[] months;
    private TextView nameText;
    private TextView ageText;
    private TextView heightText;
    private TextView weightText;
    private TextView imcText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        if (savedInstanceState != null) {
            name = savedInstanceState.getString("name");
            age = savedInstanceState.getString("age");
            height = savedInstanceState.getString("height");
            weight = savedInstanceState.getString("weight");
            imc = savedInstanceState.getString("imc");
            atualizaDados();
        }

        List<String> spinnerArray =  new ArrayList<String>();

        months = getResources().getStringArray(R.array.months);
        spinnerArray.addAll(Arrays.asList(months));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);

    }

    private void atualizaDados() {
        if (name != null)
            nameText.setText(name);
        if(age != null)
            ageText.setText(age);
        if(height != null)
            heightText.setText(height);
        if (weight != null)
            weightText.setText(weight);
        if(imc != null)
            imcText.setText(imc);
    }

    private void salvaDados(){
        name = nameText.getText().toString();
        age = ageText.getText().toString();
        height = heightText.getText().toString();
        weight = weightText.getText().toString();
        imc = imcText.getText().toString();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", name);
        outState.putString("age", age);
        outState.putString("weight", weight);
        outState.putString("height", height);
        outState.putString("imc", imc);
    }
}
