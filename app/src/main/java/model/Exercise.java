package model;

/**
 * Created by DANIEL on 08/03/2017.
 */

public class Exercise {
    private String name;
    private String description;
    private int series;
    private int reps;
    private int id;
    private int weigth;

    public String getName() {
        return name;
    }

    public Exercise(String name, int series, int reps){
        this.name = name;
        this.series = series;
        this.reps = reps;
    }

    public Exercise(String name, int series, int reps, int weigth){
        this.name = name;
        this.series = series;
        this.reps = reps;
        this.weigth = weigth;
    }

    public Exercise(){}

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getWeigth() {
        return weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
