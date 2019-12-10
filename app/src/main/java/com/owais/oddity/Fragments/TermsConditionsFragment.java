package com.owais.oddity.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owais.oddity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TermsConditionsFragment extends Fragment {


    public TermsConditionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_terms_conditions, container, false);
        getActivity().setTitle("Terms & Conditions");
        return view;
    }

}
