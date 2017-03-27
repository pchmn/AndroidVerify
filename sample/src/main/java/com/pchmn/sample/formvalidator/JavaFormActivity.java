package com.pchmn.sample.formvalidator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.pchmn.formvalidator.Form;
import com.pchmn.formvalidator.InputValidator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JavaFormActivity extends AppCompatActivity {

    @BindView(R.id.email) EditText mEmailEditText;
    @BindView(R.id.password) EditText mPasswordEditText;
    @BindView(R.id.confirm_password) EditText mConfirmPasswordEditText;
    private Form mForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_form);
        // better knife
        ButterKnife.bind(this);

        // create form validator
        InputValidator emailValidator = new InputValidator.Builder(this)
                .on(mEmailEditText)
                .validatorType(InputValidator.IS_EMAIL)
                .build();

        InputValidator passwordValidator = new InputValidator.Builder(this)
                .on(mPasswordEditText)
                .required(true)
                .minLength(6)
                .build();

        InputValidator confirmValidator = new InputValidator.Builder(this)
                .on(mConfirmPasswordEditText)
                .required(true)
                .identicalAs(R.id.password)
                .build();

        mForm = new Form.Builder(this)
                .addInputValidator(emailValidator)
                .addInputValidator(passwordValidator)
                .addInputValidator(confirmValidator)
                .build();
    }

    @OnClick(R.id.validate)
    public void validate() {
        Toast.makeText(this, mForm.isValid() ? "The form is valid": "The form is not valid", Toast.LENGTH_SHORT).show();
    }
}
