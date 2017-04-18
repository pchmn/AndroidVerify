package com.pchmn.sample.formvalidator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.pchmn.androidverify.Form;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TwoFormsActivity extends AppCompatActivity {

    @BindView(R.id.form1) Form mForm1;
    @BindView(R.id.form2) Form mForm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_forms);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.validate_form1)
    public void validateForm1() {
        Toast.makeText(this, mForm1.isValid() ? "Form 1 is valid": "Form 1 is not valid", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.validate_form2)
    public void validateForm2() {
        Toast.makeText(this, mForm2.isValid() ? "Form 2 is valid": "Form 2 is not valid", Toast.LENGTH_SHORT).show();
    }
}
