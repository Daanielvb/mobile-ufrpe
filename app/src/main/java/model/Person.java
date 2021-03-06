package model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 24/10/2016.
 */
public class Person {

    public Membership memberships;
    @Expose
    public int membership;
    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private int age;
    @Expose
    private int height;
    @Expose
    private int weight;
    @Expose
    private int weightMonth1;
    @Expose
    private int weightMonth2;
    @Expose
    private int weightMonth3;
    @Expose
    private int weightMonth4;
    @Expose
    private String username;
    @Expose
    private String password;
    @Expose
    private int workoutCounter;
    @Expose
    private int[] workouts;


    //private transient List<String> workouts = new ArrayList<String>();
    private transient double imc;

    public Person() {
    }

    public Person(String name, int age, int height, int weight, int weightMonth1, int weightMonth2, int weightMonth3, int weightMonth4) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.weightMonth1 = weightMonth1;
        this.weightMonth2 = weightMonth2;
        this.weightMonth3 = weightMonth3;
        this.weightMonth4 = weightMonth4;
        setImc();
    }

    public Person(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Person(String name, String username, int membership, int workoutCounter) {
        this.name = name;
        this.username = username;
        this.memberships = new Membership(membership);
        this.workoutCounter = workoutCounter;
    }

    public Person(int id, String name, String username, String password, int membership, int workoutCounter, int age, int height, int weight, int weightMonth1, int weightMonth2, int weightMonth3, int weightMonth4) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.membership = membership;
        this.memberships = new Membership(membership);
        this.workoutCounter = workoutCounter;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.weightMonth1 = weightMonth1;
        this.weightMonth2 = weightMonth2;
        this.weightMonth3 = weightMonth3;
        this.weightMonth4 = weightMonth4;
        this.setImc();

    }

    public void setImc() {
        this.imc = ((this.weight*100) / Math.pow(this.height, 2))*100;
    }

    public double getHeight() {

        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWorkoutCounter() {
        return workoutCounter;
    }

    public void setWorkoutCounter(int workoutCounter) {
        this.workoutCounter = workoutCounter;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public int getWeight() {
        return weight;
    }

    public int getWeightMonth1() {
        return weightMonth1;
    }

    public int getWeightMonth2() {
        return weightMonth2;
    }

    public int getWeightMonth3() {
        return weightMonth3;
    }

    public int getWeightMonth4() {
        return weightMonth4;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setWeightMonth1(int weightMonth1) {
        this.weightMonth1 = weightMonth1;
    }

    public void setWeightMonth2(int weightMonth2) {
        this.weightMonth2 = weightMonth2;
    }

    public void setWeightMonth3(int weightMonth3) {
        this.weightMonth3 = weightMonth3;
    }

    public void setWeightMonth4(int weightMonth4) {
        this.weightMonth4 = weightMonth4;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Membership getMembership() {
        return memberships;
    }

    public void setMembership(int membership) {
        this.membership = membership;
    }

    public void setMembership(Membership m) {
        this.memberships = m;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void increaseWorkoutCounter(){
        this.workoutCounter++;
    }

//    public List<String> getWorkouts() {
//        return workouts;
//    }
//
//    public void setWorkouts(List<String> workouts) {
//        this.workouts = workouts;
//    }

    public int[] getWorkouts() {
        return workouts;
    }

    public void setWorkouts(int[] workoutss) {
        this.workouts = workoutss;
    }
}
