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
public class ReturnRefundFragment extends Fragment {


    public ReturnRefundFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_return_refund, container, false);
        getActivity().setTitle("Return/Refund Policy");
        return view;
    }

}
