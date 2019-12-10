package com.owais.oddity.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.owais.oddity.API.Responses.ProductsByCategory.Category;
import com.owais.oddity.AddCartModel;
import com.owais.oddity.CartModel;
import com.owais.oddity.ImageUrlUtils;
import com.owais.oddity.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCartActivity extends AppCompatActivity {
    private static Context mContext;
    public static boolean add= true;
    public static int sum= 0;
    private TextView textViewTotalPrice;
    private TextView buttonCheckout;

    SimpleStringRecyclerViewAdapter simpleStringRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        setTitle("Shopping Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mContext = ShoppingCartActivity.this;
        textViewTotalPrice=findViewById(R.id.totalPrice_shoppingCart);
        buttonCheckout=findViewById(R.id.btnCheckout_shoppingCart);
        //ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
        // ArrayList<String> cartlistImageUri =imageUrlUtils.getCartListImageUri();




        AddCartModel addCartModel=new AddCartModel();
        final ArrayList<CartModel> cartModelList=addCartModel.getCartListModel();


        setCartLayout();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView_shoppingCartItem);
        RecyclerView.LayoutManager recylerViewLayoutManager = new LinearLayoutManager(mContext);

        recyclerView.setLayoutManager(recylerViewLayoutManager);
        simpleStringRecyclerViewAdapter=new SimpleStringRecyclerViewAdapter(recyclerView, cartModelList,textViewTotalPrice);
        recyclerView.setAdapter(simpleStringRecyclerViewAdapter);

        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                ArrayList<String> itemName=new ArrayList<>();
                ArrayList<String> itemQnty=new ArrayList<>();
                ArrayList<String> itemId=new ArrayList<>();
                ArrayList<String> itemPrice=new ArrayList<>();

                ArrayList<Integer> itemQntyIN=new ArrayList<>();
                ArrayList<Integer> itemIdIN=new ArrayList<>();

                for(CartModel cartModel:cartModelList){
                    Log.v("cartModel",cartModel.getName());
                    itemName.add(cartModel.getName());
                    itemQnty.add(cartModel.getQuantity());
                    itemId.add(cartModel.getId());
                    itemPrice.add(cartModel.getPrice());

                    bundle.putStringArrayList("itemName",itemName);
                    bundle.putStringArrayList("itemQnty",itemQnty);
                    bundle.putStringArrayList("itemId",itemId);
                    bundle.putStringArrayList("itemPrice",itemPrice);

                    itemQntyIN.add(Integer.parseInt(cartModel.getQuantity()));
                    itemIdIN.add(Integer.parseInt(cartModel.getId()));

                    bundle.putIntegerArrayList("itemQntyIN",itemQntyIN);
                    bundle.putIntegerArrayList("itemIdIN",itemIdIN);

                }


                Intent intent=new Intent(ShoppingCartActivity.this, CheckoutActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

//                Toast.makeText(ShoppingCartActivity.this, "checkout clicked", Toast.LENGTH_SHORT).show();


            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<ShoppingCartActivity.SimpleStringRecyclerViewAdapter.ViewHolder> {

        private ArrayList<String> mCartlistImageUri;
        private ArrayList<CartModel> mCartModels;
        private RecyclerView mRecyclerView;

        public SimpleStringRecyclerViewAdapter(RecyclerView mRecyclerView, ArrayList<CartModel> mCartModels, TextView textViewTotal) {
            this.mCartModels = mCartModels;
            this.mRecyclerView = mRecyclerView;
            this.textViewTotal = textViewTotal;
        }

        private TextView textViewTotal;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final SimpleDraweeView mImageView;
            public final TextView textViewTitle,textViewPrice,textViewQuantity;
            public final LinearLayout mLayoutItem, mLayoutRemove , mLayoutEdit;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImageView =  view.findViewById(R.id.image_shoppingcart);
                textViewTitle =  view.findViewById(R.id.title_shoppingcart);
                textViewPrice =  view.findViewById(R.id.price_shoppingcart);
                textViewQuantity =  view.findViewById(R.id.quantity_shoppingcart);
                mLayoutItem =  view.findViewById(R.id.layout_item_desc);
                mLayoutRemove =  view.findViewById(R.id.layout_removeItem_shoppingcart);
                mLayoutEdit = view.findViewById(R.id.layout_editItem_shoppingcart);
            }
        }

        public SimpleStringRecyclerViewAdapter(RecyclerView recyclerView, ArrayList<CartModel> cartModels) {
           // mCartlistImageUri = wishlistImageUri;
            mCartModels = cartModels;
            mRecyclerView = recyclerView;
        }

        @Override
        public ShoppingCartActivity.SimpleStringRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoppingcart_item, parent, false);
            return new ShoppingCartActivity.SimpleStringRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onViewRecycled(ShoppingCartActivity.SimpleStringRecyclerViewAdapter.ViewHolder holder) {
            if (holder.mImageView.getController() != null) {
                holder.mImageView.getController().onDetach();
            }
            if (holder.mImageView.getTopLevelDrawable() != null) {
                holder.mImageView.getTopLevelDrawable().setCallback(null);
//                ((BitmapDrawable) holder.mImageView.getTopLevelDrawable()).getBitmap().recycle();
            }
        }

        @Override
        public void onBindViewHolder(final ShoppingCartActivity.SimpleStringRecyclerViewAdapter.ViewHolder holder, final int position) {
           CartModel product=mCartModels.get(position);

            String name=mCartModels.get(position).getName();
            String description=mCartModels.get(position).getDescription();
            String id=mCartModels.get(position).getId();
            String price=mCartModels.get(position).getPrice();
            String quantity=mCartModels.get(position).getQuantity();
            String image=mCartModels.get(position).getImage();
            //holder.mImageView.setImageURI("https://static.pexels.com/photos/68562/pexels-photo-68562-medium.jpeg");

            holder.mImageView.setImageURI(image);
            holder.textViewPrice.setText("Rs. "+price);
            holder.textViewQuantity.setText(quantity);
            holder.textViewTitle.setText(name);

            int totalPrice = 0;
            for (int i = 0; i<mCartModels.size(); i++)
            {
                int quantity2=Integer.parseInt(mCartModels.get(i).getQuantity());
                int price2=Integer.parseInt(mCartModels.get(i).getPrice());

                totalPrice+=quantity2*price2;
                //totalPrice += list.get(i).priceQuantity;
            }
            Log.v("CartActivityPrice",""+totalPrice);




                textViewTotal.setText("Rs. "+totalPrice);

            holder.mLayoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(mContext, ItemDetailsActivity.class);
//                    intent.putExtra(STRING_IMAGE_URI,mCartlistImageUri.get(position));
//                    intent.putExtra(STRING_IMAGE_POSITION, position);
//                    mContext.startActivity(intent);
                }
            });

            //Set click action
            holder.mLayoutRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
//                    imageUrlUtils.removeCartListImageUri(position);
//
                    AddCartModel addCartModel=new AddCartModel();
                    addCartModel.removeCartListModel(position);
                    notifyDataSetChanged();
                    //Decrease notification count
                    MainActivity.notificationCountCart--;
                    if(mCartModels.isEmpty()||mCartModels.size()<1){
                        textViewTotal.setText("Rs. 0");
                    }
                }
            });

            //Set click action
            holder.mLayoutEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return mCartModels.size();
        }
    }


    protected void setCartLayout(){
        LinearLayout layoutCartItems = (LinearLayout) findViewById(R.id.layout_items);
        LinearLayout layoutCartPayments = (LinearLayout) findViewById(R.id.layout_payment);
        LinearLayout layoutCartNoItems = (LinearLayout) findViewById(R.id.layout_cart_empty);

        if(MainActivity.notificationCountCart >0){
            layoutCartNoItems.setVisibility(View.GONE);
            layoutCartItems.setVisibility(View.VISIBLE);
            layoutCartPayments.setVisibility(View.VISIBLE);
        }else {
            layoutCartNoItems.setVisibility(View.VISIBLE);
            layoutCartItems.setVisibility(View.GONE);
            layoutCartPayments.setVisibility(View.GONE);

            Button bStartShopping = (Button) findViewById(R.id.bAddNew);
            bStartShopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

}
