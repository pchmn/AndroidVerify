package com.pchmn.androidverify;


import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Form extends LinearLayout {

    private static final String TAG = Form.class.toString();
    // activity
    private Activity mActivity;
    private ViewGroup mViewGroupRoot;
    // show errors
    private boolean mShowErrors = true;
    // validity
    private boolean mIsValid;
    // input validators
    private List<InputValidator> mInputValidatorList = new ArrayList<>();
    // inflated
    private boolean mInflated = false;

    public Form(Context context) {
        super(context);
    }

    public Form(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Form,
                0, 0);

        try {
            mShowErrors = a.getBoolean(R.styleable.Form_showErrors, true);
        }
        finally {
            a.recycle();
        }
    }

    /**
     * Check if the form is valid
     *
     * @return true if it is valid, false either
     */
    public boolean isValid() {
        validate();
        return mIsValid;
    }

    /**
     * Validate the form by getting and checking all the fields of the form
     */
    private void validate() {
        // get the fields
        if(!mInflated) {
            int childCount = getChildCount();

            if(childCount == 0 && mActivity != null)
                getChildViews(((ViewGroup) mActivity.getWindow().getDecorView().findViewById(android.R.id.content)));
            else if(childCount == 0 && mViewGroupRoot != null)
                getChildViews(mViewGroupRoot);
            else
                getChildViews(this);

            mInflated = true;
        }

        // check the fields
        validateList();
    }

    /**
     * Get all fields
     *
     * @param viewGroup the root view
     */
    private void getChildViews(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View v = viewGroup.getChildAt(i);

            // only if input validator
            if(v instanceof InputValidator)
                mInputValidatorList.add((InputValidator) v);
                // iterate threw view group children
            else if(v instanceof ViewGroup)
                getChildViews((ViewGroup) v);
        }
    }

    /**
     * Validate all the fields of the form
     */
    private void validateList() {
        mIsValid = true;
        for(InputValidator inputValidator: mInputValidatorList) {
            // show error
            inputValidator.setShowError(mShowErrors);
            // if validator not valid, the form is not valid either
            if(!inputValidator.isValid())
                mIsValid = false;
        }
    }

    public void setShowErrors(boolean mShowErrors) {
        this.mShowErrors = mShowErrors;
    }

    public void addInputValidator(InputValidator inputValidator) {
        mInputValidatorList.add(inputValidator);
    }

    public void setInputValidatorList(List<InputValidator> mInputValidatorList) {
        this.mInputValidatorList = mInputValidatorList;
    }

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void setViewGroupRoot(ViewGroup mViewGroupRoot) {
        this.mViewGroupRoot = mViewGroupRoot;
    }

    /**
     * Builder class for Form
     */
    public static class Builder {
        private Activity activity;
        private Context context;
        private ViewGroup viewGroup;
        private boolean showErrors = true;
        private List<InputValidator> inputValidatorList = new ArrayList<>();

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder(Context context, View rootView) {
            this.context = context;
            this.viewGroup = (ViewGroup) rootView;
        }

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * Whether to show error messages
         *
         * @param showErrors true to show, false either
         * @return the Builder
         */
        public Builder showErrors(boolean showErrors) {
            this.showErrors = showErrors;
            return this;
        }

        /**
         * Add an InputValidator to the form
         *
         * @param inputValidator the input validator
         * @return the Builder
         */
        public Builder addInputValidator(InputValidator inputValidator) {
            this.inputValidatorList.add(inputValidator);
            return this;
        }

        /**
         * Build a new Form object
         *
         * @return a Form object
         */
        public Form build() {
            return Form.newInstance(this);
        }
    }

    /**
     * Create a new instance of Form according to a Builder
     *
     * @param builder the builder
     * @return a Form object
     */
    public static Form newInstance(Builder builder) {
        Form form;
        if(builder.activity != null) {
            form = new Form(builder.activity);
            form.setActivity(builder.activity);
        }
        else if(builder.viewGroup != null) {
            form = new Form(builder.context);
            form.setViewGroupRoot(builder.viewGroup);
        }
        else {
            form = new Form(builder.context);
        }
        form.setShowErrors(builder.showErrors);
        form.setInputValidatorList(builder.inputValidatorList);
        return form;
    }
}
