package com.pchmn.formvalidator.validators;


import com.pchmn.formvalidator.App;
import com.pchmn.formvalidator.R;

public class RangeLengthValidator extends AbstractValidator {

    private int mMaxLength, mMinLength;

    public RangeLengthValidator(int minLength, int maxLength) {
        mMaxLength = maxLength;
        mMinLength = minLength;
        mErrorMessage = App.getContext().getString(R.string.error_range_length, mMinLength, mMaxLength);
    }

    @Override
    public boolean isValid(String value) {
        return value.length() >= mMinLength && value.length() <= mMaxLength;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
