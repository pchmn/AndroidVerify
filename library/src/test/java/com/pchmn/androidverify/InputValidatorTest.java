package com.pchmn.androidverify;


import android.content.Context;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest="./src/main/AndroidManifest.xml")
public class InputValidatorTest {

    private Context mContext;
    private EditText mEditText;

    @Before
    public void setUp() {
        mContext = RuntimeEnvironment.application.getApplicationContext();
        mEditText = new EditText(mContext);
    }

    @Test
    public void required() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .required(true)
                .build();

        assertFalse(inputValidator.isValid());
        mEditText.setText("test");
        assertTrue(inputValidator.isValid());
    }

    @Test
    public void isEmail() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .validatorType(InputValidator.IS_EMAIL)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("test@gmail.com");
        assertTrue(inputValidator.isValid());
        mEditText.setText("test@gmailcom");
        assertFalse(inputValidator.isValid());
    }

    @Test
    public void isPhoneNumber() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .validatorType(InputValidator.IS_PHONE_NUMBER)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("+331234567");
        assertTrue(inputValidator.isValid());
        mEditText.setText("123");
        assertFalse(inputValidator.isValid());
    }

    @Test
    public void isNumeric() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .validatorType(InputValidator.IS_NUMERIC)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("1562315");
        assertTrue(inputValidator.isValid());
        mEditText.setText("abc");
        assertFalse(inputValidator.isValid());
    }

    @Test
    public void minLength() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .minLength(3)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("abc");
        assertTrue(inputValidator.isValid());
        mEditText.setText("abcd");
        assertTrue(inputValidator.isValid());
        mEditText.setText("ab");
        assertFalse(inputValidator.isValid());

        // negative min length
        try {
            inputValidator = new InputValidator.Builder(mContext)
                    .minLength(-3)
                    .build();
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }
    }

    @Test
    public void maxLength() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .maxLength(3)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("abc");
        assertTrue(inputValidator.isValid());
        mEditText.setText("ab");
        assertTrue(inputValidator.isValid());
        mEditText.setText("abcd");
        assertFalse(inputValidator.isValid());

        // negative min length
        try {
            inputValidator = new InputValidator.Builder(mContext)
                    .maxLength(-3)
                    .build();
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }
    }

    @Test
    public void rangeLength() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .minLength(2)
                .maxLength(5)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("ab");
        assertTrue(inputValidator.isValid());
        mEditText.setText("abcde");
        assertTrue(inputValidator.isValid());
        mEditText.setText("abc");
        assertTrue(inputValidator.isValid());
        mEditText.setText("abcdef");
        assertFalse(inputValidator.isValid());
        mEditText.setText("a");
        assertFalse(inputValidator.isValid());

        // min length > max length
        try {
            inputValidator = new InputValidator.Builder(mContext)
                    .minLength(3)
                    .maxLength(1)
                    .build();
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }
    }

    @Test
    public void minValue() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .minValue(3)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("3");
        assertTrue(inputValidator.isValid());
        mEditText.setText("5");
        assertTrue(inputValidator.isValid());
        mEditText.setText("ab");
        assertFalse(inputValidator.isValid());
        mEditText.setText("2");
        assertFalse(inputValidator.isValid());
    }

    @Test
    public void maxValue() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .maxValue(10)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("10");
        assertTrue(inputValidator.isValid());
        mEditText.setText("7");
        assertTrue(inputValidator.isValid());
        mEditText.setText("ab");
        assertFalse(inputValidator.isValid());
        mEditText.setText("11");
        assertFalse(inputValidator.isValid());
    }

    @Test
    public void rangevalue() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .minValue(5)
                .maxValue(10)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("10");
        assertTrue(inputValidator.isValid());
        mEditText.setText("5");
        assertTrue(inputValidator.isValid());
        mEditText.setText("7");
        assertTrue(inputValidator.isValid());
        mEditText.setText("ab");
        assertFalse(inputValidator.isValid());
        mEditText.setText("11");
        assertFalse(inputValidator.isValid());
        mEditText.setText("4");
        assertFalse(inputValidator.isValid());

        // min value > max value
        try {
            inputValidator = new InputValidator.Builder(mContext)
                    .minValue(10)
                    .maxValue(5)
                    .build();
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }
    }

    @Test
    public void regex() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .regex("^[0-9]{4}$")
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("1234");
        assertTrue(inputValidator.isValid());
        mEditText.setText("0000");
        assertTrue(inputValidator.isValid());
        mEditText.setText("abcd");
        assertFalse(inputValidator.isValid());
        mEditText.setText("123");
        assertFalse(inputValidator.isValid());
    }

    @Test
    public void identicalAs() {
        EditText otherEditText = new EditText(mContext);
        otherEditText.setText("salut les amis");

        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .identicalAs(otherEditText)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("salut");
        assertFalse(inputValidator.isValid());
        mEditText.setText("salut les amis");
        assertTrue(inputValidator.isValid());
    }

    @Test
    public void errorMessage() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .minLength(5)
                .errorMessage("Min length 5")
                .build();

        mEditText.setText("1234");
        assertFalse(inputValidator.isValid());
        assertEquals(mEditText.getError().toString(), "Min length 5");
    }

    @Test
    public void requiredMessage() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .requiredMessage("required")
                .build();

        assertFalse(inputValidator.isValid());
        assertEquals(mEditText.getError().toString(), "required");
    }
}
