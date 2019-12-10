package com.owais.oddity.Adapters;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.owais.oddity.CartItemModel;
import com.owais.oddity.CartModel;
import com.owais.oddity.Fragments.HomeLivingFragment;
import com.owais.oddity.Fragments.PlaceOrderFragment;
import com.owais.oddity.R;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.MyHolder> implements View.OnClickListener{

    List<CartItemModel> list;
    private Context context;
    private Fragment fragment;
    private TextView textViewSubTotal;
    private TextView textViewTotal;

    public CartItemAdapter(List<CartItemModel> list) {
        this.list = list;
    }

    public CartItemAdapter(List<CartItemModel> list, Context context) {
        this.list = list;
        this.context = context;


    }

    public CartItemAdapter(List<CartItemModel> list, Context context, TextView textViewSubTotal, TextView textViewTotal) {
        this.list = list;
        this.context = context;
        this.textViewSubTotal = textViewSubTotal;
        this.textViewTotal = textViewTotal;
    }

    public CartItemAdapter(List<CartItemModel> list, Context context, Fragment fragment) {
        this.list = list;
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public void onClick(View view) {
        Log.d("Recycler", "onClick " + view.toString());
    }

    @NonNull
    @Override
    public CartItemAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.placeorder_listitem, parent, false);
        CartItemAdapter.MyHolder myHolder = new CartItemAdapter.MyHolder(view);
        context = parent.getContext();
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemAdapter.MyHolder holder, int position) {
        CartItemModel product = list.get(position);

//       for(int i=0;i<product.getQuantity().size();i++){
//           Log.v("cartItemFOR",product.getName().get(i));
//       }
        holder.itemName.setText(product.getName2());
        holder.itemQuantity.setText("x "+product.getQuantity2());
        int priceQuantity=Integer.parseInt(product.getPrice2())*product.getQuantity2();

            if(priceQuantity>0){
                holder.itemPrice.setText("Rs. "+priceQuantity);
                //  textViewOrderSubtotal.setText("Rs "+priceQuantity+".00");
                //    textViewOrderTotal.setText("Rs "+priceQuantity+".00");
            }
        int totalPrice = 0;
        for (int i = 0; i<list.size(); i++)
        {
            int quantity=list.get(i).getQuantity2();
            int price=Integer.parseInt(list.get(i).getPrice2());

            totalPrice+=quantity*price;
            //totalPrice += list.get(i).priceQuantity;
        }
        Log.v("CheckoutPrice",""+totalPrice);
        textViewTotal.setText("Rs. " +totalPrice);
        textViewSubTotal.setText("Rs. " +totalPrice);
        // new PlaceOrderFragment().setTotals(String.valueOf(totalPrice));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemName, itemPrice,itemQuantity;
        public MyHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.cartItemName);
            itemPrice = itemView.findViewById(R.id.cartItemPrice);
            itemQuantity = itemView.findViewById(R.id.cartItemQuantity);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

//            if(list.get(getAdapterPosition()).getMessage()!=null){
//                Log.d("Recycler", "onClick " + getAdapterPosition());
//
//            }

        }
    }
}
