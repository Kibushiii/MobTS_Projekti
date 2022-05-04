package com.example.register_login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class water_tracker extends AppCompatActivity {

    private int vedenmaar;
    private int present;
    long myDate;

    Button add;
    EditText inputting;
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
        setContentView(R.layout.activity_main);

        // now We set the TextView and button...//

        add = findViewById(R.id.adddrink);
        inputting = findViewById(R.id.inputnumber);
        result= findViewById(R.id.watertracker);
        tvPresent = findViewById(R.id.tvPresent);
        bar = findViewById(R.id.Bar);

//     Restore the amount of percentage and water used during the day keep it if closed the app //

        SharedPreferences prefGet= getSharedPreferences("DailyPref", Activity.MODE_PRIVATE);
        vedenmaar=prefGet.getInt("Key", vedenmaar);
        present = prefGet.getInt("Key1", present);
        long date = prefGet.getLong("time", myDate);
        currentDate = System.currentTimeMillis();

//         now we reset function  the value if the new day //

        if (currentDate== date) {
            vedenmaar=reset.resetValueWater();
            present = reset.resetValuePresent();
        }

//       We set the value of numbers and progress bar  show in the UI //
        result.setText(vedenmaar +" ml/ 3000 ml");
        tvPresent.setText(present+"%");
        bar.setProgress(present);

//           in this section Add water Button and when press the add water button with the add water TextView //
//           if is it empty the app going to error there for but if statement if the field empty //

        add.setOnClickListener(view -> {
            if (!TextUtils.isEmpty(inputting.getText().toString())) {
                add.setEnabled(!inputting.getText().toString().isEmpty());
                vedenmaar = vedenmaar + Integer.parseInt(inputting.getText().toString());
                result.setText(vedenmaar +" ml"+"/ 3000 ml");
                present =  vedenmaar * 100 / 3000;
                tvPresent.setText(present + "%");
                bar.setProgress(present);

//          Store the amount of percentage, date and water used during the day keep it when  closed the app //

                SharedPreferences prefPut = getSharedPreferences("DailyPref", Activity.MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = prefPut.edit();
                myDate = System.currentTimeMillis();
                prefEditor.putInt("Key", vedenmaar);
                prefEditor.putInt("Key1", present);

                prefEditor.putLong("time",myDate  );
                prefEditor.apply();
            }
        });


    }
}