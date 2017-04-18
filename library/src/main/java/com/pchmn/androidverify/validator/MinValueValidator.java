package com.pchmn.androidverify.validator;


import com.pchmn.androidverify.App;
import com.pchmn.androidverify.R;

public class MinValueValidator extends AbstractValidator {

    private int mMinValue;

    public MinValueValidator(int value) {
        mMinValue = value;
        mErrorMessage = App.getContext().getString(R.string.error_min_value, mMinValue);
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            mErrorMessage = App.getContext().getString(R.string.error_min_value, mMinValue);
            return d >= mMinValue;
        }
        catch(NumberFormatException nfe) {
            mErrorMessage = App.getContext().getString(R.string.error_invalid_number);
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
