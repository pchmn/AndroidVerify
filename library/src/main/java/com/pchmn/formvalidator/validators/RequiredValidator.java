package com.pchmn.formvalidator.validators;


import android.text.TextUtils;

import com.pchmn.formvalidator.App;
import com.pchmn.formvalidator.R;

public class RequiredValidator extends AbstractValidator {

    private boolean mRequired;

    public RequiredValidator(boolean required) {
        mRequired = required;
        mErrorMessage = App.getContext().getString(R.string.error_field_required);
    }

    @Override
    public boolean isValid(String value) {
        if(mRequired)
            return !TextUtils.isEmpty(value);
        return true;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String message) {
        mRequired = true;
        mErrorMessage = message;
    }
}
