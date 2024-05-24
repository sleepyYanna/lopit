package com.example.lopit;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class MainActivity extends AppCompatActivity {

    FloatingActionButton addsub, floatsub;
    Animation fabOpen, fabClose, fabForward, fabBackward;
    TextView txtsub;
    boolean isOpen = false;
    ImageView school_imageView;
    RecyclerView subject_recyclerView;

    DBHelper myDB;
    ArrayList <String> subject_name, subject_teacher, subject_color, subject_note;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addsub = findViewById(R.id.addsub);
        floatsub = findViewById(R.id.floatsub);
        txtsub = findViewById(R.id.txtsub);
        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        fabForward = AnimationUtils.loadAnimation(this, R.anim.fab_forward);
        fabBackward = AnimationUtils.loadAnimation(this, R.anim.fab_backward);
        subject_recyclerView = findViewById(R.id.teacher_recyclerView);
        school_imageView = findViewById(R.id.imageview_subject);

        myDB = new DBHelper(MainActivity.this);
        subject_name = new ArrayList<>();
        subject_teacher = new ArrayList<>();
        subject_color = new ArrayList<>();
        subject_note = new ArrayList<>();

        customAdapter = new CustomAdapter(MainActivity.this, this, subject_name, subject_teacher, subject_color, subject_note);
        subject_recyclerView.setAdapter(customAdapter);
        subject_recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        storeSubjectData();

        addsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
            }
        });

        floatsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
                openNewActivity();
            }
        });



        Spinner spinner = findViewById(R.id.spintermss);

        List<String> categories = new ArrayList<>();
        categories.add("All Terms");
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
    }

    private void animateFab() {
        if (isOpen) {
            addsub.startAnimation(fabBackward);
            floatsub.startAnimation(fabClose);
            txtsub.startAnimation(fabClose);
            floatsub.setClickable(false);
            isOpen = false;
        } else {
            addsub.startAnimation(fabForward);
            floatsub.startAnimation(fabOpen);
            txtsub.startAnimation(fabOpen);
            floatsub.setClickable(true);
            isOpen = true;
        }
    }

    public void openNewActivity() {
        Intent loe = new Intent(this, create.class);
           startActivity(loe);
    }
}