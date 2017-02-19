package com.reforcointeligente.brainstormapp.View.SelectedObject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.reforcointeligente.brainstormapp.Model.Student;
import com.reforcointeligente.brainstormapp.R;

public class SelectedStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_student);
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
        Student student = (Student) intent.getSerializableExtra("student");

        setStringText((TextView) findViewById(R.id.student_name_text), student.getStudentName());
        setIntText((TextView) findViewById(R.id.student_age_text), student.getStudentAge());
        setStringText((TextView) findViewById(R.id.student_school_text), student.getStudentSchool());
        setStringText((TextView) findViewById(R.id.student_school_year_text), student.getStudentSchoolYear());
        setStringText((TextView) findViewById(R.id.student_address_text), student.getStudentAddress());
        setStringText((TextView) findViewById(R.id.student_city_text), student.getStudentCity());

        setStringText((TextView) findViewById(R.id.student_parent_name_text), student.getStudentParentName());
        setStringText((TextView) findViewById(R.id.student_parent_cellphone_text), student.getStudentParentCellphone());
        setStringText((TextView) findViewById(R.id.student_parent_phone_text), student.getStudentParentPhone());
        setStringText((TextView) findViewById(R.id.student_parent_email_text), student.getStudentParentEmail());

        ((TextView) findViewById(R.id.student_debt_text)).setText(student.getStudentDebt().toString());
    }

    private void setStringText(TextView textView, String text) {
        if (text != "") {
            textView.setText(text);
        } else {
            textView.setText("Não consta");
        }
    }

    private void setIntText(TextView textView, Integer text) {
        if (text != 0) {
            textView.setText(text.toString());
        } else {
            textView.setText("Não consta");
        }
    }
}
