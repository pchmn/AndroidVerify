package com.pchmn.sample.formvalidator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.pchmn.formvalidator.Form;
import com.pchmn.formvalidator.InputValidator;
import com.pchmn.formvalidator.validators.AbstractValidator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OneFormActivity extends AppCompatActivity {

    private static final String TAG = OneFormActivity.class.toString();
    // xml elements
    @BindView(R.id.validate) Button mValidate;
    @BindView(R.id.custom_validator) InputValidator mInputValidator;
    private Form mForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_form);
        // butter knife
        ButterKnife.bind(this);

        mForm = new Form.Builder(this)
                .showErrors(true)
                .build();

        // custom validator
        mInputValidator.setCustomValidator(new AbstractValidator() {
            @Override
            public boolean isValid(String value) {
                return value.equals("ok man");
            }

            @Override
            public String getErrorMessage() {
                return "This field must be equals to 'ok man'";
            }
        });
    }

    @OnClick(R.id.validate)
    public void validate() {
        Toast.makeText(this, mForm.isValid() ? "The form is valid": "The form is not valid", Toast.LENGTH_SHORT).show();
    }
}
