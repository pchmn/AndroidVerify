package com.pchmn.androidverify.validator;


import android.widget.EditText;

import com.pchmn.androidverify.R;

import org.robolectric.RuntimeEnvironment;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class IdenticalValidatorTest extends ValidatorTest {

    @Override
    public void setUp() {
        mContext = RuntimeEnvironment.application.getApplicationContext();
        EditText editText = new EditText(mContext);
        editText.setText("test identical");
        mValidator = new IdenticalValidator(editText);
    }

    @Override
    public void validate() {
        assertFalse(mValidator.isValid("test"));
        assertFalse(mValidator.isValid(""));
        assertTrue(mValidator.isValid("test identical"));
    }

    @Override
    public void message() {
        assertEquals(mValidator.getErrorMessage(), mContext.getString(R.string.error_fields_mismatch));
        mValidator.setErrorMessage("Fields mismatch");
        assertEquals(mValidator.getErrorMessage(), "Fields mismatch");
    }
}
