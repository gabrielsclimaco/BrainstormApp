package com.reforcointeligente.brainstormapp.View.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.reforcointeligente.brainstormapp.Controller.FirebaseUtils;
import com.reforcointeligente.brainstormapp.Model.Teacher;
import com.reforcointeligente.brainstormapp.R;
import com.reforcointeligente.brainstormapp.View.Forms.TeacherFormActivity;
import com.reforcointeligente.brainstormapp.View.SelectedObject.SelectedTeacherActivity;

public class TeacherFragment extends Fragment{

    private ListView listTeacher;

    public TeacherFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.teacher_fragment, container, false);
        setHasOptionsMenu(true);

        listTeacher = (ListView) rootView.findViewById(R.id.list_teacher);
//        registerForContextMenu(listTeacher);
        listTeacher.setOnCreateContextMenuListener(this);

        listTeacher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Teacher teacher = (Teacher) listTeacher.getItemAtPosition(position);

                Intent goToSelectedTeacher = new Intent(getContext(), SelectedTeacherActivity.class);
                goToSelectedTeacher.putExtra("teacher", teacher);
                startActivity(goToSelectedTeacher);
            }
        });

        Button newTeacherButton = (Button) rootView.findViewById(R.id.list_new_teacher);

        newTeacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TeacherFormActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        // loading list of teachers
        listTeacher.setAdapter(FirebaseUtils.loadTeachers(getActivity()));
    }

    public static TeacherFragment newInstance() {
        return new TeacherFragment();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_teacher_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_order_teacher_by_creation:
                listTeacher.setAdapter(FirebaseUtils.loadTeachers(getActivity()));
                break;
            case R.id.item_order_teacher_by_name:
                listTeacher.setAdapter(FirebaseUtils.orderListOfTeachersByName(getActivity()));
                break;
            case R.id.item_order_teacher_by_course:
                listTeacher.setAdapter(FirebaseUtils.orderListOfTeachersByCourse(getActivity()));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        String teacherName = ((Teacher) listTeacher.getItemAtPosition(((
                AdapterView.AdapterContextMenuInfo) menuInfo).position)).getTeacherName();
        menu.setHeaderTitle(teacherName);
        menuInflater.inflate(R.menu.selected_item_student, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.edit_item:
                Teacher teacher = (Teacher) listTeacher.getItemAtPosition(info.position);
                Intent goToEditTeacher = new Intent(getContext(), TeacherFormActivity.class);
                goToEditTeacher.putExtra("teacher", teacher);
                startActivity(goToEditTeacher);
                break;
            case R.id.exclude_item:
                FirebaseUtils.excludeTeacher((Teacher) listTeacher.getItemAtPosition(info.position));
                break;
        }

        return super.onContextItemSelected(item);
    }


}
