package com.owais.oddity.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.owais.oddity.Fragments.BillingDetailsFragment;
import com.owais.oddity.Fragments.PlaceOrderFragment;
import com.owais.oddity.R;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity{
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Checkout");
//        Bundle b= getIntent().getExtras();
//        ArrayList<String> itemName=new ArrayList<>();
//        ArrayList<String> itemQnty=new ArrayList<>();
//        itemName=b.getStringArrayList("itemName");
//        itemQnty=b.getStringArrayList("itemQnty");
//        Log.v("checkout_name",itemName.toString());
//        Log.v("checkout_quantity",itemQnty.toString());

//        String id=b.getString("id");
//        String name=b.getString("name");
//        String price=b.getString("price");
//        String description=b.getString("description");
//        String image=b.getString("image");
//        String quantity=b.getString("quantity");
//
//        Log.v("checkout_id",id);
//        Log.v("checkout_name",name);
//        Log.v("checkout_price",price);
//        Log.v("checkout_description",description);
//        Log.v("checkout_image",image);
//        Log.v("checkout_quantity",quantity);
        goToFragment(new BillingDetailsFragment());
       // goToFragment(new PlaceOrderFragment());
    }

    private void goToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, fragment).commit();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


}
