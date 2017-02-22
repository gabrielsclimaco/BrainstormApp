package com.reforcointeligente.brainstormapp.View.Forms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.reforcointeligente.brainstormapp.Controller.BrPhoneNumberFormatter;
import com.reforcointeligente.brainstormapp.Controller.FirebaseUtils;
import com.reforcointeligente.brainstormapp.Model.Student;
import com.reforcointeligente.brainstormapp.R;

import com.google.firebase.database.DatabaseReference;

import java.lang.ref.WeakReference;

public class StudentFormActivity extends AppCompatActivity {
    EditText parentPhone;
    EditText parentCellphone;
    Student student;
    Student oldStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        parentPhone = (EditText) findViewById(R.id.editTextStudentParentPhone);
        parentCellphone = (EditText) findViewById(R.id.editTextStudentParentCellphone);

        setUpSpinner();

        maskPhoneNumbers();

        listenButtonClickEvents();

        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("student");
        oldStudent = (Student) intent.getSerializableExtra("student");

        if(student != null) {
            FirebaseUtils.fillWithStudentInfo(student, this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void maskPhoneNumbers() {
        BrPhoneNumberFormatter addLineNumberFormatterPhone = new BrPhoneNumberFormatter(
                new WeakReference<>(parentPhone));
        parentPhone.addTextChangedListener(addLineNumberFormatterPhone);

        BrPhoneNumberFormatter addLineNumberFormatterCellphone = new BrPhoneNumberFormatter(
                new WeakReference<>(parentCellphone));
        parentCellphone.addTextChangedListener(addLineNumberFormatterCellphone);
    }

    private void listenButtonClickEvents() {
        Button cancelStudentButton = (Button) findViewById(R.id.buttonCancelStudent);
        cancelStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button confirmStudentButton = (Button) findViewById(R.id.buttonConfirmStudent);
        confirmStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStudentCreated();
            }
        });
    }

    private void setUpSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinnerStudentCity);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.list_df_cities, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
    }

    public void onStudentCreated() {
        if (isStudentFormValid()) {
            View view = findViewById(R.id.activity_student_form);

            if (student == null) {
                FirebaseUtils.saveStudent(view);
            } else {
                FirebaseUtils.updateStudent(student, view);
            }

            finish();
        }

    }

    private boolean isStudentFormValid() {
        boolean isValid = false;

        if (validateName()
                && validatePhoneNumber()
                && validateCellphoneNumber()
                && validateEmail()) {
            isValid = true;
        }

        return isValid;
    }

    private boolean validateEmail() {
        boolean isEmailValid = true;
        EditText emailField = (EditText) findViewById(R.id.editTextStudentParentEmail);

        if (!emailField.getText().toString().equals("")
                && !emailField.getText().toString().contains("@")) {
            emailField.setError("O email inserido é invalido");
            isEmailValid = false;
        }

        return isEmailValid;
    }

    private boolean validatePhoneNumber() {
        boolean isNumberValid = true;

        boolean lessThen14 = parentPhone.getText().toString().length() > 0 && parentPhone.getText().toString().length() < 14;

        if (lessThen14) {
            parentPhone.setError("O número inserido é inválido");
            isNumberValid = false;
        }

        return isNumberValid;
    }

    private boolean validateCellphoneNumber() {
        boolean isNumberValid = true;

        boolean lessThen14 = parentCellphone.getText().toString().length() > 0 && parentCellphone.getText().toString().length() < 14;

        if (lessThen14) {
            parentCellphone.setError("O número inserido é inválido");
            isNumberValid = false;
        }

        return isNumberValid;
    }

    private boolean validateName() {
        boolean isNameValid = true;
        EditText nameField = ((EditText) findViewById(R.id.editTextStudentName));

        if (nameField.getText().toString().equals("")) {
            nameField.setError("Você não pode adicionar um aluno sem nome");
            isNameValid = false;
        }

        return isNameValid;
    }
}
