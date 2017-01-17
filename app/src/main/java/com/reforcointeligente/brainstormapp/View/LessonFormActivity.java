package com.reforcointeligente.brainstormapp.View;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.FirebaseDatabase;
import com.reforcointeligente.brainstormapp.Controller.FirebaseUtils;
import com.reforcointeligente.brainstormapp.R;

import java.util.ArrayList;
import java.util.List;

public class LessonFormActivity extends AppCompatActivity {
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
//        studentSpinner.setAdapter(new StudentSpinnerAdapter(this, new ArrayList<String>()));
    }

    public void onLessonCreated() {
        View view = findViewById(R.id.activity_lesson_form);
        FirebaseUtils.saveLesson(view);

        finish();
    }
}
