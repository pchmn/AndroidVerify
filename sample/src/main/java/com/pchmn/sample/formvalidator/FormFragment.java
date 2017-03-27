package com.pchmn.sample.formvalidator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pchmn.formvalidator.Form;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class FormFragment extends Fragment {

    private Form mForm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // butter knife
        ButterKnife.bind(this, view);

        // pass the root view
        mForm = new Form.Builder(getContext(), view)
                .build();
    }

    @OnClick(R.id.validate)
    public void validate() {
        Toast.makeText(getContext(), mForm.isValid() ? "The form is valid": "The form is not valid", Toast.LENGTH_SHORT).show();
    }
}
