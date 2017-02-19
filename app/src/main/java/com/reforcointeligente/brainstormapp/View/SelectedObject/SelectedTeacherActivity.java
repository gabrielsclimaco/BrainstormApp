package com.reforcointeligente.brainstormapp.View.SelectedObject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.reforcointeligente.brainstormapp.Model.Teacher;
import com.reforcointeligente.brainstormapp.R;

import java.util.List;

public class SelectedTeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_teacher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTextFields();
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

    private void setTextFields() {
        Intent intent = getIntent();
        Teacher teacher = (Teacher) intent.getSerializableExtra("teacher");

        setStringText((TextView) findViewById(R.id.teacher_name_text), teacher.getTeacherName());
        setStringText((TextView) findViewById(R.id.teacher_address_text), teacher.getTeacherAddress());
        setStringText((TextView) findViewById(R.id.teacher_city_text), teacher.getTeacherCity());
        setStringText((TextView) findViewById(R.id.teacher_phone_text), teacher.getTeacherPhone());
        setStringText((TextView) findViewById(R.id.teacher_cellphone_text), teacher.getTeacherCellphone());
        setStringText((TextView) findViewById(R.id.teacher_course_text), teacher.getTeacherCourse());
        setBooleanText((TextView) findViewById(R.id.teacher_car_text), teacher.getTeacherCar());
        setStringText((TextView) findViewById(R.id.teacher_pricePerHour_text), teacher.getPricePerHour().toString()+"0");
        setStringText((TextView) findViewById(R.id.teacher_email_text), teacher.getTeacherEmail());
        setSubjectText((TextView) findViewById(R.id.teacher_subjects_text), teacher.getTeacherSubjects());


        ((TextView) findViewById(R.id.teacher_valueToPay_text)).setText(teacher.getTeacherValueToPay().toString());
    }

    private void setSubjectText(TextView teacherSubjectsTextView, List<String> teacherSubjects) {
        if (teacherSubjects == null) {
            teacherSubjectsTextView.setText("Não consta");
        } else {
            teacherSubjectsTextView.setText(teacherSubjects.toString());
        }
    }

    private void setStringText(TextView textView, String text) {
        if (text != "") {
            textView.setText(text);
        } else {
            textView.setText("Não consta");
        }
    }

    private void setBooleanText(TextView textView, Boolean hasCar) {
        if (hasCar == true) {
            textView.setText("Sim");
        } else {
            textView.setText("Não");
        }
    }
}
