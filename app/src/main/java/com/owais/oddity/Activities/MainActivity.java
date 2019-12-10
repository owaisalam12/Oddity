package com.owais.oddity.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.owais.oddity.CartNotification.NotificationCountSetClass;
import com.owais.oddity.Fragments.AboutUsFragment;
import com.owais.oddity.Fragments.DeliveryPolicyFragment;
import com.owais.oddity.Fragments.HomeDecorFragment;
import com.owais.oddity.Fragments.HomeFragment;
import com.owais.oddity.Fragments.HomeLivingFragment;
import com.owais.oddity.Fragments.MenFashionFragment;
import com.owais.oddity.Fragments.OddityDealsFragment;
import com.owais.oddity.Fragments.PaymentMethodsFragment;
import com.owais.oddity.Fragments.PrivacyPolicyFragment;
import com.owais.oddity.Fragments.ReturnRefundFragment;
import com.owais.oddity.Fragments.TermsConditionsFragment;
import com.owais.oddity.Fragments.WomenFashionFragment;
import com.owais.oddity.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ImageView imageOddityLogo;
    public static int notificationCountCart = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar2);

        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        imageOddityLogo = headerview.findViewById(R.id.nav_oddityLogo);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        navigationView.setNavigationItemSelectedListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#20111111"));
            //getWindow().setNavigationBarColor(Color.parseColor("#20111111"));

        }
        imageOddityLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFragment(new HomeFragment());
                closeDrwaer("Home Fragment");
            }
        });

        goToFragment(new HomeFragment());
       // goToFragment(new PlaceOrderFragment());
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void closeDrwaer(String Title) {
        drawerLayout.closeDrawer(GravityCompat.START);
        toolbar.setTitle(Title);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.action_cart:
                //Toast.makeText(this, "action shopping cart clicked!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ecommerce1_menu, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Get the notifications MenuItem and
        // its LayerDrawable (layer-list)
        MenuItem item = menu.findItem(R.id.action_cart);
        NotificationCountSetClass.setAddToCart(this, item,notificationCountCart);
        // force the ActionBar to relayout its MenuItems.
        // onCreateOptionsMenu(Menu) will be called again.
        invalidateOptionsMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    private void goToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, fragment).commit();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.nav_oddityDeals:
                //Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                goToFragment(new OddityDealsFragment());
                closeDrwaer("Oddity Deals");
                break;
            case R.id.nav_homeLiving:
                //Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                goToFragment(new HomeLivingFragment());
                closeDrwaer("Home & Living");
                break;
            case R.id.nav_homeDecor:
                //Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                goToFragment(new HomeDecorFragment());
                closeDrwaer("Home Decor");
                break;
            case R.id.nav_menFashion:
                //Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                goToFragment(new MenFashionFragment());
                closeDrwaer("Men Fashion");
                break;
            case R.id.nav_womenFashion:
                //Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                goToFragment(new WomenFashionFragment());
                closeDrwaer("Women Fashion");
                break;
            case R.id.nav_oddityLogo:
                //Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                goToFragment(new HomeFragment());
                closeDrwaer("Home Fragment");
                break;
            case R.id.about_us:
                //Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                goToFragment(new AboutUsFragment());
                closeDrwaer("About Us");
                break;
            case R.id.privacy_policy:
                //Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                goToFragment(new PrivacyPolicyFragment());
                closeDrwaer("Privacy Policy");
                break;
            case R.id.terms_conditions:
                //Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                goToFragment(new TermsConditionsFragment());
                closeDrwaer("Terms & Conditions");
                break;
            case R.id.delivery_policy:
                //Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                goToFragment(new DeliveryPolicyFragment());
                closeDrwaer("Delivery Policy");
                break;
            case R.id.returnrefund_policy:
                //Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                goToFragment(new ReturnRefundFragment());
                closeDrwaer("Return/Refund Policy");
                break;
            case R.id.payments_method:
                //Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                goToFragment(new PaymentMethodsFragment());
                closeDrwaer("Payment Methods");
                break;
            case R.id.my_cart:
                //Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
                closeDrwaer("Shopping Cart");
                break;
        }
        return true;
    }

}
