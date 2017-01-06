package com.reforcointeligente.brainstormapp.Controller;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.reforcointeligente.brainstormapp.Model.Student;
import com.reforcointeligente.brainstormapp.R;

public class FirebaseUtils {
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public static void saveStudent(View view){
        String name = ((EditText) view.findViewById(R.id.editTextStudentName)).getText().toString();
        String school = ((EditText) view.findViewById(R.id.editTextStudentSchool)).getText().toString();
        String address = ((EditText) view.findViewById(R.id.editTextStudentAddress)).getText().toString();
        String city = ((Spinner) view.findViewById(R.id.spinnerStudentCity)).getSelectedItem().toString();
        String nameParent = ((EditText) view.findViewById(R.id.editTextStudentParentName)).getText().toString();
        String cellphoneParent = ((EditText) view.findViewById(R.id.editTextStudentParentCellphone)).getText().toString();
        String phoneParent = ((EditText) view.findViewById(R.id.editTextStudentParentPhone)).getText().toString();
        String emailParent = ((EditText) view.findViewById(R.id.editTextStudentParentEmail)).getText().toString();

        // assures that int is not a empty string
        Integer age = 0;
        if (!((EditText) view.findViewById(R.id.editTextStudentAge)).getText().toString().equals("")) {
            age = Integer.valueOf(((EditText) view.findViewById(R.id.editTextStudentAge)).getText().toString());
        }

        Student studentToSave = new Student(name, age, address, city, "", school, nameParent,
                phoneParent, cellphoneParent, emailParent);

        databaseReference.child("Students").push().setValue(studentToSave);
    }

    public static FirebaseListAdapter<Student> loadStudents(FragmentActivity fragmentActivity) {
        FirebaseListAdapter<Student> adapter = new FirebaseListAdapter<Student>(fragmentActivity, Student.class,
                android.R.layout.two_line_list_item,
                databaseReference.child("Students")) {
            @Override
            protected void populateView(View view, Student student, int position) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(student.getStudentName());
            }
        };

        return adapter;
    }
}
