package com.pchmn.androidverify.validator;


import android.util.Patterns;

import com.pchmn.androidverify.App;
import com.pchmn.androidverify.R;

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
