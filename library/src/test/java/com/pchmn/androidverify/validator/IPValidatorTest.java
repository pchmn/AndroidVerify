package com.pchmn.androidverify.validator;


import com.pchmn.androidverify.R;

import org.robolectric.RuntimeEnvironment;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IPValidatorTest extends ValidatorTest {

    @Override
    public void setUp() {
        mContext = RuntimeEnvironment.application.getApplicationContext();
        mValidator = new IPValidator();
    }

    @Override
    public void validate() {
        assertTrue(mValidator.isValid("91.198.174.225"));
        assertFalse(mValidator.isValid(""));
        assertFalse(mValidator.isValid("91.198.174"));
        assertFalse(mValidator.isValid(".198.174.225"));
    }

    @Override
    public void message() {
        assertEquals(mValidator.getErrorMessage(), mContext.getString(R.string.error_invalid_ip));
        mValidator.setErrorMessage("IP not valid");
        assertEquals(mValidator.getErrorMessage(), "IP not valid");
    }
}
