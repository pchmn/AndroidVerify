package com.pchmn.androidverify.validator;


import com.pchmn.androidverify.R;

import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LengthValidatorTest extends ValidatorTest {

    private MinLengthValidator mMinLengthValidator;
    private MaxLengthValidator mMaxLengthValidator;
    private RangeLengthValidator mRangeLengthValidator;

    @Override
    public void setUp() {
        mContext = RuntimeEnvironment.application.getApplicationContext();
        mMinLengthValidator = new MinLengthValidator(2);
        mMaxLengthValidator = new MaxLengthValidator(5);
        mRangeLengthValidator = new RangeLengthValidator(2, 5);
    }

    @Override
    public void validate() {
        // min length
        assertTrue(mMinLengthValidator.isValid("abc"));
        assertTrue(mMinLengthValidator.isValid("ab"));
        assertFalse(mMinLengthValidator.isValid(""));
        assertFalse(mMinLengthValidator.isValid("a"));
        // max length
        assertTrue(mMaxLengthValidator.isValid("abcde"));
        assertTrue(mMaxLengthValidator.isValid("abc"));
        assertTrue(mMaxLengthValidator.isValid(""));
        assertFalse(mMaxLengthValidator.isValid("abcdef"));
        // range length
        assertTrue(mRangeLengthValidator.isValid("ab"));
        assertTrue(mRangeLengthValidator.isValid("abcde"));
        assertTrue(mRangeLengthValidator.isValid("abc"));
        assertFalse(mRangeLengthValidator.isValid(""));
        assertFalse(mRangeLengthValidator.isValid("a"));
        assertFalse(mRangeLengthValidator.isValid("abcdef"));
    }

    @Test
    public void illegalArgumentMinLength() {
        // negative min length
        try {
            MinLengthValidator minLengthValidator = new MinLengthValidator(-1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }
    }

    @Test
    public void illegalArgumentMaxLength() {
        // negative max length
        try {
            MaxLengthValidator maxLengthValidator = new MaxLengthValidator(-1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }

        // max length = 0
        try {
            MaxLengthValidator maxLengthValidator = new MaxLengthValidator(0);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }
    }

    @Test
    public void illegalArgumentRangeLength() {
        // negative min length
        try {
            RangeLengthValidator rangeLengthValidator = new RangeLengthValidator(-1, 0);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }

        // negative min length and max length
        try {
            RangeLengthValidator rangeLengthValidator = new RangeLengthValidator(-1, -1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }

        // negative max length
        try {
            RangeLengthValidator rangeLengthValidator = new RangeLengthValidator(0, -1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }

        // min length > max length
        try {
            RangeLengthValidator rangeLengthValidator = new RangeLengthValidator(3, 1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }
    }

    @Override
    public void message() {
        // min length
        assertEquals(mMinLengthValidator.getErrorMessage(), mContext.getString(R.string.error_min_length, 2));
        mMinLengthValidator.setErrorMessage("Min length 2");
        assertEquals(mMinLengthValidator.getErrorMessage(), "Min length 2");
        // max length
        assertEquals(mMaxLengthValidator.getErrorMessage(), mContext.getString(R.string.error_max_length, 5));
        mMaxLengthValidator.setErrorMessage("Max length 5");
        assertEquals(mMaxLengthValidator.getErrorMessage(), "Max length 5");
        // range length
        assertEquals(mRangeLengthValidator.getErrorMessage(), mContext.getString(R.string.error_range_length, 2, 5));
        mRangeLengthValidator.setErrorMessage("Between 2  and 5 characters");
        assertEquals(mRangeLengthValidator.getErrorMessage(), "Between 2  and 5 characters");
    }
}
