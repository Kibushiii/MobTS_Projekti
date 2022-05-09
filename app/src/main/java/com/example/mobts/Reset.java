package com.example.mobts;

/**
 * Reset values for trackers with this
 */

// Create constructor with parameters //
public class Reset {
    private int value;
    private int present;

    // Resets the given value //
    public int resetValue() {
        this.value = 0;
        return this.value;

    }

    // Resets the given percentage value //
    public int resetValuePresent() {
        this.present = 0;
        return  this.present;
    }
}
