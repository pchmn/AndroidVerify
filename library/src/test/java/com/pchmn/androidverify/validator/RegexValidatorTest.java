package com.pchmn.androidverify.validator;


import com.pchmn.androidverify.R;

import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegexValidatorTest extends ValidatorTest {

    @Override
    public void setUp() {
        mContext = RuntimeEnvironment.application.getApplicationContext();
        mValidator = new RegexValidator("^[0-9]{4}$");
    }

    @Override
    public void validate() {
        assertTrue(mValidator.isValid("1234"));
        assertFalse(mValidator.isValid("123"));
        assertFalse(mValidator.isValid("12345"));
        assertFalse(mValidator.isValid(""));
        assertFalse(mValidator.isValid("abcd"));
    }

    @Override
    public void message() {
        assertEquals(mValidator.getErrorMessage(), mContext.getString(R.string.error_invalid_field));
        mValidator.setErrorMessage("regex");
        assertEquals(mValidator.getErrorMessage(), "regex");
    }
}
