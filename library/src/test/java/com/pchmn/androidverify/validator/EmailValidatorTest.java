package com.pchmn.androidverify.validator;


import com.pchmn.androidverify.R;

import org.robolectric.RuntimeEnvironment;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class EmailValidatorTest extends ValidatorTest {

    @Override
    public void setUp() {
        mContext = RuntimeEnvironment.application.getApplicationContext();
        mValidator = new EmailValidator();
    }

    @Override
    public void validate() {
        assertTrue(mValidator.isValid("test@gmail.com"));
        assertFalse(mValidator.isValid(""));
        assertFalse(mValidator.isValid("test"));
        assertFalse(mValidator.isValid("test@"));
        assertFalse(mValidator.isValid("@gmail.com"));
    }

    @Override
    public void message() {
        assertEquals(mValidator.getErrorMessage(), mContext.getString(R.string.error_invalid_email));
        mValidator.setErrorMessage("Enter a valid email");
        assertEquals(mValidator.getErrorMessage(), "Enter a valid email");
    }
}
