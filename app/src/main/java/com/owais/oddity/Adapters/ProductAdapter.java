package com.owais.oddity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.owais.oddity.API.Responses.ProductsByCategory.Category;
import com.owais.oddity.API.Responses.ProductsByCategory.Image;
import com.owais.oddity.API.Responses.ProductsByCategory.productsByCategoryResponse;
import com.owais.oddity.Activities.ShoppingActivity;
import com.owais.oddity.Fragments.HomeDecorFragment;
import com.owais.oddity.R;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyHolder> implements View.OnClickListener {
    List<productsByCategoryResponse> list;
    private Context context;
    private HomeDecorFragment fragment2;
    private Fragment fragment;
    ImageLoader imageLoader;

    ArrayList<String> imagesList;
    ArrayList<String> categoriesList;

    public ProductAdapter(List<productsByCategoryResponse> list) {
        this.list = list;
    }

    public ProductAdapter(List<productsByCategoryResponse> list, Context context, Fragment fragment) {
        this.list = list;
        this.context = context;
        this.fragment = fragment;
    }
    public ProductAdapter(List<productsByCategoryResponse> list, Context context, Fragment fragment,ImageLoader imageLoader) {
    this.list = list;
    this.context = context;
    this.fragment = fragment;
    this.imageLoader = imageLoader;
}


    @Override
    public void onClick(View view) {
        Log.v("Recycler", "onClick " + view.toString());
    }

    @NonNull
    @Override
    public ProductAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ProductAdapter.MyHolder myHolder = new ProductAdapter.MyHolder(view);
        context = parent.getContext();

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        productsByCategoryResponse product = list.get(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // the view being shared
            holder.itemImage.setTransitionName("transition" + position);
        }
        if(product.getMessage()!=null ) {
            String id = product.getMessage().getResult().get(position).getId().toString();
            String name = product.getMessage().getResult().get(position).getName();
            String description = product.getMessage().getResult().get(position).getDescription();
            String price = product.getMessage().getResult().get(position).getPrice();
            String categories = product.getMessage().getResult().get(position).getCategories().toArray().toString();
            String images = product.getMessage().getResult().get(position).getImages().toArray().toString();
            String image=product.getMessage().getResult().get(position).getImages().get(0).getSrc();

//            String content2 = Jsoup.parse(content).text();

//            Log.v("catg",id);
//            Log.v("catg",name);
//            Log.v("catg",description);
//            Log.v("catg",price);
//            Log.v("catg",categories);
//            Log.v("catg",image);
          //  imageLoader = getActivity().ImageLoader.getInstance();
            Glide.with(context).load(image).apply(new RequestOptions().override(250, 250)).into(holder.itemImage);
            //imageLoader.displayImage(image, holder.itemImage);
            holder.itemName.setText(name);
            holder.itemPrice.setText("Rs."+price);

        }
        // BlurImage.withContext(context).blurFromUri(BASE_URL+image1)
        //       .into(holder.image);

    }
    public void addAllItems(List<productsByCategoryResponse> itemList)
    {
//        for(productsByCategoryResponse itemRow: itemList)
//        {
//            list.add(itemRow);
//        }
        list.addAll(itemList);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemName, itemPrice;
        ImageView itemImage;
        CardView cardView;
        //JobDetailsFragment jobDetailsFragment;
        public MyHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.textItemName);
            itemPrice = itemView.findViewById(R.id.textItemPrice);
            itemImage=itemView.findViewById(R.id.itemImage);
            cardView=itemView.findViewById(R.id.recyclerview_cardView);
            imagesList=new ArrayList<>();
            categoriesList=new ArrayList<>();
            itemView.setOnClickListener(this);
            cardView.setOnClickListener(this);

            //jobDetailsFragment=new JobDetailsFragment();
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(context, "You clicked on "+getAdapterPosition() , Toast.LENGTH_SHORT).show();
          //  Log.v("onclick",""+getAdapterPosition());
            if(list.get(getAdapterPosition()).getSuccess()!=0){
                productsByCategoryResponse product=list.get(getAdapterPosition());
                String id = product.getMessage().getResult().get(getAdapterPosition()).getId().toString();
                String name = product.getMessage().getResult().get(getAdapterPosition()).getName();
                String description = product.getMessage().getResult().get(getAdapterPosition()).getDescription();
                String price = product.getMessage().getResult().get(getAdapterPosition()).getPrice();
                List<Category> categories = product.getMessage().getResult().get(getAdapterPosition()).getCategories();
                List<Image> images = product.getMessage().getResult().get(getAdapterPosition()).getImages();
                String description2 = Jsoup.parse(description).text();
                for(Image image:images){
                    imagesList.add(image.getSrc());
                }
                for(Category category:categories){
                    String catgr = Jsoup.parse(category.getName()).text();
                    categoriesList.add(catgr);
                }

                Log.v("onclickid",id);
                Log.v("onclickname",name);
                Log.v("onclickdescription",description2);
                Log.v("onclickprice",price);
                Log.v("onclickcategoriesList",categoriesList.toString());
                Log.v("onclickimagesList",imagesList.toString());
                Log.v("onclickimagesCount",""+imagesList.size());

                Intent intent = new Intent(v.getContext(), ShoppingActivity.class);
                //Bundle extras = intent.getExtras();


//                    extras.putString("id",id);
//                    extras.putString("name",name);
//                    extras.putString("description",description2);
//                    extras.putString("price",price);
//                    extras.putStringArrayList("categoriesList",categoriesList);
//                    extras.putStringArrayList("imagesList",imagesList);

                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("description",description2);
                intent.putExtra("price",price);
                intent.putExtra("categoriesList",categoriesList);
                intent.putExtra("imagesList",imagesList);
                intent.putExtra("imagesCount",imagesList.size());


                    v.getContext().startActivity(intent);

                imagesList.clear();
                categoriesList.clear();

            }

        }
    }
}
