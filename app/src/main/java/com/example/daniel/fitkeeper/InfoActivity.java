package com.example.daniel.fitkeeper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.daniel.fitkeeper.utils.Controller;
import com.example.daniel.fitkeeper.utils.Session;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DecimalFormat;
import java.util.Random;

import model.Person;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {
    Person person = Session.getInstance().getUser();
    private String name;
    private String age;
    private String height;
    private String weight;
    private String imc;
    private String workoutCounter;
    private String currentMonth;
    //workoutsTxtValue
    private String[] months;
    private TextView nameText;
    private TextView ageText;
    private TextView heightText;
    private TextView weightText;
    private TextView workoutCounterText;
    private TextView imcText;
    private ImageButton backBtn;
    private TextView currentMonthText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setUI();
        setPerson(person);
        backBtn.setOnClickListener(this);
        if (savedInstanceState != null) {
            name = savedInstanceState.getString("name");
            age = savedInstanceState.getString("age");
            height = savedInstanceState.getString("height");
            weight = savedInstanceState.getString("weight");
            imc = savedInstanceState.getString("imc");
            currentMonth = savedInstanceState.getString("currentMonth");
            atualizaDados();
        }
        setWeightGraph(person);
        //setIMCGraph(person);

//        List<String> spinnerArray =  new ArrayList<String>();
//        months = getResources().getStringArray(R.array.months);
//        spinnerArray.addAll(Arrays.asList(months));
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this, android.R.layout.simple_spinner_item, spinnerArray);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//        spinner.setAdapter(adapter);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                currentMonthText.setText(getString(R.string.current_month) + " " + months[position]);
//                person.setWeight(person.getWeight() - 2);
//                person.setImc();
//                setPerson(person);
//               }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//            }
//        });


    }

    private void atualizaDados() {
        if (name != null)
            nameText.setText(name);
        if (age != null)
            ageText.setText(age);
        if (height != null)
            heightText.setText(height);
        if (weight != null)
            weightText.setText(weight);
        if (imc != null)
            imcText.setText(imc);
        if (currentMonth != null)
            currentMonthText.setText(currentMonth);
        if (workoutCounter != null)
            workoutCounterText.setText(workoutCounter);
    }

    private void salvaDados() {
        currentMonth = currentMonthText.getText().toString();
        name = nameText.getText().toString();
        age = ageText.getText().toString();
        height = heightText.getText().toString();
        weight = weightText.getText().toString();
        imc = imcText.getText().toString();
        workoutCounter = workoutCounterText.getText().toString();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", name);
        outState.putString("age", age);
        outState.putString("weight", weight);
        outState.putString("height", height);
        outState.putString("imc", imc);
        outState.putString("workoutCounter", workoutCounter);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backBtn:
                finish();
                break;
        }
    }


    public void setPerson(Person p) {
        nameText.setText(p.getName());
        heightText.setText(String.valueOf(p.getHeight()) + "cm");
        weightText.setText(String.valueOf(p.getWeight()));
        imcText.setText(new DecimalFormat("##.##").format(p.getImc()));
        ageText.setText(String.valueOf(p.getAge()));
        workoutCounterText.setText(String.valueOf(p.getWorkoutCounter()));
    }

    public void setWeightGraph(Person p){
        Random rand = new Random();
        int currentWeight = p.getWeight();
        GraphView weightGraph = (GraphView) findViewById(R.id.weightGraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, currentWeight + rand.nextInt(3)),
                new DataPoint(2, currentWeight + rand.nextInt(3)),
                new DataPoint(3, currentWeight + rand.nextInt(3)),
                new DataPoint(4, currentWeight + rand.nextInt(3)),
                new DataPoint(5, currentWeight)
        });

        weightGraph.addSeries(series);
    }

    /*public void setIMCGraph(Person p){
        Random rand = new Random();
        double currentICM = p.getImc();
        GraphView imcGraph = (GraphView) findViewById(R.id.imcGraph);
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, currentICM + rand.nextInt()),
                new DataPoint(2, currentICM + rand.nextInt()),
                new DataPoint(3, currentICM + rand.nextInt()),
                new DataPoint(4, currentICM + rand.nextInt()),
                new DataPoint(5, currentICM + rand.nextInt())
        });
        imcGraph.addSeries(series2);
    }*/

    public void setUI() {
        //currentMonthText = (TextView) findViewById(R.id.currentMonthTxt);
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        nameText = (TextView) findViewById(R.id.nameEdtTxt);
        ageText = (TextView) findViewById(R.id.ageEdtTxt);
        heightText = (TextView) findViewById(R.id.heightEdtTxt);
        weightText = (TextView) findViewById(R.id.weightEdtTxt);
        imcText = (TextView) findViewById(R.id.imcEdtTxt);
        workoutCounterText = (TextView) findViewById(R.id.workoutsTxtValue);
    }

}
