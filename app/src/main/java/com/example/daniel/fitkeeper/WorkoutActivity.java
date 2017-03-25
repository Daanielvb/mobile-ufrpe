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
import com.example.daniel.fitkeeper.utils.Session;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

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

import model.Membership;
import model.Workout;

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
        getCurrentWorkout();
    }

    private void getCurrentWorkout() {
        try {
            JSONArray response = Controller.getJSONObjectFromURL(
                    RequestHelper.composeUrlPathWithParam(Constants.WORKOUT_ENTITY, "weekday",
                            valueOf(getCurrentDay())), Constants.GET_REQUEST);
            JSONArray exercises = response.getJSONObject(0).getJSONArray("exercises");
            getCurrentExercises(exercises);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getCurrentExercises(JSONArray exercises){
        List<Integer> workoutIds = new ArrayList<>();
        try {
            for(int i = 0; i < exercises.length(); i++){
                 workoutIds.add((Integer) exercises.get(i));
                }
            JSONObject response = Controller.getJSONRealObjectFromURL(RequestHelper.composeUrlPathWithMultipleParams
                    (Constants.EXERCISE_ENTITY, "id", workoutIds), Constants.GET_REQUEST);
        }
        catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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

}
