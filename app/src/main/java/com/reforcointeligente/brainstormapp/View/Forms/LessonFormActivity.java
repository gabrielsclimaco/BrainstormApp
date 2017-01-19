package com.reforcointeligente.brainstormapp.View.Forms;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.reforcointeligente.brainstormapp.Controller.FirebaseUtils;
import com.reforcointeligente.brainstormapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class LessonFormActivity extends AppCompatActivity {

    final Calendar calendar = Calendar.getInstance();
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_form);

        setUpDatePicker();
        setUpTimePicker();

        setUpTeacherSpinner();
        setUpStudentSpinner();

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

    public void onLessonCreated() {
        View view = findViewById(R.id.activity_lesson_form);
        FirebaseUtils.saveLesson(view);

        finish();
    }

    private void setUpDatePicker() {
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                updateDateLabel();
            }
        };

        (findViewById(R.id.editTextLessonDate)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new DatePickerDialog(LessonFormActivity.this, date, calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });
    }

    private void updateDateLabel() {
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.ITALIAN);

        ((EditText) findViewById(R.id.editTextLessonDate)).setText(simpleDateFormat
                .format((calendar.getTime())));
    }

    private void setUpTimePicker() {
        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

                if (hour >= 10) { // no need to add 0
                    ((EditText) findViewById(R.id.editTextLessonTime)).setText(hour + ":" + minute);
                } else { // adds 0 if needed
                    ((EditText) findViewById(R.id.editTextLessonTime)).setText("0" + hour + ":"
                            + minute);
                }
            }
        };

        (findViewById(R.id.editTextLessonTime)).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new TimePickerDialog(LessonFormActivity.this, time,
                            calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                            true).show();
                }
            }
        );
    }

    private void setUpTeacherSpinner() {
        ArrayList<CharSequence> teachersList = FirebaseUtils.getTeachersList();

        ArrayAdapter<CharSequence> teacherAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, teachersList);
        teacherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner teacherSpinner = (Spinner) findViewById(R.id.spinnerLessonTeacher);
        teacherSpinner.setAdapter(teacherAdapter);
    }

    private void setUpStudentSpinner() {
        ArrayList<CharSequence> studentsList = FirebaseUtils.getStudentsList();

        ArrayAdapter<CharSequence> studentAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, studentsList);
        studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner studentSpinner = (Spinner) findViewById(R.id.spinnerLessonStudent);
        studentSpinner.setAdapter(studentAdapter);
    }
}
