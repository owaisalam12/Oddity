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
public class DeliveryPolicyFragment extends Fragment {


    public DeliveryPolicyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view=inflater.inflate(R.layout.fragment_delivery_policy, container, false);
        getActivity().setTitle("Delivery Policy");

        return view;
    }

}
