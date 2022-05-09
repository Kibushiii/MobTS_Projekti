package com.example.mobts;

import android.app.Activity;
import android.content.SharedPreferences;

public class Reset {
    private int value;
    private int present;

    public int resetValue() {
        this.value = 0;
        return this.value;

    }

    public int resetValuePresent() {
        this.present = 0;
        return  this.present;
    }
}
