package com.pchmn.formvalidator.validators;


import com.pchmn.formvalidator.App;
import com.pchmn.formvalidator.R;

import java.util.regex.Pattern;

public class RegexValidator extends AbstractValidator {

    private Pattern mRegexPattern;

    public RegexValidator(String regex) {
        mRegexPattern = Pattern.compile(regex);
        mErrorMessage = App.getContext().getString(R.string.error_invalid_field);
    }

    @Override
    public boolean isValid(String value) {
        return mRegexPattern.matcher(value).matches();
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
