package com.pchmn.formvalidator.validators;


import com.pchmn.formvalidator.App;
import com.pchmn.formvalidator.R;

public class MinLengthValidator extends AbstractValidator {

    private int mLength;

    public MinLengthValidator(int length) {
        mLength = length;
        mErrorMessage = App.getContext().getString(R.string.error_min_length, mLength);
    }

    @Override
    public boolean isValid(String value) {
        return value.length() >= mLength;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
