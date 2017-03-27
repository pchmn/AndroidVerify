package com.pchmn.formvalidator.validators;


import android.util.Patterns;

import com.pchmn.formvalidator.App;
import com.pchmn.formvalidator.R;

public class IPValidator extends AbstractValidator {

    public IPValidator() {
        mErrorMessage = App.getContext().getString(R.string.error_invalid_ip);
    }

    @Override
    public boolean isValid(String value) {
        return Patterns.IP_ADDRESS.matcher(value).matches();
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
