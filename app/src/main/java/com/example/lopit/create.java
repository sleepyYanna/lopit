package com.example.lopit;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.List;

public class create extends AppCompatActivity {
   EditText add_sub, add_color, add_teacher, add_note;
   Button btn_back;
   Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        add_sub = (EditText) findViewById(R.id.txtaddsub);
        add_color = (EditText) findViewById(R.id.txtaddcolor);
        add_teacher = (EditText) findViewById(R.id.txtaddteacher);
        add_note = (EditText) findViewById(R.id.editTextTextMultiLine_note);
        btn_back = (Button) findViewById(R.id.btnback);
        btn_save = (Button) findViewById(R.id.btnsave);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB = new DBHelper(create.this);
                myDB.addSubject(add_sub.getText().toString().trim(),
                        add_teacher.getText().toString().trim(),
                        add_color.getText().toString().trim(),
                        add_note.getText().toString().trim());

                Intent i = new Intent(create.this, MainActivity.class);
                startActivity(i);
            }
        });

        Spinner spinner = findViewById(R.id.spinterms02);

        List<String> categories = new ArrayList<>();
        categories.add("Choose your term");
        categories.add("1st Term");
        categories.add("2nd Term");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Selected: " + categories.get(position),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loe = new Intent(create.this, MainActivity.class);
                startActivity(loe);
            }
        });

    }
}

