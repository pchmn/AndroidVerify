package com.pchmn.androidverify.validator;


import com.pchmn.androidverify.R;

import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NumericValidatorTest extends ValidatorTest {

    @Override
    public void setUp() {
        mContext = RuntimeEnvironment.application.getApplicationContext();
        mValidator = new NumericValidator();
    }

    @Override
    public void validate() {
        assertTrue(mValidator.isValid("1"));
        assertTrue(mValidator.isValid("-1"));
        assertTrue(mValidator.isValid("123"));
        assertTrue(mValidator.isValid("1.2"));
        assertFalse(mValidator.isValid(""));
        assertFalse(mValidator.isValid("abc"));
        assertFalse(mValidator.isValid("12a"));
    }

    @Override
    public void message() {
        assertEquals(mValidator.getErrorMessage(), mContext.getString(R.string.error_invalid_number));
        mValidator.setErrorMessage("numeric");
        assertEquals(mValidator.getErrorMessage(), "numeric");
    }
}
