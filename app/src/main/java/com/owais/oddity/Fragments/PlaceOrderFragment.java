package com.owais.oddity.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.owais.oddity.API.Responses.orderCreatedResponse;
import com.owais.oddity.API.RetrofitClient;
import com.owais.oddity.Activities.MainActivity;
import com.owais.oddity.Adapters.CartItemAdapter;
import com.owais.oddity.CartItemModel;
import com.owais.oddity.CartModel;
import com.owais.oddity.R;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceOrderFragment extends Fragment {


    public PlaceOrderFragment() {
        // Required empty public constructor
    }
   private RadioGroup radioGroupPayment;
    private RadioButton radioButtonPayMethod;
    private TextView textViewCoD,textViewBank,textViewOrderItemName,textViewOrderPrice,textViewOrderSubtotal,textViewOrderTotal;
    private String id,name,price,country,lastname,quantity,orderNotesShipping,state,payment,fullnameBilling,PhoneBilling,streetAddr1Billing,emailBilling,townCityBilling,fullnameShipping,streetAddr1Shipping,townCityShipping,postCodeShipping;

    AlertDialog dialog;
    Button buttonPlaceOrder;
    private CheckBox checkBoxTermsCondition;
    private List<Integer> product_id2,quantity2;
    private List<Integer> itemIdIN,itemQntyIN;
    private List<String> itemName,itemQnty,itemId,itemPrice;
    ArrayList<CartModel> mCartModels;
    RecyclerView recyclerView;
    CartItemAdapter cartItemAdapter;
    List<CartItemModel> cartItemModelList;
    Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_place_order2, container, false);
        radioGroupPayment=view.findViewById(R.id.orderItem_radioGrPayment);
        textViewCoD=view.findViewById(R.id.orderItem_radioBtTextCoD);
        textViewBank=view.findViewById(R.id.orderItem_radioBtTextBank);
        checkBoxTermsCondition=view.findViewById(R.id.orderItem_termsCondition);
        buttonPlaceOrder=view.findViewById(R.id.orderItemPlaceOrderButton);
        textViewOrderItemName=view.findViewById(R.id.orderItemNameText);
        textViewOrderPrice=view.findViewById(R.id.orderItemPriceText);
        textViewOrderSubtotal=view.findViewById(R.id.orderItemSubTotalText);
        textViewOrderTotal=view.findViewById(R.id.orderItemTotalPriceText);
         mCartModels=new ArrayList<>();

        mContext=getContext();
        product_id2 =new ArrayList<>();
        quantity2=new ArrayList<>();

        itemIdIN=new ArrayList<>();
        itemQntyIN=new ArrayList<>();

        itemName=new ArrayList<>();
        itemQnty=new ArrayList<>();
        itemId=new ArrayList<>();
        itemPrice=new ArrayList<>();

        cartItemModelList=new ArrayList<>();

        dialog = new SpotsDialog.Builder().setContext(getContext())
                .setCancelable(false)
                .setTheme(R.style.Custom)
                .build();
        int selectedId = radioGroupPayment.getCheckedRadioButtonId();
        Log.v("radio",String.valueOf(selectedId));
        radioButtonPayMethod=radioGroupPayment.findViewById(selectedId);
        payment=radioButtonPayMethod.getText().toString();
        Log.v("radio2",String.valueOf(payment));
     //   radioButtonPayMethod.setSelected(true);

        if(getArguments()!=null){
            id=getArguments().getString("id");
            name=getArguments().getString("name");
            price=getArguments().getString("price");
            quantity=getArguments().getString("quantity");
            itemName=getArguments().getStringArrayList("itemName");
            itemQnty=getArguments().getStringArrayList("itemQnty");
            itemId=getArguments().getStringArrayList("itemId");
            itemPrice=getArguments().getStringArrayList("itemPrice");
            itemIdIN=getArguments().getIntegerArrayList("itemIdIN");
            itemQntyIN=getArguments().getIntegerArrayList("itemQntyIN");

            country="Pakistan";
            lastname="";
            state="";
            String state2=getArguments().getString("stateShippingSpinner");
            if(state2!=null){
                state=state2;
            }

            fullnameBilling=getArguments().getString("fullnameBilling");
            PhoneBilling=getArguments().getString("PhoneBilling");
            streetAddr1Billing=getArguments().getString("streetAddr1Billing");
            emailBilling=getArguments().getString("emailBilling");
            townCityBilling=getArguments().getString("townCityBilling");
            fullnameShipping=getArguments().getString("fullnameShipping");
            streetAddr1Shipping=getArguments().getString("streetAddr1Shipping");
            townCityShipping=getArguments().getString("townCityShipping");
            postCodeShipping=getArguments().getString("postCodeShipping");
            orderNotesShipping=getArguments().getString("orderNotesShipping");


//            for(String name:itemName){
//                cartItemModelList.add(new CartItemModel(name));
//            }

            for(int i=0;i<itemName.size();i++){
                cartItemModelList.add(new CartItemModel(itemName.get(i),itemPrice.get(i),itemQntyIN.get(i)));
            }

            for(CartItemModel a:cartItemModelList){
                Log.v("cartTEST",a.getName2());
                Log.v("cartTEST",a.getPrice2());
                Log.v("cartTEST",Integer.toString(a.getQuantity2()));
            }
//            for(String price:itemPrice){
//
//            }
//            for(int qnty:itemQntyIN){
//
//            }

            //cartItemModelList.add(new CartItemModel(itemName,itemPrice,itemQntyIN));

            recyclerView =view.findViewById(R.id.recyclerView_shoppingCart);
            RecyclerView.LayoutManager recylerViewLayoutManager = new LinearLayoutManager(mContext);

            recyclerView.setLayoutManager(recylerViewLayoutManager);
            //cartItemAdapter=new CartItemAdapter(cartItemModelList,mContext);
            cartItemAdapter=new CartItemAdapter(cartItemModelList,mContext,textViewOrderSubtotal,textViewOrderTotal);
            recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
            recyclerView.setAdapter(cartItemAdapter);

//            cartItemModelList.clear();




//            product_id2.add(Integer.valueOf(id));
//            quantity2.add(Integer.valueOf(quantity));
//            product_id2.add(13374);
//            quantity2.add(1);
//            product_id2.add(13393);
//            quantity2.add(2);

        }
//        if(name!=null){
//            textViewOrderItemName.setText(name+" x "+1);
//        }
//
//        int priceQuantity=Integer.parseInt(price)*1;
//
//        if(priceQuantity>0){
//            textViewOrderPrice.setText("Rs "+priceQuantity+".00");
//            textViewOrderSubtotal.setText("Rs "+priceQuantity+".00");
//            textViewOrderTotal.setText("Rs "+priceQuantity+".00");
//
//        }
        radioGroupPayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.orderItem_radioBtCoD){
                    textViewCoD.setVisibility(View.VISIBLE);
                    textViewBank.setVisibility(View.GONE);

                }else if(checkedId == R.id.orderItem_radioBtBank) {
                    textViewBank.setVisibility(View.VISIBLE);
                    textViewCoD.setVisibility(View.GONE);

                }else{

                }
            }
        });

        buttonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroupPayment.getCheckedRadioButtonId();
                radioButtonPayMethod=radioGroupPayment.findViewById(selectedId);
                payment=radioButtonPayMethod.getText().toString();
                String paymentId="";
                if(payment.equals("Direct Bank Transfer")){
                    paymentId="bacs";
                }else if(payment.equals("Cash on delivery")){
                    paymentId="cod";

                }else{

                }

                if(checkBoxTermsCondition.isChecked()){
                    dialog.show();
                    createOrder(fullnameBilling,lastname,streetAddr1Billing,townCityBilling,country,state,emailBilling,PhoneBilling,orderNotesShipping,paymentId,payment,itemIdIN,itemQntyIN);
                }else{
                    Toast.makeText(getContext(), "You must accept Terms & Conditions", Toast.LENGTH_SHORT).show();
                }



            }
        });
        return view;
    }
    private void createOrder(String first_name, String last_name,String address_1,String city,String country,String state,String email,String phone,String orderNotes,String payment_method_id,String payment_method, List<Integer> product_id2,List<Integer> quantity2){


//        int[]a={13393,13393};
//        int[]b={1,1};
        //Call<orderCreatedResponse>call= RetrofitClient.getInstance().getApi().createOrder(first_name,last_name,address_1,city,country,state,email,phone,payment_method,product_id,quantity);
        Call<orderCreatedResponse>call= RetrofitClient.getInstance().getApi().createOrder2(first_name,last_name,address_1,city,country,state,email,phone,orderNotes,payment_method_id,payment_method,product_id2,quantity2);
        call.enqueue(new Callback<orderCreatedResponse>() {
            @Override
            public void onResponse(Call<orderCreatedResponse> call, Response<orderCreatedResponse> response) {
                orderCreatedResponse orderCreatedResponse=response.body();
                if (orderCreatedResponse.getSuccess() != 0) {
                    dialog.dismiss();
                    Log.v("order1",orderCreatedResponse.getMessage());
                    //Toast.makeText(getActivity(), orderResponse.getMessage(), Toast.LENGTH_SHORT).show();
                   // MainActivity.notificationCountCart=0;
                    dialogSuccess(new Intent(getContext(), MainActivity.class));

                }else{
                    dialog.dismiss();
                    dialogError("Something went wrong",orderCreatedResponse.getMessage());
                    Log.v("order2",orderCreatedResponse.getMessage());
                    // Toast.makeText(getActivity(), orderResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<orderCreatedResponse> call, Throwable t) {
                dialog.dismiss();
                dialogError("Something went wrong",t.getMessage().toString());

                Log.v("order3",t.getMessage().toString());
                // Toast.makeText(getActivity(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void dialogError(String title, String msg) {

        final PrettyDialog pDialog = new PrettyDialog(getContext());
        pDialog.setCancelable(false);
        pDialog

                .setIcon(R.drawable.pdlg_icon_close)
                .setIconTint(R.color.pdlg_color_red)
                .setAnimationEnabled(false)
                .setTitle(title)
                .setMessage(msg)

                .addButton(
                        "OK",
                        R.color.pdlg_color_white,
                        R.color.pdlg_color_red,
                        new PrettyDialogCallback() {
                            @Override
                            public void onClick() {
                                pDialog.dismiss();
                            }
                        }
                )
                .show();
    }
    private void dialogSuccess(final Intent intent){

        final PrettyDialog pDialog = new PrettyDialog(getContext());
        pDialog.setCancelable(false);
        pDialog

                .setIcon(R.drawable.pdlg_icon_success)
                .setIconTint(R.color.pdlg_color_green)
                .setAnimationEnabled(false)
                .setTitle("Congratulations")
                .setMessage("Your order has been confirmed. We will contact you soon.")

                .addButton(
                        "OK",
                        R.color.pdlg_color_white,
                        R.color.pdlg_color_green,
                        new PrettyDialogCallback() {
                            @Override
                            public void onClick() {
                                mCartModels.clear();
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                getActivity().finish();

                                //goToFragment(fragment);
                                pDialog.dismiss();
                            }
                        }
                )
                .show();
    }
    private void goToFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, fragment).addToBackStack(null).commit();
    }

}
