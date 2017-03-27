package com.pchmn.formvalidator.validators;


import com.pchmn.formvalidator.App;
import com.pchmn.formvalidator.R;

public class MaxLengthValidator extends AbstractValidator {

    private int mLength;

    public MaxLengthValidator(int length) {
        mLength = length;
        mErrorMessage = App.getContext().getString(R.string.error_max_length, mLength);
    }

    @Override
    public boolean isValid(String value) {
        return value.length() <= mLength;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
