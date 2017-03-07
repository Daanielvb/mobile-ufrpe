package com.example.daniel.fitkeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {
    public String name;
    public String age;
    public String height;
    public String weight;
    public String imc;
    public String currentMonth;

    private String[] months;
    private TextView nameText;
    private TextView ageText;
    private TextView heightText;
    private TextView weightText;
    private TextView imcText;
    private ImageButton backBtn;
    private TextView currentMonthText;

    Person person = new Person("Daniel", 25, 1.71, 76);

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
    }

    private void salvaDados() {
        currentMonth = currentMonthText.getText().toString();
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

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backBtn:
                finish();
                break;
        }
    }


    public void setPerson(Person p) {
        nameText.setText(p.getName());
        heightText.setText(String.valueOf(p.getHeight()));
        weightText.setText(String.valueOf(p.getWeight()));
        imcText.setText(new DecimalFormat("##.##").format(p.getImc()));
        ageText.setText(String.valueOf(p.getAge()));
    }

    public void setUI() {
        //currentMonthText = (TextView) findViewById(R.id.currentMonthTxt);
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        nameText = (TextView) findViewById(R.id.nameEdtTxt);
        ageText = (TextView) findViewById(R.id.ageEdtTxt);
        heightText = (TextView) findViewById(R.id.heightEdtTxt);
        weightText = (TextView) findViewById(R.id.weightEdtTxt);
        imcText = (TextView) findViewById(R.id.imcEdtTxt);
    }

}
