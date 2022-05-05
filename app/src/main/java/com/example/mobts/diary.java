package com.example.mobts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class diary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        EditText diaryInput = (EditText) findViewById(R.id.diaryinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        Button diarySaveBtn =  (Button) findViewById(R.id.diaryBtn);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        diarySaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = diaryInput.getText().toString();
                String description = descriptionInput.getText().toString();
                long timeCreated = System.currentTimeMillis();

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setTitle(title);
                note.setDescription(description);
                note.setTimeCreated(timeCreated);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(), "Diary saved", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }
}