package com.reforcointeligente.brainstormapp.View.Forms;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.reforcointeligente.brainstormapp.Controller.FirebaseUtils;
import com.reforcointeligente.brainstormapp.Controller.MoneyTextWatcher;
import com.reforcointeligente.brainstormapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class LessonFormActivity extends AppCompatActivity {

    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpDatePicker();
        setUpTimePicker();

        setUpTeacherSpinner();
        setUpStudentSpinner();

        maskMoneyFields();

        listenButtonClickEvents();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void maskMoneyFields() {
        EditText lessonDisplacement = (EditText) findViewById(R.id.editTextLessonDisplacement);
        MoneyTextWatcher moneyTextWatcherDisplacement = new MoneyTextWatcher(lessonDisplacement);
        lessonDisplacement.addTextChangedListener(moneyTextWatcherDisplacement);

        EditText lessonValuePerHour = (EditText) findViewById(R.id.editTextLessonValuePerHour);
        MoneyTextWatcher moneyTextWatcherValuePerHour = new MoneyTextWatcher(lessonValuePerHour);
        lessonValuePerHour.addTextChangedListener(moneyTextWatcherValuePerHour);

        EditText lessonDuration = (EditText) findViewById(R.id.editTextLessonDuration);
        MoneyTextWatcher moneyTextWatcherDuration = new MoneyTextWatcher(lessonDuration);
        lessonDuration.addTextChangedListener(moneyTextWatcherDuration);
    }

    private void listenButtonClickEvents() {
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
        if(isLessonValid()){
            View view = findViewById(R.id.activity_lesson_form);
            FirebaseUtils.saveLesson(view);

            finish();
        }
    }

    private boolean isLessonValid() {
        boolean isValid = false;

        if (isValueValid()
                && isDurationValid()) {
            isValid = true;
        }

        return isValid;
    }

    private boolean isDurationValid() {
        boolean isValid = true;
        EditText duration = (EditText) findViewById(R.id.editTextLessonDuration);

        if (duration.getText().toString().isEmpty()) {
            duration.setError("Você precisa dizer quanto tempo durou a aula");
            isValid = false;
        }

        return isValid;
    }

    private boolean isValueValid() {
        boolean isValid = true;
        EditText valuePerHour = (EditText) findViewById(R.id.editTextLessonValuePerHour);

        if (valuePerHour.getText().toString().isEmpty()) {
            valuePerHour.setError("Você precisa dizer o quanto a aula custou ao Aluno");
            isValid = false;
        }

        return isValid;
    }

    private void setUpStudentSpinner() {
        ArrayList<CharSequence> studentsList = FirebaseUtils.getStudentsList();

        ArrayAdapter<CharSequence> studentAdapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.spinner_item, studentsList);

        Spinner studentSpinner = (Spinner) findViewById(R.id.spinnerLessonStudent);
        studentSpinner.setAdapter(studentAdapter);
    }

    private void setUpTeacherSpinner() {
        ArrayList<CharSequence> teachersList = FirebaseUtils.getTeachersList();

        ArrayAdapter<CharSequence> teacherAdapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.spinner_item, teachersList);

        Spinner teacherSpinner = (Spinner) findViewById(R.id.spinnerLessonTeacher);
        teacherSpinner.setAdapter(teacherAdapter);
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

                if (hour >= 10 && minute >= 10) { // no need to add 0
                    ((EditText) findViewById(R.id.editTextLessonTime)).setText(hour + ":" + minute);
                } else if (hour < 10 && minute >= 10) { // adds 0 on the hour
                    ((EditText) findViewById(R.id.editTextLessonTime)).setText("0" + hour + ":"
                            + minute);
                } else if (hour >= 10 && minute < 10) { // adds 0 on the minute
                    ((EditText) findViewById(R.id.editTextLessonTime)).setText(hour + ":" + "0"
                            + minute);
                } else { // adds 0 on the hour and the minute
                    ((EditText) findViewById(R.id.editTextLessonTime)).setText("0" + hour + ":" + "0"
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
}
