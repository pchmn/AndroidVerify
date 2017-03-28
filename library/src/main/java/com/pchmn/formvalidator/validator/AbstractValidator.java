package com.pchmn.formvalidator.validator;


import com.pchmn.formvalidator.App;
import com.pchmn.formvalidator.R;

public abstract class AbstractValidator {

    String mErrorMessage = App.getContext().getString(R.string.error_invalid_field);

    public abstract boolean isValid(String value);

    public abstract String getErrorMessage();

    public void setErrorMessage(String message) {
        mErrorMessage = message;
    }
}
