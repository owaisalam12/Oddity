package com.owais.oddity.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.owais.oddity.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillingDetailsFragment extends Fragment {

    private CheckBox checkBoxShipping;
    private LinearLayout linearLayoutShipping;
    private EditText editTextfullnameBilling,editTextPhoneBilling,editTextemailBilling,editTextStreetAddr1Billing,editTextStreetAddr2Billing,editTextTownCityBilling;
    private EditText editTextfullnameShipping,editTextCompanyShipping,editTextStreetAddr1Shipping,editTextStreetAddr2Shipping,editTextTownCityShipping,editTextPostCodeShipping,editTextOrderNotesShipping;
    private Spinner spinnerStateShipping;
    private Button buttonNext;

    public BillingDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_billing_details, container, false);
        checkBoxShipping=view.findViewById(R.id.billinginfo_checkboxShipDifferent);
        Bundle b= getActivity().getIntent().getExtras();
        final String id=b.getString("id");
        final String name=b.getString("name");
        final String price=b.getString("price");
        String description=b.getString("description");
        final String quantity=b.getString("quantity");

        final ArrayList<String> itemName=b.getStringArrayList("itemName");
        final ArrayList<String> itemQnty=b.getStringArrayList("itemQnty");
        final ArrayList<String> itemId=b.getStringArrayList("itemId");
        final ArrayList<String> itemPrice=b.getStringArrayList("itemPrice");
        final ArrayList<Integer> itemQntyIN=b.getIntegerArrayList("itemQntyIN");
        final ArrayList<Integer> itemIdIN=b.getIntegerArrayList("itemIdIN");


        Log.v("checkout_name",itemName.toString());
        Log.v("checkout_quantity",itemQnty.toString());
        Log.v("checkout_id",itemId.toString());
        Log.v("checkout_price",itemPrice.toString());


//        Log.v("checkout",id);
//        Log.v("checkout",name);
//        Log.v("checkout",price);
//        Log.v("checkout",description);

        editTextfullnameBilling=view.findViewById(R.id.billinginfo_fullName);
        editTextPhoneBilling=view.findViewById(R.id.billinginfo_phone);
        editTextemailBilling=view.findViewById(R.id.billinginfo_email);
        editTextStreetAddr1Billing=view.findViewById(R.id.billinginfo_streetaddress1);
        editTextStreetAddr2Billing=view.findViewById(R.id.billinginfo_streetaddress2);
        editTextTownCityBilling=view.findViewById(R.id.billinginfo_townCity);

        editTextfullnameShipping=view.findViewById(R.id.shippingdetails_fullName);
        editTextCompanyShipping=view.findViewById(R.id.shippingdetails_companyName);
        editTextStreetAddr1Shipping=view.findViewById(R.id.shippingdetails_streetaddress1);
        editTextStreetAddr2Shipping=view.findViewById(R.id.shippingdetails_streetaddress2);
        editTextTownCityShipping=view.findViewById(R.id.shippingdetails_townCity);
        editTextPostCodeShipping=view.findViewById(R.id.shippingdetails_postcodezip);
        editTextOrderNotesShipping=view.findViewById(R.id.billinginfo_Ordernotes);
        spinnerStateShipping=view.findViewById(R.id.shippingdetails_spinnerState);
        buttonNext=view.findViewById(R.id.billingdetails_NextButton);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullnameBilling=editTextfullnameBilling.getText().toString();
                String PhoneBilling=editTextPhoneBilling.getText().toString();
                String emailBilling=editTextemailBilling.getText().toString();
                String streetAddr1Billing=editTextStreetAddr1Billing.getText().toString();
                String streetAddr2Billing=editTextStreetAddr2Billing.getText().toString();
                String townCityBilling=editTextTownCityBilling.getText().toString();

                String fullnameShipping=editTextfullnameShipping.getText().toString();
                String companyShipping=editTextCompanyShipping.getText().toString();
                String streetAddr1Shipping=editTextStreetAddr1Shipping.getText().toString();
                String streetAddr2Shipping=editTextStreetAddr2Shipping.getText().toString();
                String townCityShipping=editTextTownCityShipping.getText().toString();
                String postCodeShipping=editTextPostCodeShipping.getText().toString();
                String orderNotesShipping=editTextOrderNotesShipping.getText().toString();
                String stateShippingSpinner=spinnerStateShipping.getSelectedItem().toString();
                Bundle args = new Bundle();
                if (!validateEditText(editTextfullnameBilling)) {
                    return;
                }
                if (!validateEditText(editTextPhoneBilling)) {
                    return;
                }
                if (!validateEmail(emailBilling,editTextemailBilling)) {
                    return;
                }
                if (!validateEditText(editTextStreetAddr1Billing)) {
                    return;
                }
                if (!validateEditText(editTextTownCityBilling)) {
                    return;
                }

                if(checkBoxShipping.isChecked()){
                    if (!validateEditText(editTextfullnameShipping)) {
                        return;
                    }
                    if (!validateEditText(editTextStreetAddr1Shipping)) {
                        return;
                    }
                    if (!validateEditText(editTextTownCityShipping)) {
                        return;
                    }
                    if (!validateEditText(editTextPostCodeShipping)) {
                        return;
                    }
                   // Toast.makeText(getContext(), "Shipping", Toast.LENGTH_SHORT).show();
                    args.putString("fullnameShipping", fullnameShipping);
                    args.putString("streetAddr1Shipping", streetAddr1Shipping);
                    args.putString("townCityShipping", townCityShipping);
                    args.putString("postCodeShipping", postCodeShipping);
                    args.putString("stateShippingSpinner", stateShippingSpinner);


                }
              //  Toast.makeText(getContext(), "without Shipping", Toast.LENGTH_SHORT).show();

                args.putString("id", id);
                args.putString("name", name);
                args.putString("price", price);
                args.putString("quantity", quantity);

                args.putStringArrayList("itemName",itemName);
                args.putStringArrayList("itemQnty",itemQnty);
                args.putStringArrayList("itemId",itemId);
                args.putStringArrayList("itemPrice",itemPrice);
                args.putIntegerArrayList("itemQntyIN",itemQntyIN);
                args.putIntegerArrayList("itemIdIN",itemIdIN);

                args.putString("fullnameBilling", fullnameBilling);
                args.putString("PhoneBilling", PhoneBilling);
                args.putString("streetAddr1Billing", streetAddr1Billing);
                args.putString("emailBilling", emailBilling);
                args.putString("townCityBilling", townCityBilling);
                args.putString("orderNotesShipping", orderNotesShipping);

                goToFragment(new PlaceOrderFragment(),args);

            }
        });

        linearLayoutShipping=view.findViewById(R.id.shippingDetails_layout);
        checkBoxShipping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if(isChecked){
                linearLayoutShipping.setVisibility(View.VISIBLE);
            }else{
                linearLayoutShipping.setVisibility(View.GONE);

            }
            }
        });
        return view;
    }
    private void goToFragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, fragment).addToBackStack(null).commit();
    }
    private boolean validateEditText(EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            // inputLayoutName.setError("Enter your name");
            editText.setError("This field is required");
            requestFocus(editText);
            return false;
        } else {

        }

        return true;
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    private boolean validateEmail(String email,EditText editTextEmail) {

        if (editTextEmail.getText().toString().trim().isEmpty()) {
            //inputLayoutEmail.setError("Enter your email");
            editTextEmail.setError("This field is required");
            editTextEmail.requestFocus();
            return false;
        } else {
            // inputLayoutEmail.setErrorEnabled(false);

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //inputLayoutEmail.setError("Valid email is required");
            editTextEmail.setError("Valid email is required");
            editTextEmail.requestFocus();
            return false;
        } else {
            //inputLayoutName.setErrorEnabled(false);
        }
        return true;

    }
}
