package com.reforcointeligente.brainstormapp.View.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.reforcointeligente.brainstormapp.Controller.FirebaseUtils;
import com.reforcointeligente.brainstormapp.Model.Student;
import com.reforcointeligente.brainstormapp.R;
import com.reforcointeligente.brainstormapp.View.SelectedObjectActivity;
import com.reforcointeligente.brainstormapp.View.StudentFormActivity;

public class StudentFragment extends Fragment {
    private ListView listStudent;

    public StudentFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.student_fragment, container, false);

        listStudent = (ListView) rootView.findViewById(R.id.list_student);

        listStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Student student = (Student) listStudent.getItemAtPosition(position);

                Intent goToSelectedStudent = new Intent(getContext(), SelectedObjectActivity.class);
                startActivity(goToSelectedStudent);
            }
        });


        Button newStudentButton = (Button) rootView.findViewById(R.id.list_new_student);

        newStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StudentFormActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listStudent);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        // loading list of student
        listStudent.setAdapter(FirebaseUtils.loadStudents(getActivity()));
    }

    public static StudentFragment newInstance() {
        return new StudentFragment();
    }

}
