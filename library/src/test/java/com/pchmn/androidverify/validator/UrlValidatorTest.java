package com.pchmn.androidverify.validator;


import com.pchmn.androidverify.R;

import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UrlValidatorTest extends ValidatorTest {

    @Override
    public void setUp() {
        mContext = RuntimeEnvironment.application.getApplicationContext();
        mValidator = new UrlValidator();
    }

    @Override
    public void validate() {
        assertTrue(mValidator.isValid("www.google.com"));
        assertTrue(mValidator.isValid("google.com"));
        assertTrue(mValidator.isValid("http://www.wp.pl/"));
        assertTrue(mValidator.isValid("http://www.google.com/page/1"));
        assertTrue(mValidator.isValid("https://google.com"));
        assertFalse(mValidator.isValid("htp://google.com"));
        assertFalse(mValidator.isValid("http://google"));
    }

    @Override
    public void message() {
        assertEquals(mValidator.getErrorMessage(), mContext.getString(R.string.error_invalid_url));
        mValidator.setErrorMessage("url");
        assertEquals(mValidator.getErrorMessage(), "url");
    }
}
