package com.pchmn.sample.formvalidator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.one_form_activity)
    public void oneFormActivity() {
        startActivity(new Intent(this, OneFormActivity.class));
    }

    @OnClick(R.id.two_forms_activity)
    public void twoFormsActivity() {
        startActivity(new Intent(this, TwoFormsActivity.class));
    }

    @OnClick(R.id.form_fragment)
    public void formFragment() {
        startActivity(new Intent(this, FormFragmentActivity.class));
    }

    @OnClick(R.id.java_form_activity)
    public void javaFormActivity() {
        startActivity(new Intent(this, JavaFormActivity.class));
    }
}
