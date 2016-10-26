package com.example.daniel.fitkeeper;

import java.util.jar.Attributes;

/**
 * Created by Daniel on 24/10/2016.
 */
public class Person {
    public String name;
    public int age;
    public Double height;
    public float weight;
    public double imc;

    public Person(String name, int age, double height,float weight){
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        setImc();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setImc() {
        this.imc = this.weight/Math.pow(this.height,2) ;
    }

    public double getHeight() {

        return height;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public double getImc() {
        return imc;
    }

    public float getWeight() {
        return weight;
    }

}
