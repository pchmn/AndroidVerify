package com.pchmn.androidverify.validator;


import com.pchmn.androidverify.App;
import com.pchmn.androidverify.R;

public abstract class AbstractValidator {

    String mErrorMessage = App.getContext().getString(R.string.error_invalid_field);

    public abstract boolean isValid(String value);

    public abstract String getErrorMessage();

    public void setErrorMessage(String message) {
        mErrorMessage = message;
    }
}
