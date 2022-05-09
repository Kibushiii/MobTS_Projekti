package com.example.mobts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Login page to sign in
 */
public class login extends AppCompatActivity {

    EditText userName1, passWord1;
    Button btnSignin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName1 = (EditText) findViewById(R.id.username1);
        passWord1 = (EditText) findViewById(R.id.password1);
        btnSignin = (Button) findViewById(R.id.signin);
        DB = new DBHelper(this);

        /**
         * onClick to check if user or password is empty and to if it is empty
         * check if username and password exists in database
         * if user exists toast a message and send into new activity
         */
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = userName1.getText().toString();
                String pass = passWord1.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("USERNAME", user);
                editor.apply();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkUsernamePassword(user, pass);
                    if (checkuserpass == true){
                        Toast.makeText(login.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), Frontpage.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(login.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}