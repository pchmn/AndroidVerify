package com.pchmn.androidverify.validator;


import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest="./src/main/AndroidManifest.xml")
public abstract class ValidatorTest {

    protected Context mContext;
    protected AbstractValidator mValidator;

    @Before
    public abstract void setUp();

    @Test
    public abstract void validate();

    @Test
    public abstract void message();
}
