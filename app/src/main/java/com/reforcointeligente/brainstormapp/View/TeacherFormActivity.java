package com.reforcointeligente.brainstormapp.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.reforcointeligente.brainstormapp.Model.Teacher;
import com.reforcointeligente.brainstormapp.R;

public class TeacherFormActivity extends AppCompatActivity {
    Spinner spinner;
    boolean hasCar;
    Button confirmTeacherButton;
    Button cancelTeacherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form);
        setUpSpinner();

        cancelTeacherButton = (Button) findViewById(R.id.buttonCancelTeacher);
        cancelTeacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setUpSpinner() {
        spinner = (Spinner) findViewById(R.id.spinnerTeacherCity);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.list_df_cities, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioButtonYes:
                if (checked)
                    hasCar = true;
                    break;
            case R.id.radioButtonNo:
                if (checked)
                    hasCar = false;
                    break;
        }
    }

    public void onTeacherCreated() {
        Teacher teacher = new Teacher();

        EditText teacherNameEditText = (EditText) findViewById(R.id.editTextTeacherName);
        EditText teacherAddressEditText = (EditText) findViewById(R.id.editTextTeacherAddress);
        EditText teacherGraduationEditText = (EditText) findViewById(R.id.editTextTeacherGraduation);
        EditText teacherPhoneEditText = (EditText) findViewById(R.id.editTextTeacherPhone);
        EditText teacherCellphoneEditText = (EditText) findViewById(R.id.editTextTeacherCellphone);;
        EditText teacherEmailEditText = (EditText) findViewById(R.id.editTextTeacherEmail);
        EditText teacherPricePerHourEditText = (EditText) findViewById(R.id.editTextTeacherValuePerHour);


        String teacherName = teacherNameEditText.getText().toString();
        String teacherAddress = teacherAddressEditText.getText().toString();
        String teacherCity = spinner.getSelectedItem().toString();
        String teacherGraduation = teacherGraduationEditText.getText().toString();
        String teacherPhone = teacherPhoneEditText.getText().toString();
        String teacherCellphone = teacherCellphoneEditText.getText().toString();
        String teacherEmail = teacherEmailEditText.getText().toString();
        Double teacherPricePerHour = Double.valueOf(teacherPricePerHourEditText.getText().toString());


        teacher.setTeacherName(teacherName);
        teacher.setTeacherAddress(teacherAddress);
        teacher.setTeacherCity(teacherCity);
        teacher.setTeacherCourse(teacherGraduation);
        teacher.setTeacherPhone(teacherPhone);
        teacher.setTeacherCellphone(teacherCellphone);
        teacher.setTeacherEmail(teacherEmail);
        teacher.setTeacherPricePerHour(teacherPricePerHour);
        teacher.setTeacherCar(hasCar);

        //falta materias

    }
}
