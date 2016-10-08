package com.reforcointeligente.brainstormapp.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.reforcointeligente.brainstormapp.R;

public class StudentFragment extends Fragment {

    private ListView listStudent;

    public StudentFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.studment_fragnet, container, false);

        listStudent = (ListView) rootView.findViewById(R.id.list_student);

        Button newStudentButton = (Button) rootView.findViewById(R.id.list_new_student);


        return rootView;
    }

    public static StudentFragment newInstance() {
        return new StudentFragment();
    }
}
