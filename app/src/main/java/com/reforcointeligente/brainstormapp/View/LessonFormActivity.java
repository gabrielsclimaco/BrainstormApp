package com.reforcointeligente.brainstormapp.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.reforcointeligente.brainstormapp.R;

public class LessonFormActivity extends AppCompatActivity {

    private Button confirmLessonButton;
    private Button cancelLessonButton;

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
}
