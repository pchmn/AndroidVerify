package com.pchmn.androidverify.validator;


import com.pchmn.androidverify.App;
import com.pchmn.androidverify.R;

public class MaxLengthValidator extends AbstractValidator {

    private int mLength;

    public MaxLengthValidator(int length) {
        if(length < 0)
            throw new IllegalArgumentException("You put a negative max length (" + length +")");
        if(length == 0)
            throw new IllegalArgumentException("Max length cannot be equal to zero");
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
