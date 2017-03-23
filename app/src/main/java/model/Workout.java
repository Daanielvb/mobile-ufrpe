package model;

import java.util.ArrayList;

/**
 * Created by DANIEL on 08/03/2017.
 */

public class Workout {

    private Person owner;
    private ArrayList<Exercise> exercises;
    private String weekDay;

    public Workout(Person owner) {
        this.owner = owner;
        this.exercises = new ArrayList<>();
    }

    public Workout(Person owner, ArrayList<Exercise> exercises) {
        this.owner = owner;
        this.exercises = exercises;
    }

    public Workout(ArrayList<Exercise> exercises, String weekDay){
        this.exercises = exercises;
        this.weekDay = weekDay;
    }


    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }
}
