package com.pchmn.androidverify.validator;


import com.pchmn.androidverify.App;
import com.pchmn.androidverify.R;

public class NumericValidator extends AbstractValidator {

    public NumericValidator() {
        mErrorMessage = App.getContext().getString(R.string.error_invalid_number);
    }

    @Override
    public boolean isValid(String value) {
        return isNumeric(value);
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }

    protected boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
