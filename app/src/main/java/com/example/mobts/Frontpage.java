package com.example.mobts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class Frontpage extends AppCompatActivity {

    ListView listView;

    private int age;
    private float height;
    private float weight;

    public static final String SETTINGS = "settings";
    public static final String PROFILE = "profile";
    public static final String WATER = "water";
    public static final String CALORIES = "calories";
    public static final String BLOODPRESSURE = "bloodpressure";
    public static final String STEPS = "steps";
    public static final String AGE = "age";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";

    private boolean waterState;
    private boolean caloriesState;
    private boolean bloodpressureState;
    private boolean stepsState;
    private String username;

    private ArrayList arrayList;

    private TextView welcome;
    Button diaryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpage);

        listView = (ListView)findViewById(R.id.list);

        arrayList = new ArrayList<Tracker>();

        load();
        createArray();

        welcome = (TextView) findViewById(R.id.welcomeMessage);
        welcome.setText("Hello, " + username);

        diaryBtn = (Button) findViewById(R.id.diaryBtn);
        diaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), diary.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Intent trackerActivity = new Intent(Frontpage.this, TrackerPage.class);
                Object tracker = arrayList.get(i);
                trackerActivity.putExtra("Single", (Serializable) tracker);
                startActivity(trackerActivity);
            }
        });
    }

    public void load(){
        SharedPreferences sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        waterState = sharedPreferences.getBoolean(WATER, true);
        caloriesState = sharedPreferences.getBoolean(CALORIES, true);
        bloodpressureState = sharedPreferences.getBoolean(BLOODPRESSURE, true);
        stepsState = sharedPreferences.getBoolean(STEPS, false);
        username = sharedPreferences.getString("USERNAME", "username");
    }

    public void createArray(){

        SharedPreferences sharedPreferences = getSharedPreferences(PROFILE, MODE_PRIVATE);

        if(waterState){
            arrayList.add(new Tracker("Water", sharedPreferences.getInt(AGE, 18), sharedPreferences.getFloat(HEIGHT, 180), sharedPreferences.getFloat(WEIGHT, 90)));
        }
        if(caloriesState){
            arrayList.add(new Tracker("Calories", sharedPreferences.getInt(AGE, 18), sharedPreferences.getFloat(HEIGHT, 180), sharedPreferences.getFloat(WEIGHT, 90)));
        }
        if(bloodpressureState){
            arrayList.add(new Tracker("Blood pressure", sharedPreferences.getInt(AGE, 18), sharedPreferences.getFloat(HEIGHT, 180), sharedPreferences.getFloat(WEIGHT, 90)));
        }
        if(stepsState){
            arrayList.add(new Tracker ("Steps", sharedPreferences.getInt(AGE, 18), sharedPreferences.getFloat(HEIGHT, 180), sharedPreferences.getFloat(WEIGHT, 90)));
        }


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);
    }

    public void openProfile(View view){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void openSettings(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}