package com.pchmn.androidverify.validator;

import android.widget.EditText;

import com.pchmn.androidverify.App;
import com.pchmn.androidverify.R;

public class IdenticalValidator extends AbstractValidator {

    private EditText mOtherEditText;

    public IdenticalValidator(EditText view) {
        mOtherEditText = view;
        mErrorMessage = App.getContext().getString(R.string.error_fields_mismatch);
    }

    @Override
    public boolean isValid(String value) {
        return value.equals(mOtherEditText.getText().toString());
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
