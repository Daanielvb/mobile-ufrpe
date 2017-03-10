package com.example.daniel.fitkeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.daniel.fitkeeper.utils.Constants;
import com.example.daniel.fitkeeper.utils.Controller;
import com.example.daniel.fitkeeper.utils.RequestHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class WorkoutActivity extends AppCompatActivity implements View.OnClickListener {
    public static int checkCount = 0;
    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
    Calendar calendar = Calendar.getInstance();
    CheckBox c1, c2, c3, c4;
    private String[] days;
    private ImageButton backBtn;
    private Button finishWorkoutBtn;
    private TextView currentDayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        setUI();

        List<String> spinnerArray = new ArrayList<String>();

        days = getResources().getStringArray(R.array.week);
        spinnerArray.addAll(Arrays.asList(days));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerWeek);
        spinner.setAdapter(adapter);
        spinner.setSelection(getIndexByName(getCurrentDay()));
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
        switch (view.getId()) {
            case R.id.backBtn:
                finish();
                break;
            case R.id.finish_workout_btn:
                saveWorkoutFinished();
                break;
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkbox_1:
                if (checked)
                    setRowColor(R.id.tablerow1, true);
                else
                    setRowColor(R.id.tablerow1, false);
                break;
            case R.id.checkbox_2:
                if (checked)
                    setRowColor(R.id.tablerow2, true);
                else
                    setRowColor(R.id.tablerow2, false);
                break;
            case R.id.checkbox_3:
                if (checked)
                    setRowColor(R.id.tablerow3, true);
                else
                    setRowColor(R.id.tablerow3, false);
                break;
            case R.id.checkbox_4:
                if (checked)
                    setRowColor(R.id.tablerow4, true);
                else
                    setRowColor(R.id.tablerow4, false);
                break;
        }
    }

    private void setRowColor(int id, boolean flag) {
        TableRow tr = (TableRow) findViewById(id);
        if (flag) {
            checkCount++;
            tr.setBackgroundColor(getResources().getColor(R.color.lightGray));
        } else {
            tr.setBackgroundColor(getResources().getColor(R.color.white));
            checkCount--;
        }
        releaseEndWorkoutButton();
    }

    private void releaseEndWorkoutButton() {
        if (checkCount > 3)
            finishWorkoutBtn.setVisibility(View.VISIBLE);
        else
            finishWorkoutBtn.setVisibility(View.GONE);
    }

    public void setUI() {
        currentDayText = (TextView) findViewById(R.id.currentDayTxt);
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        finishWorkoutBtn = (Button) findViewById(R.id.finish_workout_btn);
        backBtn.setOnClickListener(this);
        finishWorkoutBtn.setOnClickListener(this);
        c1 = (CheckBox) findViewById(R.id.checkbox_1);
        c2 = (CheckBox) findViewById(R.id.checkbox_2);
        c3 = (CheckBox) findViewById(R.id.checkbox_3);
        c4 = (CheckBox) findViewById(R.id.checkbox_4);
    }


    public void saveWorkoutFinished() {
        try {
            Controller.currentUser.increaseWorkoutCounter();
            String jsonStringPerson = Controller.gson.toJson(Controller.currentUser);
            if (Controller.putRequestWithJson(
                    RequestHelper.composeUrlPath(Constants.PERSON_ENTITY, String.valueOf(Controller.currentUser.getId())),
                    jsonStringPerson)) {
                //TODO:
                //Mostrar mensagem de sucesso
                //Usar strings.xml para mensagens
                //Desmarcar checkbox
                System.out.println("Treino salvo");
            } else {
                //TODO:
                //Mostrar mensagem de erro
                //Usar strings.xml para mensagens
                System.out.println("Falha ao salvar o treino. Tente novamente depois");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getCurrentDay() {
        return dayFormat.format(calendar.getTime());
    }

    public int getIndexByName(String day) {
        String preparedDay = day.replaceAll("f", "F");
        preparedDay = preparedDay.substring(0, 1).toUpperCase() + preparedDay.substring(1);
        return Arrays.asList(days).indexOf(preparedDay);
    }

}
