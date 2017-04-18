package com.pchmn.androidverify.validator;


import com.pchmn.androidverify.R;

import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PhoneNumberValidatorTest extends ValidatorTest {

    @Override
    public void setUp() {
        mContext = RuntimeEnvironment.application.getApplicationContext();
        mValidator = new PhoneNumberValidator();
    }

    @Override
    public void validate() {
        assertTrue(mValidator.isValid("0299123456"));
        assertTrue(mValidator.isValid("+33123456789"));
        assertTrue(mValidator.isValid("600456789"));
        assertTrue(mValidator.isValid("600456"));
        assertFalse(mValidator.isValid(""));
        assertFalse(mValidator.isValid("123"));
        assertFalse(mValidator.isValid("ab12"));
    }

    @Override
    public void message() {
        assertEquals(mValidator.getErrorMessage(), mContext.getString(R.string.error_invalid_phone_number));
        mValidator.setErrorMessage("phone number");
        assertEquals(mValidator.getErrorMessage(), "phone number");
    }
}
