package com.reforcointeligente.brainstormapp.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.FirebaseDatabase;
import com.reforcointeligente.brainstormapp.Controller.FirebaseUtils;
import com.reforcointeligente.brainstormapp.Model.Student;
import com.reforcointeligente.brainstormapp.R;

import com.google.firebase.database.DatabaseReference;

public class StudentFormActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);

        setUpSpinner();

        Button cancelStudentButton = (Button) findViewById(R.id.buttonCancelStudent);
        cancelStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button confirmStudentButton = (Button) findViewById(R.id.buttonConfirmStudent);
        confirmStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStudentCreated();
            }
        });
    }

    private void setUpSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinnerStudentCity);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.list_df_cities, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
    }

    public void onStudentCreated() {
        View view = findViewById(R.id.activity_student_form);
        FirebaseUtils.saveStudent(view);

        finish();
    }
}
