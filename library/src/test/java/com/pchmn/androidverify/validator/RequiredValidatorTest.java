package com.pchmn.androidverify.validator;


import com.pchmn.androidverify.R;

import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RequiredValidatorTest extends ValidatorTest {

    private RequiredValidator mRequiredValidator;
    private RequiredValidator mNotRequiredValidator;

    @Override
    public void setUp() {
        mContext = RuntimeEnvironment.application.getApplicationContext();
        mRequiredValidator = new RequiredValidator(true);
        mNotRequiredValidator = new RequiredValidator(false);
    }

    @Override
    public void validate() {
        // required
        assertTrue(mRequiredValidator.isValid("abcd"));
        assertTrue(mRequiredValidator.isValid("123"));
        assertFalse(mRequiredValidator.isValid(""));
        // not required
        assertTrue(mNotRequiredValidator.isValid(""));
        assertTrue(mNotRequiredValidator.isValid("abcd"));
        assertTrue(mNotRequiredValidator.isValid("123"));
    }

    @Override
    public void message() {
        assertEquals(mRequiredValidator.getErrorMessage(), mContext.getString(R.string.error_field_required));
        mRequiredValidator.setErrorMessage("required");
        assertEquals(mRequiredValidator.getErrorMessage(), "required");
    }
}
