package com.example.daniel.fitkeeper;

import android.os.Bundle;
import android.os.Parcelable;
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
import com.example.daniel.fitkeeper.utils.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import model.Exercise;

import static java.lang.String.valueOf;

public class WorkoutActivity extends AppCompatActivity implements View.OnClickListener {
    public static int checkCount = 0;
    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
    Calendar calendar = Calendar.getInstance();
    CheckBox c1, c2, c3, c4;
    private String[] days;
    private ImageButton backBtn;
    private Button finishWorkoutBtn;
    private TextView currentDayText;
    private TextView workoutInfoMessage;

    private TextView exercise1;
    private TextView exercise2;
    private TextView exercise3;
    private TextView exercise4;

    private TextView set1;
    private TextView set2;
    private TextView set3;
    private TextView set4;

    private TextView reps1;
    private TextView reps2;
    private TextView reps3;
    private TextView reps4;

    private TextView weigth1;
    private TextView weigth2;
    private TextView weigth3;
    private TextView weigth4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkCount = 0;
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

        if (savedInstanceState != null) {

            if (savedInstanceState.getBoolean("finishWorkoutBtn"))
            {
                finishWorkoutBtn.setVisibility(View.VISIBLE);
                setRowColor(R.id.tablerow1, true);
                setRowColor(R.id.tablerow2, true);
                setRowColor(R.id.tablerow3, true);
                setRowColor(R.id.tablerow4, true);
            }
            else {
                if (savedInstanceState.getBoolean("c1"))
                    setRowColor(R.id.tablerow1, true);

                if (savedInstanceState.getBoolean("c2"))
                    setRowColor(R.id.tablerow2, true);

                if (savedInstanceState.getBoolean("c3"))
                    setRowColor(R.id.tablerow3, true);

                if (savedInstanceState.getBoolean("c4"))
                    setRowColor(R.id.tablerow4, true);
            }
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                currentDayText.setText(getString(R.string.current_day) + " " + days[position]);
                getCurrentWorkout(days[position]);
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
        if (checkCount > 3) {
            if (finishWorkoutBtn.getVisibility() != View.VISIBLE)
                finishWorkoutBtn.setVisibility(View.VISIBLE);
        } else {
            if (finishWorkoutBtn.getVisibility() == View.VISIBLE)
                finishWorkoutBtn.setVisibility(View.GONE);
        }
    }

    public void setUI() {
        currentDayText = (TextView) findViewById(R.id.currentDayTxt);
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        finishWorkoutBtn = (Button) findViewById(R.id.finish_workout_btn);
        workoutInfoMessage = (TextView) findViewById(R.id.workout_info_message);
        workoutInfoMessage.setVisibility(View.INVISIBLE);
        backBtn.setOnClickListener(this);
        finishWorkoutBtn.setOnClickListener(this);
        c1 = (CheckBox) findViewById(R.id.checkbox_1);
        c2 = (CheckBox) findViewById(R.id.checkbox_2);
        c3 = (CheckBox) findViewById(R.id.checkbox_3);
        c4 = (CheckBox) findViewById(R.id.checkbox_4);

        exercise1 = (TextView) findViewById(R.id.exercise1);
        exercise2 = (TextView) findViewById(R.id.exercise2);
        exercise3 = (TextView) findViewById(R.id.exercise3);
        exercise4 = (TextView) findViewById(R.id.exercise4);

        set1 = (TextView) findViewById(R.id.set1);
        set2 = (TextView) findViewById(R.id.set2);
        set3 = (TextView) findViewById(R.id.set3);
        set4 = (TextView) findViewById(R.id.set4);

        reps1 = (TextView) findViewById(R.id.reps1);
        reps2 = (TextView) findViewById(R.id.reps2);
        reps3 = (TextView) findViewById(R.id.reps3);
        reps4 = (TextView) findViewById(R.id.reps4);

        weigth1 = (TextView) findViewById(R.id.weight1);
        weigth2 = (TextView) findViewById(R.id.weight2);
        weigth3 = (TextView) findViewById(R.id.weight3);
        weigth4 = (TextView) findViewById(R.id.weight4);

        getCurrentWorkout(null);
    }

    private void getCurrentWorkout(String day) {
        try {
            JSONArray response = new JSONArray();
            if(day == null) {
                response = Controller.getJSONObjectFromURL(
                        RequestHelper.composeUrlPathWithParam(Constants.WORKOUT_ENTITY, "weekday",
                                valueOf(getCurrentDay())), Constants.GET_REQUEST);
            }
            else{
                response = Controller.getJSONObjectFromURL(
                        RequestHelper.composeUrlPathWithParam(Constants.WORKOUT_ENTITY, "weekday",
                                day), Constants.GET_REQUEST);
            }
            JSONArray exercises = response.getJSONObject(0).getJSONArray("exercises");
            getCurrentExercises(exercises);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getCurrentExercises(JSONArray exercises) {
        List<Integer> workoutIds = new ArrayList<>();
        List<Exercise> exerciseList = new ArrayList<>();
        try {
            for (int i = 0; i < exercises.length(); i++) {
                workoutIds.add((Integer) exercises.get(i));
            }
            JSONArray response = Controller.getJSONObjectFromURL(RequestHelper.composeUrlPathWithMultipleParams
                    (Constants.EXERCISE_ENTITY, "id", workoutIds), Constants.GET_REQUEST);
            System.out.println(response);

            for (int i = 0; i < response.length(); i++) {
                JSONObject element = (JSONObject) response.get(i);
                System.out.println(element);
                Exercise exercise = new Exercise(element.get("name").toString(),
                        Integer.valueOf((Integer) element.get("series")), ((Integer) element.get("reps")),
                        (Integer) element.get("weight"));
                exerciseList.add(exercise);
            }
            setExercises(exerciseList);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void setExercises(List<Exercise> exerciseList) {
        for (int j = 0; j < exerciseList.size(); j++) {
            if (j == 0) {
                exercise1.setText(exerciseList.get(j).getName());
                set1.setText(String.valueOf(exerciseList.get(j).getSeries()));
                reps1.setText(String.valueOf(exerciseList.get(j).getReps()));
                weigth1.setText(String.valueOf(exerciseList.get(j).getWeigth()));
            }
            if (j == 1) {
                exercise2.setText(exerciseList.get(j).getName());
                set2.setText(String.valueOf(exerciseList.get(j).getSeries()));
                reps2.setText(String.valueOf(exerciseList.get(j).getReps()));
                weigth2.setText(String.valueOf(exerciseList.get(j).getWeigth()));
            }
            if (j == 2) {
                exercise3.setText(exerciseList.get(j).getName());
                set3.setText(String.valueOf(exerciseList.get(j).getSeries()));
                reps3.setText(String.valueOf(exerciseList.get(j).getReps()));
                weigth3.setText(String.valueOf(exerciseList.get(j).getWeigth()));
            }
            if (j == 3) {
                exercise4.setText(exerciseList.get(j).getName());
                set4.setText(String.valueOf(exerciseList.get(j).getSeries()));
                reps4.setText(String.valueOf(exerciseList.get(j).getReps()));
                weigth4.setText(String.valueOf(exerciseList.get(j).getWeigth()));
            }
        }
    }


    public void saveWorkoutFinished() {
        try {
            Session.getInstance().getUser().increaseWorkoutCounter();
            String jsonStringPerson = Controller.gson.toJson(Session.getInstance().getUser());
            if (Controller.putRequestWithJson(
                    RequestHelper.composeUrlPath(Constants.PERSON_ENTITY, String.valueOf(Session.getInstance().getUser().getId())),
                    jsonStringPerson)) {
                setErrorMessage(true);
            } else
                setErrorMessage(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setErrorMessage(boolean success) {
        workoutInfoMessage.setVisibility(View.VISIBLE);
        if (success)
            workoutInfoMessage.setText(getResources().getString(R.string.finish_workout_success));
        else
            workoutInfoMessage.setText(getResources().getString(R.string.finish_workout_error));
    }

    public String getCurrentDay() {
        return dayFormat.format(calendar.getTime());
    }

    public int getIndexByName(String day) {
        String preparedDay = day.replaceAll("f", "F");
        preparedDay = preparedDay.substring(0, 1).toUpperCase() + preparedDay.substring(1);
        return Arrays.asList(days).indexOf(preparedDay);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (c1.isChecked())
            outState.putBoolean("c1",true);
        if (c2.isChecked())
            outState.putBoolean("c2",true);
        if (c3.isChecked())
            outState.putBoolean("c3",true);
        if (c4.isChecked())
            outState.putBoolean("c4",true);
        if (finishWorkoutBtn.getVisibility() == View.VISIBLE)
            outState.putBoolean("finishWorkoutBtn",true);
    }

}
