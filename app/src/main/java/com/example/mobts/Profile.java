package com.example.mobts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;



public class Profile extends AppCompatActivity {

    private TextView textView;
    private int age;
    private float height;
    private float weight;

    public static final String PROFILE = "profile";
    public static final String AGE = "age";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";

    private EditText ageView;
    private EditText heightView;
    private EditText weightView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Selects views //
        ageView = findViewById(R.id.age);
        heightView = findViewById(R.id.height);
        weightView = findViewById(R.id.weight);

        load();
    }


    // Saves changes //
    public void saveChanges(View view){
        age = Integer.parseInt(ageView.getText().toString());
        height = Float.parseFloat(heightView.getText().toString());
        weight = Float.parseFloat(weightView.getText().toString());

        SharedPreferences sharedPreferences = getSharedPreferences(PROFILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(AGE, age);
        editor.putFloat(HEIGHT, height);
        editor.putFloat(WEIGHT, weight);

        editor.apply();

        Toast.makeText(getApplicationContext(), "Saved changes!", Toast.LENGTH_SHORT).show();
    }

    // Loads information from shared prefs //
    public void load(){
        SharedPreferences sharedPreferences = getSharedPreferences(PROFILE, MODE_PRIVATE);
        age = sharedPreferences.getInt(AGE, 18);
        height = sharedPreferences.getFloat(HEIGHT, 180);
        weight = sharedPreferences.getFloat(WEIGHT, 90);

        ageView.setText(Integer.toString(age));
        heightView.setText(Float.toString(height));
        weightView.setText(Float.toString(weight));
    }
}