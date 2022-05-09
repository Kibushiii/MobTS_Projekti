package com.example.mobts;

import java.io.Serializable;

/**
 * Get values for using the tracker here
 */

public class Tracker implements Serializable {

    private String tracker;
    private float weight;
    private float height;
    private int age;

    // Create constructor with parameters //
    public Tracker(String tracker, int age, float height, float weight){
        this.tracker = tracker;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    // Get the value of the cell we selected in the listview //
    public String getValue(){
        return this.tracker;
    }

    // Get the age of the user //
    public int getAge(){
        return this.age;
    }

    // Get the height of the user //
    public float getHeight(){
        return this.height;
    }

    // Get the weight of the user //
    public float getWeight(){
        return this.weight;
    }


    // Determine what the listview displays //
    public String toString(){
        return this.tracker;
    }
}
