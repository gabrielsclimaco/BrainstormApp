package com.reforcointeligente.brainstormapp.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.FirebaseDatabase;
import com.reforcointeligente.brainstormapp.Controller.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.reforcointeligente.brainstormapp.Model.Student;
import com.reforcointeligente.brainstormapp.R;

import java.util.ArrayList;
import java.util.List;

public class LessonFormActivity extends AppCompatActivity {

    Spinner spinner;
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_form);

        setUpStudentSpinner();
        setUpSubjectSpinner();

        Button cancelLessonButton = (Button) findViewById(R.id.buttonCancelLesson);
        cancelLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button confirmLessonButton = (Button) findViewById(R.id.buttonConfirmLesson);
        confirmLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLessonCreated();
            }
        });
    }

    private void setUpSubjectSpinner() {
        ArrayAdapter<CharSequence> subjectAdapter = ArrayAdapter.createFromResource(this,
            R.array.list_of_subjects, android.R.layout.simple_spinner_dropdown_item);
        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.spinnerLessonSubject);
        Spinner subjectSpinner = (Spinner) findViewById(R.id.spinnerLessonSubject);
        subjectSpinner.setAdapter(subjectAdapter);
    }

    private void setUpStudentSpinner() {
        ArrayList<String> studentsList = FirebaseUtils.getStudentsList();

        ArrayAdapter<String> studentAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, studentsList);
        studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner studentSpinner = (Spinner) findViewById(R.id.spinnerLessonStudent);
        studentSpinner.setAdapter(studentAdapter);
    }

    public void onLessonCreated() {
        View view = findViewById(R.id.activity_lesson_form);
        FirebaseUtils.saveLesson(view);

        finish();
    }
}
