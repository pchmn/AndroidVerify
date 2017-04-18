package com.pchmn.androidverify;


import android.content.Context;
import android.text.InputType;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest="./src/main/AndroidManifest.xml")
public class FormTest {

    private Context mContext;
    private Form mForm;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    @Before
    public void setUp() {
        mContext = RuntimeEnvironment.application.getApplicationContext();
        mEmailEditText = new EditText(mContext);
        mEmailEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        mPasswordEditText = new EditText(mContext);
    }

    @Test
    public void validateEmptyForm() {
        mForm = new Form(mContext);
        assertTrue(mForm.isValid());
    }

    @Test
    public void validateJava() {
        // form
        mForm = new Form(mContext);

        // validators
        InputValidator emailInputValidator = new InputValidator(mContext);
        emailInputValidator.setRequired(true);
        emailInputValidator.setEditText(mEmailEditText);
        mForm.addInputValidator(emailInputValidator);

        InputValidator passwordInputValidator = new InputValidator(mContext);
        passwordInputValidator.setEditText(mPasswordEditText);
        passwordInputValidator.setMinLength(6);
        mForm.addInputValidator(passwordInputValidator);

        // validate form
        validate();
    }

    @Test
    public void validateBuilder() {
        // form
        mForm = new Form.Builder(mContext)
                .build();

        // validators
        InputValidator emailValidator = new InputValidator.Builder(mContext)
                .on(mEmailEditText)
                .required(true)
                .build();
        mForm.addInputValidator(emailValidator);

        InputValidator passwordValidator = new InputValidator.Builder(mContext)
                .on(mPasswordEditText)
                .minLength(6)
                .build();
        mForm.addInputValidator(passwordValidator);

        // validate form
        validate();
    }

    public void validate() {
        // reset
        mEmailEditText.setText("");
        mPasswordEditText.setText("");

        // not valid
        assertFalse(mForm.isValid());
        mEmailEditText.setText("ab");
        assertFalse(mForm.isValid());
        mEmailEditText.setText("test@gmail.com");
        mPasswordEditText.setText("12345");
        assertFalse(mForm.isValid());

        // valid
        mEmailEditText.setText("test@gmail.com");
        mPasswordEditText.setText("123456");
        assertTrue(mForm.isValid());
        mPasswordEditText.setText("");
        assertTrue(mForm.isValid());
    }

    @Test
    public void showErrorsJava() {
        // form
        mForm = new Form(mContext);

        // validator
        InputValidator emailInputValidator = new InputValidator(mContext);
        emailInputValidator.setRequired(true);
        emailInputValidator.setEditText(mEmailEditText);
        mForm.addInputValidator(emailInputValidator);

        // show errors
        showErrors();
    }

    @Test
    public void showErrorsBuilder() {
        // form
        mForm = new Form.Builder(mContext)
                .build();

        // validator
        InputValidator emailValidator = new InputValidator.Builder(mContext)
                .on(mEmailEditText)
                .required(true)
                .build();
        mForm.addInputValidator(emailValidator);

        // show errors
        showErrors();
    }

    public void showErrors() {
        // reset error
        mEmailEditText.setError(null);
        // show error
        mEmailEditText.setText("abc");
        assertFalse(mForm.isValid());
        assertTrue(mEmailEditText.getError() != null);

        // reset error
        mEmailEditText.setError(null);
        // do not show error
        mForm.setShowErrors(false);
        assertFalse(mForm.isValid());
        assertTrue(mEmailEditText.getError() == null);
    }
}
