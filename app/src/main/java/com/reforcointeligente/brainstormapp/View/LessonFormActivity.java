package com.reforcointeligente.brainstormapp.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.reforcointeligente.brainstormapp.Model.Lesson;
import com.reforcointeligente.brainstormapp.Model.Student;
import com.reforcointeligente.brainstormapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LessonFormActivity extends AppCompatActivity {

    private Button confirmLessonButton;
    private Button cancelLessonButton;

    Button btnDate;
    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;

    Spinner spinner;
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_form);

        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);

        showDialogOnButtonClick();

        setUpStudentSpinner();
        setUpSubjectSpinner();

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

    public void showDialogOnButtonClick() {
        btnDate = (Button) findViewById(R.id.btnDate);
        btnDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, dPickerListener, year_x, month_x, day_x);
        } else {
            return null;
        }
    }

    private DatePickerDialog.OnDateSetListener dPickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            Toast.makeText(LessonFormActivity.this, year_x + "/" + month_x + "/" + day_x,
                    Toast.LENGTH_LONG).show();
        }
    };

    private void setUpSubjectSpinner() {
        spinner = (Spinner) findViewById(R.id.spinnerLessonSubject);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.list_of_subjects, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
    }

    private void setUpStudentSpinner() {
        DatabaseReference reference = databaseReference.child("Students");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> studentsList = new ArrayList<String>();
                studentsList.add("");

                for (DataSnapshot studentSnapshot: dataSnapshot.getChildren()) {
                    Student student = studentSnapshot.child("Student").getValue(Student.class);
                    studentsList.add(student.getStudentName());
                }

                Spinner studentSpinner = (Spinner) findViewById(R.id.spinnerLessonStudent);
                ArrayAdapter<String> studentAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, studentsList);
                studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                studentSpinner.setAdapter(studentAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void onLessonCreated() {


    }
}
