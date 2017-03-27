package com.pchmn.formvalidator.validators;


import com.pchmn.formvalidator.App;
import com.pchmn.formvalidator.R;

public class MaxValueValidator extends AbstractValidator {

    private int mMaxValue;

    public MaxValueValidator(int value) {
        mMaxValue = value;
        mErrorMessage = App.getContext().getString(R.string.error_invalid_number);
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            mErrorMessage = App.getContext().getString(R.string.error_max_value, mMaxValue);
            return d <= mMaxValue;
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
