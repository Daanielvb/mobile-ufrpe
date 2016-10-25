package com.example.daniel.fitkeeper;

import java.util.jar.Attributes;

/**
 * Created by Daniel on 24/10/2016.
 */
public class Person {
    public String name;
    public int age;
    public float height;
    public float weight;
    public  float imc;

    public Person(String name, int age, float height,float weight, float imc){
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.imc = imc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public float getHeight() {

        return height;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getImc() {
        return age;
    }

    public String getWeight() {
        return name;
    }

}
