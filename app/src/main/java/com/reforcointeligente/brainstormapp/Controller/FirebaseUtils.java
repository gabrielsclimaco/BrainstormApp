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
import com.reforcointeligente.brainstormapp.Model.Lesson;
import com.reforcointeligente.brainstormapp.Model.Teacher;
import com.reforcointeligente.brainstormapp.R;

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
                ((TextView) view.findViewById(android.R.id.text2)).setText(student.getStudentParentName());
            }
        };

        return adapter;
    }

    public static void saveLesson(View view){
        String date = ((EditText) view.findViewById(R.id.editTextLessonDate)).getText().toString();
        String time = ((EditText) view.findViewById(R.id.editTextLessonTime)).getText().toString();
        //Teacher teacher = ((Spinner) view.findViewById(R.id.spinnerLessonTeacher)).getSelectedItem();
        //Student student = ((Spinner) view.findViewById(R.id.spinnerLessonStudent)).getSelectedItem();
        String subject = ((Spinner) view.findViewById(R.id.spinnerLessonSubject)).getSelectedItem()
                .toString();
        String place = ((EditText) view.findViewById(R.id.editTextLessonPlace)).getText()
                .toString();

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

        Student student = new Student();
        Teacher teacher = new Teacher();

        Lesson lessonToSave = new Lesson(date, time, teacher, student, subject,
                place, displacement, valuePerHour, duration);

        databaseReference.child("Lessons").push().setValue(lessonToSave);

    }

    public static FirebaseListAdapter<Lesson> loadLessons(FragmentActivity fragmentActivity) {
        FirebaseListAdapter<Lesson> adapter = new FirebaseListAdapter<Lesson>(fragmentActivity, Lesson.class,
                android.R.layout.two_line_list_item,
                databaseReference.child("Lessons")) {
            @Override
            protected void populateView(View view, Lesson lesson, int position) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(lesson.getLessonDate());
            }
        };

        return adapter;
    }

    public static void saveTeacher(View view){
//        private String teacherName;
//        private String teacherAddress;
//        private String teacherCity;
//        private String teacherPhone;
//        private String teacherCellphone;
//        private String teacherCourse;
//        private Boolean teacherCar;
//        private Double pricePerHour;
//        private String teacherEmail;
//        private List<String> teacherSubjects;

        String name = ((EditText) view.findViewById(R.id.editTextTeacherName)).getText().toString();
        String address = ((EditText) view.findViewById(R.id.editTextTeacherAddress)).getText().toString();
        String city = ((Spinner) view.findViewById(R.id.spinnerTeacherCity)).getSelectedItem().toString();
        String phone = ((EditText) view.findViewById(R.id.editTextTeacherPhone)).getText().toString();
        String cellphone = ((EditText) view.findViewById(R.id.editTextTeacherCellphone)).getText().toString();
        String course = ((EditText) view.findViewById(R.id.editTextTeacherCourse)).getText().toString();



    }
}
