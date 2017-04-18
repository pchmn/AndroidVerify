package com.pchmn.androidverify.validator;


import com.pchmn.androidverify.R;

import junit.framework.Assert;

import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValueValidatorTest extends ValidatorTest {

    private MinValueValidator mMinValueValidator;
    private MaxValueValidator mMaxValueValidator;
    private RangeValueValidator mRangeValueValidator;

    @Override
    public void setUp() {
        mContext = RuntimeEnvironment.application.getApplicationContext();
        mMinValueValidator = new MinValueValidator(5);
        mMaxValueValidator = new MaxValueValidator(10);
        mRangeValueValidator = new RangeValueValidator(0, 10);
    }

    @Override
    public void validate() {
        // min value
        assertTrue(mMinValueValidator.isValid("5"));
        assertTrue(mMinValueValidator.isValid("10"));
        assertFalse(mMinValueValidator.isValid("3"));
        assertFalse(mMinValueValidator.isValid(""));
        assertFalse(mMinValueValidator.isValid("-1"));
        assertFalse(mMinValueValidator.isValid("abc"));
        assertFalse(mMinValueValidator.isValid("12a"));
        // max value
        assertTrue(mMaxValueValidator.isValid("10"));
        assertTrue(mMaxValueValidator.isValid("8"));
        assertTrue(mMaxValueValidator.isValid("-1"));
        assertFalse(mMaxValueValidator.isValid(""));
        assertFalse(mMaxValueValidator.isValid("11"));
        assertFalse(mMaxValueValidator.isValid("abc"));
        assertFalse(mMaxValueValidator.isValid("12a"));
        // range value
        assertTrue(mRangeValueValidator.isValid("0"));
        assertTrue(mRangeValueValidator.isValid("10"));
        assertTrue(mRangeValueValidator.isValid("5"));
        assertFalse(mRangeValueValidator.isValid(""));
        assertFalse(mRangeValueValidator.isValid("-1"));
        assertFalse(mRangeValueValidator.isValid("11"));
        assertFalse(mRangeValueValidator.isValid("abc"));
        assertFalse(mRangeValueValidator.isValid("12a"));
    }

    @Test
    public void illegalArgumentRange() {
        // min value > max value
        try {
            RangeValueValidator rangeValueValidator = new RangeValueValidator(3, 1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }

        // min value > max value
        try {
            RangeValueValidator rangeValueValidator = new RangeValueValidator(-2, -10);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }
    }

    @Override
    public void message() {
        // min value
        assertEquals(mMinValueValidator.getErrorMessage(), mContext.getString(R.string.error_min_value, 5));
        mMinValueValidator.setErrorMessage("min");
        assertEquals(mMinValueValidator.getErrorMessage(), "min");
        // invalid number
        assertFalse(mMinValueValidator.isValid("abc"));
        Assert.assertEquals(mMinValueValidator.getErrorMessage(), mContext.getString(R.string.error_invalid_number));
        // max value
        assertEquals(mMaxValueValidator.getErrorMessage(), mContext.getString(R.string.error_max_value, 10));
        mMaxValueValidator.setErrorMessage("max");
        assertEquals(mMaxValueValidator.getErrorMessage(), "max");
        // invalid number
        assertFalse(mMaxValueValidator.isValid("abc"));
        Assert.assertEquals(mMaxValueValidator.getErrorMessage(), mContext.getString(R.string.error_invalid_number));
        // range value
        assertEquals(mRangeValueValidator.getErrorMessage(), mContext.getString(R.string.error_range_value, 0, 10));
        mRangeValueValidator.setErrorMessage("range");
        assertEquals(mRangeValueValidator.getErrorMessage(), "range");
        // invalid number
        assertFalse(mRangeValueValidator.isValid("abc"));
        Assert.assertEquals(mRangeValueValidator.getErrorMessage(), mContext.getString(R.string.error_invalid_number));
    }
}
