package com.reforcointeligente.brainstormapp.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.reforcointeligente.brainstormapp.Model.Student;
import com.reforcointeligente.brainstormapp.R;

public class StudentFormActivity extends AppCompatActivity {
    Spinner spinner;
    Button confirmStudentButton;
    Button cancelStudentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);
        setUpSpinner();

        cancelStudentButton = (Button) findViewById(R.id.buttonCancelStudent);
        cancelStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        confirmStudentButton = (Button) findViewById(R.id.buttonConfirmStudent);
        confirmStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setUpSpinner() {
        spinner = (Spinner) findViewById(R.id.spinnerStudentCity);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.list_df_cities, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
    }

    public void onTeacherCreated() {
        Student student = new Student();

        EditText studentNameEditText = (EditText) findViewById(R.id.editTextStudentName);
        EditText studentAgeEditText = (EditText) findViewById(R.id.editTextStudentAge);
        EditText studentSchoolEditText = (EditText) findViewById(R.id.editTextStudentSchool);
        EditText studentAddressEditText = (EditText) findViewById(R.id.editTextStudentAddress);
        EditText studentParentNameEditText = (EditText) findViewById(R.id.editTextStudentParentName);
        EditText studentParentCellphoneEditText = (EditText) findViewById(R.id.editTextStudentParentCellphone);
        EditText studentParentPhoneEditText = (EditText) findViewById(R.id.editTextStudentParentPhone);
        EditText studentParentEmailEditText = (EditText) findViewById(R.id.editTextStudentParentEmail);

        String studentName = studentNameEditText.getText().toString();
        String studentAge = studentAgeEditText.getText().toString();
        String studentSchool = studentSchoolEditText.getText().toString();
        String studentAddress = studentAddressEditText.getText().toString();
        String studentCity = spinner.getSelectedItem().toString();
        String studentParentName = studentParentNameEditText.getText().toString();
        String studentParenteCellphone = studentParentCellphoneEditText.getText().toString();
        String studentParentPhone = studentParentPhoneEditText.getText().toString();
        String studentParentEmail = studentParentEmailEditText.getText().toString();

        student.setStudentName(studentName);
        student.setStudentAge(studentAge);
        student.setStudentSchool(studentSchool);
        student.setStudentAddress(studentAddress);
        student.setStudentCity(studentCity);
        student.setStudentParentName(studentParentName);
        student.setStudentParentCellphone(studentParenteCellphone);
        student.setStudentParentPhone(studentParentPhone);
        student.setStudentParentEmail(studentParentEmail);

    }

}
