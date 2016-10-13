package com.reforcointeligente.brainstormapp.View.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.reforcointeligente.brainstormapp.R;
import com.reforcointeligente.brainstormapp.View.LessonFormActivity;

public class LessonFragment extends Fragment{

    private ListView listLesson;

    public LessonFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lesson_fragment, container, false);

        listLesson = (ListView) rootView.findViewById(R.id.list_lesson);

        Button newLessonButton = (Button) rootView.findViewById(R.id.list_new_lesson);
        newLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lessonFormActivity = new Intent(getActivity(), LessonFormActivity.class);
                getActivity().startActivity(lessonFormActivity);
            }
        });

        return rootView;
    }

    public static LessonFragment newInstance() {
        return new LessonFragment();
    }
}
