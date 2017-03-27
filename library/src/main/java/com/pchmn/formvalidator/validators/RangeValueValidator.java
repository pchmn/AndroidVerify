package com.pchmn.formvalidator.validators;


import com.pchmn.formvalidator.App;
import com.pchmn.formvalidator.R;

public class RangeValueValidator extends AbstractValidator {

    private int mMinValue, mMaxValue;

    public RangeValueValidator(int minValue, int maxValue) {
        mMinValue = minValue;
        mMaxValue = maxValue;
        mErrorMessage = App.getContext().getString(R.string.error_invalid_number);
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            mErrorMessage = App.getContext().getString(R.string.error_range_value, mMinValue, mMaxValue);
            return d >= mMinValue && d <= mMaxValue;
        }
        catch(NumberFormatException nfe) {
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
