package com.reforcointeligente.brainstormapp.View.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.reforcointeligente.brainstormapp.Model.Student;
import com.reforcointeligente.brainstormapp.R;
import com.reforcointeligente.brainstormapp.View.Forms.StudentFormActivity;
import com.reforcointeligente.brainstormapp.View.SelectedObject.SelectedStudentActivity;

public class StudentFragment extends Fragment {
    private ListView listStudent;

    public StudentFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.student_fragment, container, false);
        setHasOptionsMenu(true);

        listStudent = (ListView) rootView.findViewById(R.id.list_student);

        listStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Student student = (Student) listStudent.getItemAtPosition(position);

                Intent goToSelectedStudent = new Intent(getContext(), SelectedStudentActivity.class);
                goToSelectedStudent.putExtra("student", student);
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

        // loading list of students
        listStudent.setAdapter(FirebaseUtils.loadStudents(getActivity()));
    }

    public static StudentFragment newInstance() {
        return new StudentFragment();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_student_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_order_student_by_creation:
                listStudent.setAdapter(FirebaseUtils.loadStudents(getActivity()));
                break;
            case R.id.item_order_student_by_name:
                listStudent.setAdapter(FirebaseUtils.orderListOfStudentsByName(getActivity()));
                break;
            case R.id.item_order_student_by_parent:
                listStudent.setAdapter(FirebaseUtils.orderListOfStudentsByParent(getActivity()));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        String studentName = ((Student) listStudent.getItemAtPosition(((
                AdapterView.AdapterContextMenuInfo) menuInfo).position)).getStudentName();
        menu.setHeaderTitle(studentName);
        menuInflater.inflate(R.menu.selected_item_student, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.edit_item:
                Student student = (Student) listStudent.getItemAtPosition(info.position);
                Intent goToEditStudent = new Intent(getContext(), StudentFormActivity.class);
                goToEditStudent.putExtra("student", student);
                startActivity(goToEditStudent);
                break;
            case R.id.exclude_item:
                FirebaseUtils.excludeStudent((Student) listStudent.getItemAtPosition(info.position));
                break;
        }

        return super.onContextItemSelected(item);
    }
}
