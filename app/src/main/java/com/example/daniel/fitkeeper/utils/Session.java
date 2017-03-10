package com.example.daniel.fitkeeper.utils;

import java.util.List;

import model.Person;

/**
 * Created by DANIEL on 10/03/2017.
 */

public class Session {

    private static Session session;

    private static Person currentUser;

    private static List<Integer> workouts;


    private Session(){
    }
    /**
     * Create a static method to get instance.
     */
    public static Session getInstance(){
        if(session == null){
            session = new Session();
        }
        return session;
    }

    public Person getUser(){
        return this.currentUser;
    }

    public void setUser(Person person){
        this.currentUser = person;
    }

    public static List<Integer> getWorkouts() {
        return workouts;
    }
}


