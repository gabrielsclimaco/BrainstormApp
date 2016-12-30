package com.reforcointeligente.brainstormapp.View.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.reforcointeligente.brainstormapp.Controller.Utils;
import com.reforcointeligente.brainstormapp.Model.Student;
import com.reforcointeligente.brainstormapp.R;
import com.reforcointeligente.brainstormapp.View.StudentFormActivity;

import java.util.ArrayList;
import java.util.List;

public class StudentFragment extends Fragment {

    private ListView listStudent;

    public StudentFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.student_fragment, container, false);

        listStudent = (ListView) rootView.findViewById(R.id.list_student);

        Button newStudentButton = (Button) rootView.findViewById(R.id.list_new_student);

        newStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StudentFormActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void loadStudentList() {
        try {
            List<Student> students = Utils.getListOfStudents();

            ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(getContext(), android.R.layout.simple_list_item_1, students);

            if (adapter != null){
                listStudent.setAdapter(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

//        loadStudentList();
    }

    public static StudentFragment newInstance() {
        return new StudentFragment();
    }
}
