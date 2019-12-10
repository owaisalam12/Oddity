package com.owais.oddity.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.owais.oddity.CartModel;
import com.owais.oddity.CartNotification.NotificationCountSetClass;
import com.owais.oddity.Fragments.ShoppingFragment;
import com.owais.oddity.ImageUrlUtils;
import com.owais.oddity.R;
import com.owais.oddity.AddCartModel;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingActivity extends AppCompatActivity  implements View.OnClickListener{
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    SliderView sliderView;
    ImageView img;
    private String id,name,description,price;
    ArrayList<String> imagesList;
    ArrayList<String> categoriesList;
    List<CartModel> cartList;
    Context context;
    int size;
    int quantity=1;
    EditText editTextQuatityText;
//    public static int notificationCountCart = 0;
    ArrayList<String> sizeList;
    TextView textViewTitle,textViewCategory,textViewPrice,textViewDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        imagesList=new ArrayList<>();
        categoriesList=new ArrayList<>();
        textViewTitle=findViewById(R.id.shopping_title);
        textViewCategory=findViewById(R.id.shopping_category);
        textViewPrice=findViewById(R.id.shopping_price);
        textViewDescription=findViewById(R.id.shopping_description);
        editTextQuatityText=findViewById(R.id.quantityText);
        cartList=new ArrayList<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(getIntent().getExtras()!=null){
            id=getIntent().getExtras().getString("id");
            name=getIntent().getExtras().getString("name");
            description=getIntent().getExtras().getString("description");
            price=getIntent().getExtras().getString("price");
            imagesList=getIntent().getExtras().getStringArrayList("imagesList");
            categoriesList=getIntent().getExtras().getStringArrayList("categoriesList");
            String category=categoriesList.toString();
            String category1=category.replace(","," >");
            String category2= category1.replaceAll("\\[|\\]", "");
            Log.v("shopping",id);
            Log.v("shopping",name);
            Log.v("shopping",description);
            Log.v("shopping",price);
            Log.v("shopping",category2);
            Log.v("shopping",imagesList.toString());

            textViewTitle.setText(name);
            textViewCategory.setText(category2);
            textViewPrice.setText("Rs."+price);
            textViewDescription.setText(description);

        }
      //  sliderView = findViewById(R.id.imageSliderShopping);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new WizardPageChangeListener());

        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.ecommerce13_viewpager_margin));


        Log.v("imagesCountSize",""+imagesList.size());
        Log.v("shoppingOn","onCreate");
//        final SliderAdapterShopping adapter = new SliderAdapterShopping(this);
//        adapter.setCount(imagesList.size());
//        adapter.setArrayListImages(imagesList);
//        sliderView.setSliderAdapter(adapter);
//
//        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
//        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
//        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
//        sliderView.setIndicatorSelectedColor(Color.WHITE);
//        sliderView.setIndicatorUnselectedColor(Color.GRAY);
//        sliderView.startAutoCycle();
//
//        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
//            @Override
//            public void onIndicatorClicked(int position) {
//                sliderView.setCurrentPagePosition(position);
//            }
//        });
        //img =  findViewById(R.id.imagePage1);
    }



    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private int WIZARD_PAGES_COUNT = 1;


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            sizeList=getIntent().getExtras().getStringArrayList("imagesList");
            viewPager.setOffscreenPageLimit(sizeList.size());
            viewPager.setSaveFromParentEnabled(false);
            Log.v("shopping","ViewPagerAdapter");
           // viewPagerAdapter.notifyDataSetChanged();


        }
                    //Toast.makeText(ShoppingActivity.this, "clicked: "+view.getId(), Toast.LENGTH_SHORT).show();




        @Override
        public Fragment getItem(int position) {
            return new ShoppingFragment(position);
        }

        @Override
        public int getCount() {
            //notifyDataSetChanged();
            return sizeList.size();
        }


    }


    private class WizardPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int position) {
            // TODO Auto-generated method stub
          //  Toast.makeText(ShoppingActivity.this, " onPageScrollStateChanged: "+position, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            // TODO Auto-generated method stub
           // Toast.makeText(ShoppingActivity.this, " onPageScrolled: "+position, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onPageSelected(int position) {
           // Toast.makeText(ShoppingActivity.this, " onPageSelected: "+position, Toast.LENGTH_SHORT).show();

//
        }

    }

    public void Images(int position) {
        String url;
        switch (position) {
            case 0:
                url="";
                loadImageRequest(img,url);
                break;
            case 1:
                 url="";
                loadImageRequest(img,url);
                break;
            case 2:
                url="";
                loadImageRequest(img,url);
                break;
            case 3:

                break;
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.ecommerce1_menu, menu);
//        return true;
//    }
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // Get the notifications MenuItem and
//        // its LayerDrawable (layer-list)
//        MenuItem item = menu.findItem(R.id.action_cart);
//        NotificationCountSetClass.setAddToCart(ShoppingActivity.this, item,notificationCountCart);
//        // force the ActionBar to relayout its MenuItems.
//        // onCreateOptionsMenu(Menu) will be called again.
//        invalidateOptionsMenu();
//        return super.onPrepareOptionsMenu(menu);
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
//            case R.id.action_cart:
//                //Toast.makeText(this, "action shopping cart clicked!", Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(ShoppingActivity.this, ShoppingCartActivity.class);
//                startActivity(intent);
//                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.incrementQuantity:
                quantity++;
                //Toast.makeText(this, "incrementQuantity clicked!", Toast.LENGTH_SHORT).show();
                editTextQuatityText.setText(String.valueOf(quantity));
                break;
            case R.id.decrementQuantity:
                if(quantity>1){
                    quantity--;
                }

                //Toast.makeText(this, "decrementQuantity clicked!", Toast.LENGTH_SHORT).show();
                editTextQuatityText.setText(String.valueOf(quantity));
                break;
            case R.id.shopping_btnBuyNow:
               // Toast.makeText(this, "Button Buy Now clicked!", Toast.LENGTH_SHORT).show();

//                Intent intent=new Intent(ShoppingActivity.this, CheckoutActivity.class);
//                intent.putExtra("id",id);
//                intent.putExtra("name",name);
//                intent.putExtra("price",price);
//                intent.putExtra("description",description);
//                intent.putExtra("image",imagesList.get(0));
//                intent.putExtra("quantity",editTextQuatityText.getText().toString());
//                startActivity(intent);

                AddCartModel addCartModel2= new AddCartModel();
                addCartModel2.addCartListModel(new CartModel(id,name,description,price,editTextQuatityText.getText().toString(),imagesList.get(0)));
                MainActivity.notificationCountCart++;
                NotificationCountSetClass.setNotifyCount(MainActivity.notificationCountCart);
                startActivity(new Intent(ShoppingActivity.this, ShoppingCartActivity.class));

                break;
            case R.id.shopping_btnAddToChart:

                //cartList.add(new CartModel(id,name,price,description,editTextQuatityText.getText().toString()));
              //  ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
              //  imageUrlUtils.addCartListImageUri("https://static.pexels.com/photos/68562/pexels-photo-68562-medium.jpeg");

                AddCartModel addCartModel= new AddCartModel();
                addCartModel.addCartListModel(new CartModel(id,name,description,price,editTextQuatityText.getText().toString(),imagesList.get(0)));
                Toast.makeText(this,"Item added to cart.",Toast.LENGTH_SHORT).show();
                MainActivity.notificationCountCart++;
                NotificationCountSetClass.setNotifyCount(MainActivity.notificationCountCart);

                //Toast.makeText(this, "Button Add to Cart clicked!", Toast.LENGTH_SHORT).show();

                break;
            default:
                break;
        }

    }
    private void loadImageRequest(ImageView img, String url) {
        Glide.with(this)
                .load(url)
                .thumbnail(0.01f)
                .centerCrop()
                .into(img);
    }

}
