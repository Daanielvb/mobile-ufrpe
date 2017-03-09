package model;

/**
 * Created by Daniel on 24/10/2016.
 */
public class Person {

    public Membership membership;
    private long id;
    private String name;
    private int age;
    private Double height;
    private float weight;
    private double imc;
    private String email;
    private String password;
    private int workoutCounter;

    public Person(String name, int age, double height, float weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        setImc();
    }

    public Person(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void setImc() {
        this.imc = this.weight / Math.pow(this.height, 2);
    }

    public double getHeight() {

        return height;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWorkoutCounter() {
        return workoutCounter;
    }

    public void setWorkoutCounter(int workoutCounter) {
        this.workoutCounter = workoutCounter;
    }

    public void setHeight(Double height) {

        this.height = height;
    }

    public void setHeight(double height) {
        this.height = height;
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

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership m) {
        this.membership = m;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
