package com.reforcointeligente.brainstormapp.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.reforcointeligente.brainstormapp.Model.Lesson;
import com.reforcointeligente.brainstormapp.R;

public class LessonFormActivity extends AppCompatActivity {

    private Button confirmLessonButton;
    private Button cancelLessonButton;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_form);

        cancelLessonButton = (Button) findViewById(R.id.buttonCancelLesson);
        cancelLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        confirmLessonButton = (Button) findViewById(R.id.buttonConfirmLesson);
        confirmLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setUpSpinner() {
        spinner = (Spinner) findViewById(R.id.spinnerSubject);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.list_of_subjects, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
    }

    public void onLessonCreated() {
        Lesson lesson = new Lesson();

        EditText dateEditText = (EditText) findViewById(R.id.editTextDate);
        EditText timeEditText = (EditText) findViewById(R.id.editTextTime);
        EditText placeEditText = (EditText) findViewById(R.id.editTextPlace);
        EditText displacementEditText = (EditText) findViewById(R.id.editTextDisplacement);
        EditText valuePerHourEditText = (EditText) findViewById(R.id.editTextValuePerHour);
        EditText durationLessonEditText = (EditText) findViewById(R.id.editTextLessonDuration);
        EditText totalLessonValueEditText = (EditText) findViewById(R.id.editTextTotalLessonValue);
        EditText valuePaidEditText = (EditText) findViewById(R.id.editTextValuePaid);

        String subject = spinner.getSelectedItem().toString();
        String date = dateEditText.getText().toString();
        String time = timeEditText.getText().toString();
        String place = placeEditText.getText().toString();
        String displacement = displacementEditText.getText().toString();
        Double valuePerHour = Double.valueOf(valuePerHourEditText.getText().toString());
        Double duration = Double.valueOf(durationLessonEditText.getText().toString());
        Double totalLessonValue = Double.valueOf(totalLessonValueEditText.getText().toString());
        Double valuePaid = Double.valueOf(valuePaidEditText.getText().toString());

        lesson.setLessonDate(date);
        lesson.setLessonTime(time);
        lesson.setLessonSubject(subject);
        lesson.setLessonPlace(place);
        lesson.setLessonDuration(duration);

        // falta displacement, value per hour, total lesson value e value paid


    }
}
