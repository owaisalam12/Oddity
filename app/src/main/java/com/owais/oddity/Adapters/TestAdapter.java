package com.owais.oddity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.owais.oddity.API.Responses.ProductsByCategory.Category;
import com.owais.oddity.API.Responses.ProductsByCategory.Image;
import com.owais.oddity.API.Responses.ProductsByCategory.Result;
import com.owais.oddity.API.Responses.ProductsByCategory.productsByCategoryResponse;
import com.owais.oddity.Activities.ShoppingActivity;
import com.owais.oddity.R;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder>{

    private List<Result> resultList;
    private Context context;

    ImageLoader imageLoader;


    public TestAdapter(List<Result> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }

    @NonNull
    @Override
    public TestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.MyViewHolder holder, int position) {
        Result result=resultList.get(position);

        Glide.with(context).load(result.getImages().get(0).getSrc()).apply(new RequestOptions().override(250, 250)).into(holder.itemImage);
        //imageLoader.displayImage(image, holder.itemImage);
        holder.itemName.setText(result.getName());
        holder.itemPrice.setText("Rs."+result.getPrice());

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemName, itemPrice;
        ImageView itemImage;
        CardView cardView;
        ArrayList<String> imagesList;
        ArrayList<String> categoriesList;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.textItemName);
            itemPrice = itemView.findViewById(R.id.textItemPrice);
            itemImage=itemView.findViewById(R.id.itemImage);
            cardView=itemView.findViewById(R.id.recyclerview_cardView);
            imagesList=new ArrayList<>();
            categoriesList=new ArrayList<>();
            itemView.setOnClickListener(this);
            cardView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            if(resultList.get(getAdapterPosition())!=null){

                Result product=resultList.get(getAdapterPosition());


                String id = product.getId().toString();
                String name = product.getName();
                String description = product.getDescription();
                String price = product.getPrice();
                List<Category> categories = product.getCategories();
                List<Image> images = product.getImages();
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
    public void addResult(List<Result> results){

        for(Result res : results){
            resultList.add(res);
        }
        notifyDataSetChanged();
    }

}
