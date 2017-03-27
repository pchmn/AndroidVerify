package com.pchmn.formvalidator.validators;


import android.util.Patterns;

import com.pchmn.formvalidator.App;
import com.pchmn.formvalidator.R;

public class PhoneNumberValidator extends AbstractValidator {

    public PhoneNumberValidator() {
        mErrorMessage = App.getContext().getString(R.string.error_invalid_phone_number);
    }

    @Override
    public boolean isValid(String value) {
        return Patterns.PHONE.matcher(value).matches();
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
