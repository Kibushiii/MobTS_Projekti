package com.example.mobts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;



public class Settings extends AppCompatActivity {

    private TextView textView;
    private Switch water;
    private Switch calories;
    private Switch bloodpressure;
    private Switch steps;

    public static final String SETTINGS = "settings";
    public static final String WATER = "water";
    public static final String CALORIES = "calories";
    public static final String BLOODPRESSURE = "bloodpressure";
    public static final String STEPS = "steps";

    private boolean waterState;
    private boolean caloriesState;
    private boolean bloodpressureState;
    private boolean stepsState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        water = findViewById(R.id.waterSwitch);
        calories = findViewById(R.id.calorieSwitch);
        bloodpressure = findViewById(R.id.bloodpressureSwitch);
        steps = findViewById(R.id.stepSwitch);

        load();

    }



    public void saveChanges(View view){
        SharedPreferences sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(WATER, water.isChecked());
        editor.putBoolean(CALORIES, calories.isChecked());
        editor.putBoolean(BLOODPRESSURE, bloodpressure.isChecked());
        editor.putBoolean(STEPS, steps.isChecked());

        editor.apply();

        Toast.makeText(getApplicationContext(), "Saved changes!", Toast.LENGTH_SHORT).show();
    }

    public void load(){
        SharedPreferences sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        waterState = sharedPreferences.getBoolean(WATER, true);
        caloriesState = sharedPreferences.getBoolean(CALORIES, true);
        bloodpressureState = sharedPreferences.getBoolean(BLOODPRESSURE, true);
        stepsState = sharedPreferences.getBoolean(STEPS, false);

        water.setChecked(waterState);
        calories.setChecked(caloriesState);
        bloodpressure.setChecked(bloodpressureState);
        steps.setChecked(stepsState);
    }
}