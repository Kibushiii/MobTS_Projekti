package com.example.mobts;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;

public class TrackerPage extends AppCompatActivity {

    private float BMI;
    private float waterIntake;
    private float calorieIntake;

    private int waterValue;
    private int calorieValue;
    private int bloodpressureValue;

    private int waterPresent;
    private int caloriePresent;

    long myDate;

    ConstraintLayout tvpr;

    Button add;
    EditText inputting;
    TextView WIP;
    TextView result;
    ProgressBar bar;
    TextView tvPresent;
    Reset reset = new Reset();
    long currentDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker_page);

        /**
         * Initialize object tracker
         */

        Tracker tracker = (Tracker) getIntent().getSerializableExtra("Single");


        /**
         * Calculate BMI
         */

        BMI = tracker.getWeight() / (int) Math.pow(tracker.getHeight(), 2);

        /**
         * Select constraintlayout
         */
        tvpr = findViewById(R.id.tvpr);


        /**
         * Calculate approximate recommended intake amounts
         */

        waterIntake = Math.round(((tracker.getWeight() / 27.5f) * 100.0) / 100.0 * 1000);
        calorieIntake = Math.round((66.4730f + (13.7516f * tracker.getWeight() + (5.0033f * tracker.getHeight() - 6.7550f * tracker.getAge())) * 100.0) / 100.0);

        /**
         * now We set the TextView and button...
         */

        WIP = findViewById(R.id.WIP);
        add = findViewById(R.id.addValue);
        inputting = findViewById(R.id.inputnumber);
        result = findViewById(R.id.watertracker);
        tvPresent = findViewById(R.id.tvPresent);
        bar = findViewById(R.id.Bar);

        /**
         * Restore the amount of percentage and water used during the day keep it if closed the app
         */

        SharedPreferences prefGet= getSharedPreferences("DailyPref", Activity.MODE_PRIVATE);
        waterValue = prefGet.getInt("WATER", waterValue);
        waterPresent = prefGet.getInt("WATERPERCENTAGE", waterPresent);

        calorieValue = prefGet.getInt("CALORIES", calorieValue);
        caloriePresent = prefGet.getInt("CALORIEPERCENTAGE", caloriePresent);

        bloodpressureValue = prefGet.getInt("BLOODPRESSURE", bloodpressureValue);



        long waterDate = prefGet.getLong("waterTime", myDate);
        long calorieDate = prefGet.getLong("waterTime", myDate);
        long bloodpressureDate = prefGet.getLong("waterTime", myDate);
        currentDate = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);

        /**
         * now we reset function  the value if the new day
         */

        if (currentDate == waterDate) {
            waterValue = reset.resetValue();
            waterPresent = reset.resetValuePresent();

            calorieValue = reset.resetValue();
            caloriePresent = reset.resetValuePresent();

            bloodpressureValue = reset.resetValue();
        }

        /**
         * We set the value of numbers and progress bar  show in the UI
         */
        switch (tracker.getValue()){
            case "Water":
                tvpr.setBackground(ContextCompat.getDrawable(this, R.drawable.water));
                result.setText(waterValue +" ml/ " + waterIntake + " ml");

                /**
                 * in this section Add water Button and when press the add water button with the add water TextView
                 * if is it empty the app going to error there for but if statement if the field empty
                 */
                WIP.setVisibility(View.GONE);
                add.setOnClickListener(view -> {
                    if (!TextUtils.isEmpty(inputting.getText().toString())) {
                        add.setEnabled(!inputting.getText().toString().isEmpty());
                        waterValue = waterValue + Integer.parseInt(inputting.getText().toString());
                        result.setText(waterValue +" ml/ " + waterIntake + " ml");
                        waterPresent = waterValue * 100 / (int) waterIntake;
                        tvPresent.setText(waterPresent + "%");
                        bar.setProgress(waterPresent);

                        /**
                         * Store the amount of percentage, date and water used during the day keep it when  closed the app
                         */
                        SharedPreferences prefPut = getSharedPreferences("DailyPref", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor prefEditor = prefPut.edit();
                        myDate = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
                        prefEditor.putInt("WATER", waterValue);
                        prefEditor.putInt("WATERPERCENTAGE", waterPresent);

                        prefEditor.putLong("waterTime",myDate  );
                        prefEditor.apply();
                    }
                });
                break;
            case "Calories":
                tvpr.setBackground(ContextCompat.getDrawable(this, R.drawable.background));
                result.setText(calorieValue +" kcal/ " + calorieIntake + " kcal");
                /**
                 * in this section Add calorie Button and when press the add calorie button with the add calorie TextView
                 * if is it empty the app going to error there for but if statement if the field empty
                 */
                WIP.setVisibility(View.GONE);
                add.setOnClickListener(view -> {
                    if (!TextUtils.isEmpty(inputting.getText().toString())) {
                        add.setEnabled(!inputting.getText().toString().isEmpty());
                        calorieValue = calorieValue + Integer.parseInt(inputting.getText().toString());
                        result.setText(calorieValue +" kcal/ " + calorieIntake + " kcal");
                        caloriePresent = calorieValue * 100 / (int) calorieIntake;
                        tvPresent.setText(caloriePresent + "%");
                        bar.setProgress(caloriePresent);

                        //Store the amount of percentage, date and water used during the day keep it when  closed the app //
                        SharedPreferences prefPut = getSharedPreferences("DailyPref", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor prefEditor = prefPut.edit();
                        myDate = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
                        prefEditor.putInt("CALORIES", calorieValue);
                        prefEditor.putInt("CALORIEPERCENTAGE", caloriePresent);

                        prefEditor.putLong("calorieTime",myDate  );
                        prefEditor.apply();
                    }
                });
                break;
            case "Blood pressure":
                tvpr.setBackground(ContextCompat.getDrawable(this, R.drawable.bloodpressure));
                result.setText(bloodpressureValue +" mmHg");
                /**
                 * in this section Add blood pressure and when press the add blood pressure button with the add blood pressure TextView
                 * if is it empty the app going to error there for but if statement if the field empty
                 */
                WIP.setVisibility(View.GONE);
                tvPresent.setVisibility(View.GONE);
                bar.setVisibility(View.GONE);
                add.setOnClickListener(view -> {
                    if (!TextUtils.isEmpty(inputting.getText().toString())) {
                        add.setEnabled(!inputting.getText().toString().isEmpty());
                        bloodpressureValue = bloodpressureValue + Integer.parseInt(inputting.getText().toString());
                        result.setText(bloodpressureValue + " mmHg");

                        /**
                         * Store the amount of percentage, date and water used during the day keep it when  closed the app
                         */
                        SharedPreferences prefPut = getSharedPreferences("DailyPref", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor prefEditor = prefPut.edit();
                        myDate = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
                        prefEditor.putInt("BLOODPRESSURE", bloodpressureValue);
                        prefEditor.putLong("BloodpressureTime",myDate  );
                        prefEditor.apply();
                    }
                });
                break;
            case "Steps":
                tvpr.setBackground(ContextCompat.getDrawable(this, R.drawable.walking));
                WIP.setVisibility(View.VISIBLE);
                inputting.setVisibility(View.GONE);
                add.setVisibility(View.GONE);
                result.setVisibility(View.GONE);
                tvPresent.setVisibility(View.GONE);
                bar.setVisibility(View.GONE);
                break;
        }
    }
    @Override
    protected void onPause(){
        /**
         * Store the amount of percentage, date and water used during the day keep it when  closed the app
         */
        super.onPause();
        SharedPreferences prefPut = getSharedPreferences("DailyPref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        myDate = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);

        prefEditor.putInt("WATER", waterValue);
        prefEditor.putInt("WATERPERCENTAGE", waterPresent);

        prefEditor.putInt("CALORIES", calorieValue);
        prefEditor.putInt("CALORIEPERCENTAGE", caloriePresent);

        prefEditor.putInt("BLOODPRESSURE", bloodpressureValue);
        prefEditor.putLong("BloodpressureTime",myDate  );

        prefEditor.putLong("time",myDate  );
        prefEditor.apply();
    }
}