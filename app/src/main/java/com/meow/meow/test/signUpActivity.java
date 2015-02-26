package com.meow.meow.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class signUpActivity extends Activity {

    private int gender = 3;                 //0-female, 1-male, 2-other, 3-unselected
    private RadioGroup genderRadioGroup;
    private RadioButton femaleRadioButton, maleRadioButton, otherRadioButton;
    private Spinner mailboxSpinner;
    private ArrayAdapter adapter;
    private EditText username, mailSubject, password, confirmPassword;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Radio Group & Button
        genderRadioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);
        femaleRadioButton = (RadioButton) findViewById(R.id.femaleRadioButton);
        maleRadioButton = (RadioButton) findViewById(R.id.maleRadioButton);
        otherRadioButton = (RadioButton) findViewById(R.id.otherRadioButton);

        //EditText
        username = (EditText) findViewById(R.id.usernameSignUp);
        mailSubject = (EditText) findViewById(R.id.mailSubjectSignUp);
        password = (EditText) findViewById(R.id.passwordSignUp);
        confirmPassword = (EditText) findViewById(R.id.confirmPasswordSignUp);

        //Button
        submit = (Button) findViewById(R.id.submitSignUp);

        //Spinner
        mailboxSpinner = (Spinner) findViewById(R.id.mailboxSignUp);
        adapter = ArrayAdapter.createFromResource(this, R.array.valid_mailbox_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mailboxSpinner.setAdapter(adapter);
        OnItemSelectedListener ois = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TODO: switch mailbox
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };
        mailboxSpinner.setOnItemSelectedListener(ois);

        //RadioGroup
        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkId) {
                //TODO: switch gender
                switch (checkId) {
                    case R.id.femaleRadioButton:
                        gender = 0;
                        break;

                    case R.id.maleRadioButton:
                        gender = 1;
                        break;

                    case R.id.otherRadioButton:
                        gender = 2;
                        break;
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSginUpInfoLocal()) {
                    //TODO: check sign up info online
                }
            }
        });
    }

    private boolean checkSginUpInfoLocal() {
        boolean isCorrect = true;
        int usernameMinLength = 4;
        int passwordMinLength = 6;

        if (username.getText().length() == 0) {
            Toast.makeText(signUpActivity.this, "Please enter your username.", Toast.LENGTH_LONG).show();
            isCorrect = false;
        }
        else if (username.getText().length() < usernameMinLength) {
            Toast.makeText(signUpActivity.this, "Username should be " + String.valueOf(usernameMinLength) + " characters at least", Toast.LENGTH_LONG).show();
            isCorrect = false;
        }

        if (gender == 3) {
            Toast.makeText(signUpActivity.this, "Please select a gender", Toast.LENGTH_LONG).show();
            isCorrect = false;
        }

        if (mailSubject.getText().length() == 0) {
            Toast.makeText(signUpActivity.this, "Please enter your email address.", Toast.LENGTH_LONG).show();
            isCorrect = false;
        }

        if (password.getText().length() == 0) {
            Toast.makeText(signUpActivity.this, "Please enter your password.", Toast.LENGTH_LONG).show();
            isCorrect = false;
        }
        else if (password.getText().length() < passwordMinLength) {
            Toast.makeText(signUpActivity.this, "Password should be " + String.valueOf(usernameMinLength) + " digits at least", Toast.LENGTH_LONG).show();
            isCorrect = false;
        }
        else if (!password.getText().equals(confirmPassword.getText())) {
            Toast.makeText(signUpActivity.this, "Two passwords do not match.", Toast.LENGTH_LONG).show();
            isCorrect = false;
        }

        return isCorrect;
    }

}
