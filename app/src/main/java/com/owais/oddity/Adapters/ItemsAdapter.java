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
import androidx.recyclerview.widget.RecyclerView;

import com.owais.oddity.API.Responses.Categories.categoriesResponse;
import com.owais.oddity.Fragments.HomeLivingFragment;
import com.owais.oddity.R;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyHolder> implements View.OnClickListener{

    List<categoriesResponse> list;
    private Context context;
    private HomeLivingFragment fragment;
    public ItemsAdapter(List<categoriesResponse> list) {
        this.list = list;
    }

    public ItemsAdapter(List<categoriesResponse> list, Context context, HomeLivingFragment fragment) {
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
    public ItemsAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ItemsAdapter.MyHolder myHolder = new ItemsAdapter.MyHolder(view);
        context = parent.getContext();
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.MyHolder holder, int position) {
        categoriesResponse product = list.get(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // the view being shared
            holder.itemImage.setTransitionName("transition" + position);
        }
        if(product.getMessage()!=null ) {
            String id = product.getMessage().get(position).getId().toString();
            String name = product.getMessage().get(position).getName();
            String count = product.getMessage().get(position).getCount().toString();
            String parent = product.getMessage().get(position).getParent().toString();

//            String content2 = Jsoup.parse(content).text();

            Log.v("catg",id);
            Log.v("catg",name);
            Log.v("catg",count);
            Log.v("catg",parent);

//            holder.Title.setText(title);
//            holder.Date.setText(date2);
        }
        // BlurImage.withContext(context).blurFromUri(BASE_URL+image1)
        //       .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemName, itemPrice;
        ImageView itemImage;
        //JobDetailsFragment jobDetailsFragment;
        public MyHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.textItemName);
            itemPrice = itemView.findViewById(R.id.textItemPrice);
            itemImage=itemView.findViewById(R.id.itemImage);
            itemView.setOnClickListener(this);
            //jobDetailsFragment=new JobDetailsFragment();
        }



        @Override
        public void onClick(View v) {


//            Log.d("Recycler", "onClick " + getAdapterPosition() + " " + list.get(getAdapterPosition()).getDeals().get(getAdapterPosition()).getDealsName());
            if(list.get(getAdapterPosition()).getMessage()!=null){
//                String content=list.get(getAdapterPosition()).getContent().getRendered();
//                String id=list.get(getAdapterPosition()).getId().toString();
//
//                String content2= Jsoup.parse(content).text();

                //Log.d("Recycler", "onClick " + getAdapterPosition() + " ID: " +id+" Content: "+content2);
                Log.d("Recycler", "onClick " + getAdapterPosition());

//                fragment.openMovieDetailFragment(getAdapterPosition(), v.findViewById(R.id.jobImgUrl));


            }
        }
    }
}
