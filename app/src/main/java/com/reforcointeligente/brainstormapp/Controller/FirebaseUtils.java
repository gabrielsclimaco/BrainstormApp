package com.reforcointeligente.brainstormapp.Controller;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListAdapter;
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
import com.reforcointeligente.brainstormapp.View.Forms.StudentFormActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

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
        String teacherName = ((Spinner) view.findViewById(R.id.spinnerLessonTeacher))
                .getSelectedItem()
                .toString();
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

        Lesson lessonToSave = new Lesson(date, time, teacherName, studentName, lessonSubjects,
                place, displacement, valuePerHour, duration);
        lessonToSave = setLessonValuesAndDoCharges(lessonToSave);

        databaseReference.child("Lessons").push().setValue(lessonToSave);

    }

    private static Lesson setLessonValuesAndDoCharges(Lesson lesson) {
        final Double totalLessonValue = lesson.getLessonTotalValue();

        chargeStudent(lesson, totalLessonValue);
        lesson = payTeacher(lesson, totalLessonValue);

        return lesson;
    }

    private static Lesson payTeacher(final Lesson lesson, final Double totalLessonValue) {
//        final ArrayList<Double> profit = new ArrayList<>();
//        profit.add(0.0);

        FirebaseDatabase.getInstance().getReference().child("Teachers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot teacherSnapshot : dataSnapshot.getChildren()) {
                    Teacher teacher = teacherSnapshot.getValue(Teacher.class);
                    if (teacher.getTeacherName().toUpperCase().trim()
                            .equals(lesson.getLessonTeacher().toUpperCase().trim())) {
                        Double previousPay = teacher.getTeacherValueToPay();
                        Double toPayNow = teacher.getTeacherPricePerHour() * lesson.getLessonDuration()
                                + lesson.getLessonDisplacement();
                        lesson.setLessonProfit(lesson.getLessonTotalValue() - toPayNow);
//                        profit.add(lesson.getLessonTotalValue() - toPayNow);


                        teacher.setTeacherValueToPay(previousPay + toPayNow);
                        Map<String, Object> postStudent = teacher.toMap();
                        Map<String, Object> updateChildren = new HashMap<>();

                        updateChildren.put("/Teachers/" + teacherSnapshot.getKey(), postStudent);
                        databaseReference.updateChildren(updateChildren);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return lesson;
    }

    private static void chargeStudent(final Lesson lesson, final Double totalLessonValue) {
        FirebaseDatabase.getInstance().getReference().child("Students").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()) {
                    Student student = studentSnapshot.getValue(Student.class);
                    if (student.getStudentName().toUpperCase().trim()
                            .equals(lesson.getLessonStudent().toUpperCase().trim())) {
                        Double previousDebt = student.getStudentDebt();

                        student.setStudentDebt(previousDebt + totalLessonValue);
                        Map<String, Object> postStudent = student.toMap();
                        Map<String, Object> updateChildren = new HashMap<>();

                        updateChildren.put("/Students/" + studentSnapshot.getKey(), postStudent);
                        databaseReference.updateChildren(updateChildren);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
                String title = lesson.getLessonDate() + " - " + lesson.getLessonTime();
                ((TextView) view.findViewById(android.R.id.text1)).setText(title);
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

        FirebaseDatabase.getInstance().getReference().child("Students").orderByChild("studentName")
        .addValueEventListener(new ValueEventListener() {
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

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Teachers");
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

    public static FirebaseListAdapter<Student> orderListOfStudentsByName(FragmentActivity fragmentActivity) {
        FirebaseListAdapter<Student> adapter = new FirebaseListAdapter<Student>(fragmentActivity, Student.class,
                android.R.layout.two_line_list_item,
                databaseReference.child("Students").orderByChild("studentName")) {
            @Override
            protected void populateView(View view, Student student, int position) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(student.getStudentName());
                ((TextView) view.findViewById(android.R.id.text2)).setText(student.getStudentParentName());
            }
        };

        return adapter;
    }

    public static FirebaseListAdapter<Student> orderListOfStudentsByParent(FragmentActivity fragmentActivity) {
        FirebaseListAdapter<Student> adapter = new FirebaseListAdapter<Student>(fragmentActivity, Student.class,
                android.R.layout.two_line_list_item,
                databaseReference.child("Students").orderByChild("studentParentName")) {
            @Override
            protected void populateView(View view, Student student, int position) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(student.getStudentName());
                ((TextView) view.findViewById(android.R.id.text2)).setText(student.getStudentParentName());
            }
        };

        return adapter;
    }

    public static FirebaseListAdapter<Teacher> orderListOfTeachersByName(FragmentActivity fragmentActivity) {
        FirebaseListAdapter<Teacher> adapter = new FirebaseListAdapter<Teacher>(fragmentActivity, Teacher.class,
                android.R.layout.two_line_list_item,
                databaseReference.child("Teachers").orderByChild("teacherName")) {
            @Override
            protected void populateView(View view, Teacher teacher, int position) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(teacher.getTeacherName());
                ((TextView) view.findViewById(android.R.id.text2)).setText(teacher.getTeacherCourse());
            }
        };

        return adapter;
    }

    public static FirebaseListAdapter<Teacher> orderListOfTeachersByCourse(FragmentActivity fragmentActivity) {
        FirebaseListAdapter<Teacher> adapter = new FirebaseListAdapter<Teacher>(fragmentActivity, Teacher.class,
                android.R.layout.two_line_list_item,
                databaseReference.child("Teachers").orderByChild("teacherCourse")) {
            @Override
            protected void populateView(View view, Teacher teacher, int position) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(teacher.getTeacherName());
                ((TextView) view.findViewById(android.R.id.text2)).setText(teacher.getTeacherCourse());
            }
        };

        return adapter;
    }

    public static FirebaseListAdapter<Lesson> orderListOfLessonsByDate(FragmentActivity fragmentActivity) {
        FirebaseListAdapter<Lesson> adapter = new FirebaseListAdapter<Lesson>(fragmentActivity, Lesson.class,
                android.R.layout.two_line_list_item,
                databaseReference.child("Lessons").orderByChild("lessonDate")) {
            @Override
            protected void populateView(View view, Lesson lesson, int position) {
                String title = lesson.getLessonDate() + " - " + lesson.getLessonTime();
                ((TextView) view.findViewById(android.R.id.text1)).setText(title);
                String subtitle = "Aluno: " + lesson.getLessonStudent() + "\nProfessor: " +
                        lesson.getLessonTeacher();
                ((TextView) view.findViewById(android.R.id.text2)).setText(subtitle);
            }
        };

        return adapter;
    }

    public static FirebaseListAdapter<Lesson> orderListOfLessonsByStudentName(FragmentActivity fragmentActivity) {
        FirebaseListAdapter<Lesson> adapter = new FirebaseListAdapter<Lesson>(fragmentActivity, Lesson.class,
                android.R.layout.two_line_list_item,
                databaseReference.child("Lessons").orderByChild("lessonStudent")) {
            @Override
            protected void populateView(View view, Lesson lesson, int position) {
                String title = lesson.getLessonDate() + " - " + lesson.getLessonTime();
                ((TextView) view.findViewById(android.R.id.text1)).setText(title);
                String subtitle = "Aluno: " + lesson.getLessonStudent() + "\nProfessor: " +
                        lesson.getLessonTeacher();
                ((TextView) view.findViewById(android.R.id.text2)).setText(subtitle);
            }
        };

        return adapter;
    }

    public static FirebaseListAdapter<Lesson> orderListOfLessonsByTeacherName(FragmentActivity fragmentActivity) {
        FirebaseListAdapter<Lesson> adapter = new FirebaseListAdapter<Lesson>(fragmentActivity, Lesson.class,
                android.R.layout.two_line_list_item,
                databaseReference.child("Lessons").orderByChild("lessonTeacher")) {
            @Override
            protected void populateView(View view, Lesson lesson, int position) {
                String title = lesson.getLessonDate() + " - " + lesson.getLessonTime();
                ((TextView) view.findViewById(android.R.id.text1)).setText(title);
                String subtitle = "Aluno: " + lesson.getLessonStudent() + "\nProfessor: " +
                        lesson.getLessonTeacher();
                ((TextView) view.findViewById(android.R.id.text2)).setText(subtitle);
            }
        };

        return adapter;
    }

    public static void excludeStudent(final Student student) {
        databaseReference.child("Students").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot students: dataSnapshot.getChildren()) {
                    if (student.getStudentName().equals(students.getValue(Student.class).getStudentName())) {
                        students.getRef().removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void excludeLesson(final Lesson lesson) {
        databaseReference.child("Lessons").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot lessons: dataSnapshot.getChildren()) {
                    if (lesson.getLessonDate().equals(lessons.getValue(Lesson.class).getLessonDate())
                            && lesson.getLessonTime().equals(lessons.getValue(Lesson.class).getLessonTime())
                            && lesson.getLessonStudent().equals(lessons.getValue(Lesson.class).getLessonStudent())
                            && lesson.getLessonTeacher().equals(lessons.getValue(Lesson.class).getLessonTeacher())) {
                        lessons.getRef().removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void excludeTeacher(final Teacher teacher) {
        databaseReference.child("Teachers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot teachers: dataSnapshot.getChildren()) {
                    if (teacher.getTeacherName().equals(teachers.getValue(Teacher.class).getTeacherName())) {
                        teachers.getRef().removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void fillWithStudentInfo(Student student, Activity activity) {
        EditText nameField = (EditText) activity.findViewById(R.id.editTextStudentName);
        nameField.setText(student.getStudentName());

        EditText ageField = (EditText) activity.findViewById(R.id.editTextStudentAge);
        ageField.setText(student.getStudentAge().toString());

        EditText schoolField = (EditText) activity.findViewById(R.id.editTextStudentSchool);
        schoolField.setText(student.getStudentSchool());

        EditText schoolYearField = (EditText) activity.findViewById(R.id.editTextStudentSchoolYear);
        schoolYearField.setText(student.getStudentSchoolYear());

        EditText addressField = (EditText) activity.findViewById(R.id.editTextStudentAddress);
        addressField.setText(student.getStudentAddress());

        Spinner cityField = (Spinner) activity.findViewById(R.id.spinnerStudentCity);
        String[] cities = activity.getResources().getStringArray(R.array.list_df_cities);
        cityField.setSelection(Arrays.asList(cities).indexOf(student.getStudentCity()));

        EditText parentNameField = (EditText) activity.findViewById(R.id.editTextStudentParentName);
        parentNameField.setText(student.getStudentParentName());

        EditText parentCellphoneField = (EditText) activity.findViewById(R.id.editTextStudentParentCellphone);
        parentCellphoneField.setText(student.getStudentParentCellphone());

        EditText parentPhoneField = (EditText) activity.findViewById(R.id.editTextStudentParentPhone);
        parentPhoneField.setText(student.getStudentParentPhone());

        EditText parentEmailField = (EditText) activity.findViewById(R.id.editTextStudentParentEmail);
        parentEmailField.setText(student.getStudentParentEmail());

    }

    public static void fillWithTeacherInfo(Teacher teacher, Activity activity) {
        EditText nameField = (EditText) activity.findViewById(R.id.editTextTeacherName);
        nameField.setText(teacher.getTeacherName());

        EditText addressField = (EditText) activity.findViewById(R.id.editTextTeacherAddress);
        addressField.setText(teacher.getTeacherAddress());

        Spinner cityField = (Spinner) activity.findViewById(R.id.spinnerTeacherCity);
        String[] cities = activity.getResources().getStringArray(R.array.list_df_cities);
        cityField.setSelection(Arrays.asList(cities).indexOf(teacher.getTeacherCity()));

        EditText courseField = (EditText) activity.findViewById(R.id.editTextTeacherCourse);
        courseField.setText(teacher.getTeacherCourse());

        EditText phoneField = (EditText) activity.findViewById(R.id.editTextTeacherPhone);
        phoneField.setText(teacher.getTeacherPhone());

        EditText cellphoneField = (EditText) activity.findViewById(R.id.editTextTeacherCellphone);
        cellphoneField.setText(teacher.getTeacherCellphone());

        //carro boolean

        EditText pricePerHourField = (EditText) activity.findViewById(R.id.editTextTeacherValuePerHour);
        pricePerHourField.setText(teacher.getTeacherPricePerHour().toString());

        EditText emailField = (EditText) activity.findViewById(R.id.editTextTeacherEmail);
        emailField.setText(teacher.getTeacherEmail());

        //materias array

        //total a receber?

    }

    public static void fillWithLessonInfo(Lesson lesson, Activity activity) {
        EditText dateField = (EditText) activity.findViewById(R.id.editTextLessonDate);
        dateField.setText(lesson.getLessonDate());

        EditText timeField = (EditText) activity.findViewById(R.id.editTextLessonTime);
        timeField.setText(lesson.getLessonTime());

        // professor

        // aluno

        // materias array

        EditText placeField = (EditText) activity.findViewById(R.id.editTextLessonPlace);
        placeField.setText(lesson.getLessonPlace());

        EditText displacementField = (EditText) activity.findViewById(R.id.editTextLessonDisplacement);
        displacementField.setText(lesson.getLessonDisplacement().toString());

        EditText valuePerHourField = (EditText) activity.findViewById(R.id.editTextLessonValuePerHour);
        valuePerHourField.setText(lesson.getLessonValuePerHour().toString());

        EditText durationField = (EditText) activity.findViewById(R.id.editTextLessonDuration);
        durationField.setText(lesson.getLessonDuration().toString());

        // valor total

    }
}
