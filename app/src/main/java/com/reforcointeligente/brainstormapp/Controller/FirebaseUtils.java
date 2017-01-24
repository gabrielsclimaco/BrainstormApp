package com.reforcointeligente.brainstormapp.Controller;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.reforcointeligente.brainstormapp.Model.Student;
import com.reforcointeligente.brainstormapp.Model.Lesson;
import com.reforcointeligente.brainstormapp.Model.Teacher;
import com.reforcointeligente.brainstormapp.R;

import java.util.ArrayList;
import java.util.List;

public class FirebaseUtils {
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public static void saveStudent(View view){
        String name = ((EditText) view.findViewById(R.id.editTextStudentName)).getText().toString();
        String school = ((EditText) view.findViewById(R.id.editTextStudentSchool)).getText().toString();
        String schoolYear = ((EditText) view.findViewById(R.id.editTextStudentSchoolYear)).getText().toString();
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

        Student studentToSave = new Student(name, age, address, city, schoolYear, school, nameParent,
                phoneParent, cellphoneParent, emailParent, 0.0);

        databaseReference.child("Students").push().setValue(studentToSave);
    }

    public static void saveLesson(View view){
        String date = ((EditText) view.findViewById(R.id.editTextLessonDate)).getText().toString();
        String time = ((EditText) view.findViewById(R.id.editTextLessonTime)).getText().toString();
        //Teacher teacher = ((Spinner) view.findViewById(R.id.spinnerLessonTeacher)).getSelectedItem();
        String studentName = ((Spinner) view.findViewById(R.id.spinnerLessonStudent))
                .getSelectedItem()
                .toString();
        String place = ((EditText) view.findViewById(R.id.editTextLessonPlace)).getText().toString();

        // assures that int is not a empty string
        Double displacement = 0.0;
        if (!((EditText) view.findViewById(R.id.editTextLessonDisplacement)).getText()
                .toString().equals("")) {
            displacement = Double.valueOf(((EditText)
                    view.findViewById(R.id.editTextLessonDisplacement)).getText().toString());
        }

        // assures that int is not a empty string
        Double valuePerHour = 0.0;
        if (!((EditText) view.findViewById(R.id.editTextLessonValuePerHour)).getText()
                .toString().equals("")) {
            valuePerHour = Double.valueOf(((EditText)
                    view.findViewById(R.id.editTextLessonValuePerHour)).getText().toString());
        }

        // assures that int is not a empty string
        Double duration = 0.0;
        if (!((EditText) view.findViewById(R.id.editTextLessonDuration)).getText()
                .toString().equals("")) {
            duration = Double.valueOf(((EditText)
                    view.findViewById(R.id.editTextLessonDuration)).getText().toString());
        }

        GridLayout subjectsForm = (GridLayout) view.findViewById(R.id.gridSubjectsLessonForm);
        ArrayList<String> lessonSubjects = new ArrayList<>();

        for (int i = 0; i < subjectsForm.getChildCount(); i++) {
            CheckBox subject = ((CheckBox) subjectsForm.getChildAt(i));

            if (subject.isChecked()) {
                lessonSubjects.add(subject.getText().toString());
            }
        }

        Lesson lessonToSave = new Lesson(date, time, "", studentName, lessonSubjects,
                place, displacement, valuePerHour, duration);

        databaseReference.child("Lessons").push().setValue(lessonToSave);

    }

    public static void saveTeacher(View view){
        List<String> teacherSubjects = new ArrayList<>();

        String name = ((EditText) view.findViewById(R.id.editTextTeacherName)).getText().toString();
        String address = ((EditText) view.findViewById(R.id.editTextTeacherAddress)).getText().toString();
        String city = ((Spinner) view.findViewById(R.id.spinnerTeacherCity)).getSelectedItem().toString();
        String phone = ((EditText) view.findViewById(R.id.editTextTeacherPhone)).getText().toString();
        String cellphone = ((EditText) view.findViewById(R.id.editTextTeacherCellphone)).getText().toString();
        String course = ((EditText) view.findViewById(R.id.editTextTeacherCourse)).getText().toString();
        String email = ((EditText) view.findViewById(R.id.editTextTeacherEmail)).getText().toString();

        Boolean car = false;
        if (((RadioButton) view.findViewById(R.id.radioButtonNo)).isChecked()) {
            car = false;
        } else if (((RadioButton) view.findViewById(R.id.radioButtonYes)).isChecked()) {
            car = true;
        }

        // assures that int is not a empty string
        Double pricePerHour = 0.0;
        if (!((EditText) view.findViewById(R.id.editTextTeacherPricePerHour)).getText()
                .toString().equals("")) {
            pricePerHour = Double.valueOf(((EditText)
                    view.findViewById(R.id.editTextTeacherPricePerHour)).getText().toString());
        }

        GridLayout subjectsForm = (GridLayout) view.findViewById(R.id.gridSubjectsTeacherForm);

        for (int i = 0; i < subjectsForm.getChildCount(); i++) {
            CheckBox subject = ((CheckBox) subjectsForm.getChildAt(i));

            if (subject.isChecked()) {
                teacherSubjects.add(subject.getText().toString());
            }
        }

        Teacher teacherToSave = new Teacher(name, address, city, phone, cellphone, course,
                car, pricePerHour, email, teacherSubjects, 0.0);

        databaseReference.child("Teachers").push().setValue(teacherToSave);

    }

    public static FirebaseListAdapter<Student> loadStudents(FragmentActivity fragmentActivity) {
        FirebaseListAdapter<Student> adapter = new FirebaseListAdapter<Student>(fragmentActivity, Student.class,
                android.R.layout.two_line_list_item,
                databaseReference.child("Students")) {
            @Override
            protected void populateView(View view, Student student, int position) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(student.getStudentName());
                ((TextView) view.findViewById(android.R.id.text2)).setText(student.getStudentParentName());
            }
        };

        return adapter;
    }

    public static FirebaseListAdapter<Lesson> loadLessons(FragmentActivity fragmentActivity) {
        FirebaseListAdapter<Lesson> adapter = new FirebaseListAdapter<Lesson>(fragmentActivity, Lesson.class,
                android.R.layout.two_line_list_item,
                databaseReference.child("Lessons")) {
            @Override
            protected void populateView(View view, Lesson lesson, int position) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(lesson.getLessonDate());
                String subtitle = "Aluno: " + lesson.getLessonStudent() + "\nProfessor: " +
                        lesson.getLessonTeacher();
                ((TextView) view.findViewById(android.R.id.text2)).setText(subtitle);
            }
        };

        return adapter;
    }

    public static FirebaseListAdapter<Teacher> loadTeachers(FragmentActivity fragmentActivity) {
        FirebaseListAdapter<Teacher> adapter = new FirebaseListAdapter<Teacher>(fragmentActivity, Teacher.class,
                android.R.layout.two_line_list_item,
                databaseReference.child("Teachers")) {
            @Override
            protected void populateView(View view, Teacher teacher, int position) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(teacher.getTeacherName());
                ((TextView) view.findViewById(android.R.id.text2)).setText(teacher.getTeacherCourse());
            }
        };

        return adapter;
    }

    public static ArrayList<CharSequence> getStudentsList() {
        final ArrayList<CharSequence> studentsList = new ArrayList<>();
        studentsList.add("");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Students");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot studentSnapshot: dataSnapshot.getChildren()) {
                    String studentName = studentSnapshot.getValue(Student.class).getStudentName();
                    studentsList.add(studentName);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase error", databaseError.toString());
            }
        });

        return studentsList;
    }

    public static ArrayList<CharSequence> getTeachersList() {
        final ArrayList<CharSequence> teachersList = new ArrayList<>();
        teachersList.add("");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Teacher");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot teacherSnapshot: dataSnapshot.getChildren()) {
                    String teacherName = teacherSnapshot.getValue(Teacher.class).getTeacherName();
                    teachersList.add(teacherName);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase error", databaseError.toString());
            }
        });

        return teachersList;
    }
}
