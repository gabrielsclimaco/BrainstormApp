package com.reforcointeligente.brainstormapp.View.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.reforcointeligente.brainstormapp.R;

public class TeacherFragment extends Fragment{

    private ListView listTeacher;

    public TeacherFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.teacher_fragment, container, false);

        listTeacher = (ListView) rootView.findViewById(R.id.list_teacher);

        Button newStudentButton = (Button) rootView.findViewById(R.id.list_new_teacher);


        return rootView;
    }

    public static StudentFragment newInstance() {
        return new StudentFragment();
    }
}
