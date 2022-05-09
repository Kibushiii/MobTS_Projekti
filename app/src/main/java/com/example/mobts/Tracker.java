package com.example.mobts;

import android.content.SharedPreferences;

import java.io.Serializable;

public class Tracker implements Serializable {

    private String tracker;
    private float weight;
    private float height;
    private int age;

    public Tracker(String tracker, int age, float height, float weight){
        this.tracker = tracker;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public String getValue(){
        return this.tracker;
    }

    public int getAge(){
        return this.age;
    }

    public float getHeight(){
        return this.height;
    }

    public float getWeight(){
        return this.weight;
    }

    public String toString(){
        return this.tracker;
    }
}
