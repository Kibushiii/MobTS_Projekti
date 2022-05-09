package com.example.mobts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.channels.InterruptedByTimeoutException;

/**
 * Register page to sign up
 */
public class register extends AppCompatActivity {

    EditText userName, passWord, rePassWord;
    Button signUp;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        userName = (EditText) findViewById(R.id.username);
        passWord = (EditText) findViewById(R.id.password);
        rePassWord = (EditText) findViewById(R.id.repassword);
        signUp = (Button) findViewById(R.id.signup);
        DB = new DBHelper(this);


        /**
         * onClick check if all the fields are not empty
         * check if pass matches repass
         * check if name exists in database
         *
         */
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userName.getText().toString();
                String pass = passWord.getText().toString();
                String repass = rePassWord.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("USERNAME", user);
                editor.apply();


                if (user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkUsername(user);
                        if (checkuser == false){
                            Boolean insert = DB.insertData(user, pass);
                            if ( insert == true){
                                Toast.makeText(register.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Frontpage.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(register.this, "User already exists, please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(register.this, "Passwords do noy match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}