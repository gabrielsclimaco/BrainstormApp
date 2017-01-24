package com.reforcointeligente.brainstormapp.View.Forms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.reforcointeligente.brainstormapp.Controller.BrPhoneNumberFormatter;
import com.reforcointeligente.brainstormapp.Controller.FirebaseUtils;
import com.reforcointeligente.brainstormapp.Controller.MoneyTextWatcher;
import com.reforcointeligente.brainstormapp.Model.Teacher;
import com.reforcointeligente.brainstormapp.R;

import java.lang.ref.WeakReference;

public class TeacherFormActivity extends AppCompatActivity {
    Spinner spinner;
    Button confirmTeacherButton;
    Button cancelTeacherButton;
    EditText teacherPhone;
    EditText teacherCellphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form);

        teacherPhone = (EditText) findViewById(R.id.editTextTeacherPhone);
        teacherCellphone = (EditText) findViewById(R.id.editTextTeacherCellphone);

        setUpSpinner();

        maskPhoneNumbers();
        maskMoneyFields();

        listenButtonClickEvents();

        ((RadioButton) findViewById(R.id.radioButtonNo)).setChecked(true);
    }

    private void maskMoneyFields() {
        EditText teacherPricePerHour =(EditText) findViewById(R.id.editTextTeacherPricePerHour);
        MoneyTextWatcher moneyTextWatcherPricePerHour = new MoneyTextWatcher(teacherPricePerHour);
        teacherPricePerHour.addTextChangedListener(moneyTextWatcherPricePerHour);
    }

    private void maskPhoneNumbers() {
        BrPhoneNumberFormatter addLineNumberFormatterPhone = new BrPhoneNumberFormatter(
                new WeakReference<>(teacherPhone));
        teacherPhone.addTextChangedListener(addLineNumberFormatterPhone);

        BrPhoneNumberFormatter addLineNumberFormatterCellphone = new BrPhoneNumberFormatter(
                new WeakReference<>(teacherCellphone));
        teacherCellphone.addTextChangedListener(addLineNumberFormatterCellphone);
    }

    private void listenButtonClickEvents() {
        cancelTeacherButton = (Button) findViewById(R.id.buttonCancelTeacher);
        cancelTeacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        confirmTeacherButton = (Button) findViewById(R.id.buttonConfirmTeacher);
        confirmTeacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTeacherCreated();
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

    public void onTeacherCreated() {
        if (isTeacherValid()) {
            View view = findViewById(R.id.activity_teacher_form);
            FirebaseUtils.saveTeacher(view);

            finish();
        }
    }

    private boolean isTeacherValid() {
        boolean isValid = false;

        if (isNameValid()
                && isPricePerHourValid()
                && validatePhoneNumber()
                && validateCellphoneNumber()) {
            isValid = true;
        }

        return isValid;
    }

    private boolean validateCellphoneNumber() {
        boolean isNumberValid = true;

        boolean lessThen14 = teacherCellphone.getText().toString().length() > 0 && teacherCellphone.getText().toString().length() < 14;

        if (lessThen14) {
            teacherCellphone.setError("O número inserido é inválido");
            isNumberValid = false;
        }

        return isNumberValid;
    }

    private boolean validatePhoneNumber() {
        boolean isNumberValid = true;

        boolean lessThen14 = teacherPhone.getText().toString().length() > 0 && teacherPhone.getText().toString().length() < 14;

        if (lessThen14) {
            teacherPhone.setError("O número inserido é inválido");
            isNumberValid = false;
        }

        return isNumberValid;
    }

    private boolean isPricePerHourValid() {
        boolean isValid = true;
        EditText pricePerHour = (EditText) findViewById(R.id.editTextTeacherPricePerHour);

        if (pricePerHour.getText().toString().isEmpty()) {
            pricePerHour.setError("Você precisa falar quanto o professor deve receber");
            isValid = false;
        }

        return isValid;
    }

    private boolean isNameValid() {
        boolean isValid = true;
        EditText nameField = ((EditText) findViewById(R.id.editTextTeacherName));

        if (nameField.getText().toString().equals("")) {
            nameField.setError("Você não pode adicionar um professor sem nome");
            isValid = false;
        }

        return isValid;
    }
}
