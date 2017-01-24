package com.reforcointeligente.brainstormapp.View.SelectedObject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.reforcointeligente.brainstormapp.Model.Teacher;
import com.reforcointeligente.brainstormapp.R;

public class SelectedTeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_teacher);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTextFields();
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
        setStringText((TextView) findViewById(R.id.teacher_subjects_text), teacher.getTeacherSubjects().toString());

        ((TextView) findViewById(R.id.teacher_valueToPay_text)).setText(teacher.getTeacherValueToPay().toString());
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
