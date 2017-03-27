package com.pchmn.formvalidator.validators;


import com.pchmn.formvalidator.App;
import com.pchmn.formvalidator.R;

public class MinValueValidator extends AbstractValidator {

    private int mMinValue;

    public MinValueValidator(int value) {
        mMinValue = value;
        mErrorMessage = App.getContext().getString(R.string.error_invalid_number);
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            mErrorMessage = App.getContext().getString(R.string.error_min_value, mMinValue);
            return d >= mMinValue;
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
