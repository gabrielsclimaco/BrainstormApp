package com.reforcointeligente.brainstormapp.View.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.reforcointeligente.brainstormapp.R;

public class LessonFragment extends Fragment{

    private ListView listLesson;

    public LessonFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lesson_fragment, container, false);

        listLesson = (ListView) rootView.findViewById(R.id.list_lesson);

        Button newLessonButton = (Button) rootView.findViewById(R.id.list_new_lesson);


        return rootView;
    }

    public static LessonFragment newInstance() {
        return new LessonFragment();
    }
}
