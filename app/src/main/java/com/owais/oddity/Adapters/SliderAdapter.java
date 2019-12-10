package com.owais.oddity.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.owais.oddity.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter  extends
        SliderViewAdapter<SliderAdapter.SliderAdapterVH>{

    private Context context;
    private int mCount;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public void setCount(int count) {
        this.mCount = count;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });


        switch (position) {
            case 0:
                viewHolder.textViewDescription.setText("");
                viewHolder.textViewDescription.setTextSize(18);
                viewHolder.textViewDescription.setTextColor(Color.WHITE);
                viewHolder.imageGifContainer.setVisibility(View.GONE);
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.banner1)
                        //.load("https://oddity.pk/wp-content/uploads/2019/02/banner-3.jpg")
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                viewHolder.textViewDescription.setText("");
                viewHolder.textViewDescription.setTextSize(18);
                viewHolder.textViewDescription.setTextColor(Color.WHITE);
                viewHolder.imageGifContainer.setVisibility(View.GONE);
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.banner2)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
//            case 2:
//                viewHolder.textViewDescription.setText("");
//                viewHolder.textViewDescription.setTextSize(18);
//                viewHolder.textViewDescription.setTextColor(Color.WHITE);
//                viewHolder.imageGifContainer.setVisibility(View.GONE);
//                Glide.with(viewHolder.itemView)
//                        .load("http://www.auhda.com/wp-content/uploads/2019/02/Untitled-1-1.jpg")
//                        .fitCenter()
//                        .into(viewHolder.imageViewBackground);
//                break;
            default:

                break;

        }

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mCount;
    }



    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
