package com.pchmn.androidverify.validator;


import com.pchmn.androidverify.App;
import com.pchmn.androidverify.R;

public class MinLengthValidator extends AbstractValidator {

    private int mLength;

    public MinLengthValidator(int length) {
        if(length < 0)
            throw new IllegalArgumentException("You put a negative min length (" + length +")");
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
