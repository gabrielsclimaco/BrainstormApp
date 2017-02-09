package com.reforcointeligente.brainstormapp.View.SelectedObject;

import android.app.ActionBar;import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.reforcointeligente.brainstormapp.Model.Lesson;
import com.reforcointeligente.brainstormapp.R;

import java.util.ArrayList;

public class SelectedLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_lesson);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTextFields();
    }

    private void setTextFields() {
        Intent intent = getIntent();
        Lesson lesson = (Lesson) intent.getSerializableExtra("lesson");

        setStringText((TextView) findViewById(R.id.lesson_date_text), lesson.getLessonDate());
        setStringText((TextView) findViewById(R.id.lesson_time_text), lesson.getLessonTime());
        setStringText((TextView) findViewById(R.id.lesson_teacher_text), lesson.getLessonTeacher());
        setStringText((TextView) findViewById(R.id.lesson_student_text), lesson.getLessonStudent());
        setSubjectText((TextView) findViewById(R.id.lesson_subject_text), lesson.getLessonSubject());
        setStringText((TextView) findViewById(R.id.lesson_place_text), lesson.getLessonPlace());

        ((TextView) findViewById(R.id.lesson_displacement_text)).setText(lesson.getLessonDisplacement().toString());
        ((TextView) findViewById(R.id.lesson_valuePerHour_text)).setText(lesson.getLessonValuePerHour().toString());
        ((TextView) findViewById(R.id.lesson_duration_text)).setText(lesson.getLessonDuration().toString());
        ((TextView) findViewById(R.id.lesson_totalValue_text)).setText(lesson.getLessonTotalValue().toString());
    }

    private void setSubjectText(TextView lessonSubjectsTextView, ArrayList<String> lessonSubjects) {
        if (lessonSubjects == null) {
            lessonSubjectsTextView.setText("Não consta");
        } else {
            lessonSubjectsTextView.setText(lessonSubjects.toString());
        }
    }


    private void setStringText(TextView textView, String text) {
        if (text != "") {
            textView.setText(text);
        } else {
            textView.setText("Não consta");
        }
    }

}
