package com.example.mobts;

public class User {

    private String userName;
    private int age;
    private double height;
    private double weight;

    public User(String userName, int age, double height, double weight) {
        this.userName = userName;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public String toString() {
        return "Name: " + this.userName + "\nAge: " + this.age + "\nHeight: " + this.height + "\nWeight: " + this.weight;
    }

}
