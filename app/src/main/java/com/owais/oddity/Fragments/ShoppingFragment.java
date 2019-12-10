package com.owais.oddity.Fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.github.chrisbanes.photoview.PhotoView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.owais.oddity.Activities.ShoppingActivity;
import com.owais.oddity.PrefManager;
import com.owais.oddity.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends Fragment  {

    int wizard_page_position;
    ImageView img;
    private String id,name,description,price;
    ArrayList<String> imagesList;
    ArrayList<String> categoriesList;
    private PrefManager prefManager;
    public ShoppingFragment(int position) {
        this.wizard_page_position = position;
    }
    public ShoppingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int layout_id = R.layout.shopping_fragment;
        View view = inflater.inflate(layout_id, container, false);

        imagesList=new ArrayList<>();
        categoriesList=new ArrayList<>();
        //String url = BuildConfig.IMAGE_URL + "ecommerce/style-13/Ecommerce-13.jpg";
//        String url="https://www.oddity.pk/wp-content/uploads/2019/06/6PCS-DIY-Grid-Drawer-Divider-Storage-Partition-Organizer-01.jpg";
         img =  view.findViewById(R.id.imagePage1);
         img.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 // Toast.makeText(getActivity(), "Clicked: "+imagesList.get(wizard_page_position), Toast.LENGTH_SHORT).show();
//

                 AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                 mBuilder.setView(mView);
                 final PhotoView photoView = mView.findViewById(R.id.imageView);
                // ImageView imgAlert = mView.findViewById(R.id.imageView);
                // photoView.setImageResource(R.drawable.men_fashipn);
//                 photoView.setImageURI(Uri.parse(imagesList.get(wizard_page_position)));
                 ImageLoader imageLoader = ImageLoader.getInstance();
                 imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
                // imageLoader.displayImage(imagesList.get(wizard_page_position), photoView);
                // Glide.with(getContext()).load(imagesList.get(wizard_page_position)).into(imgAlert);



                 Log.v("glide",imagesList.get(wizard_page_position));

                 final AVLoadingIndicatorView avLoadingIndicatorView=mView.findViewById(R.id.loadingIndicator_alert);

                 DisplayImageOptions options = new DisplayImageOptions.Builder()
                         .resetViewBeforeLoading(false) // default
                         .cacheInMemory(true) // default
                         .cacheOnDisk(true) // default
                         .build();

                 imageLoader.displayImage(imagesList.get(wizard_page_position), photoView, options, new ImageLoadingListener() {
                     @Override
                     public void onLoadingStarted(String imageUri, View view) {
                         avLoadingIndicatorView.setVisibility(View.VISIBLE);
                         avLoadingIndicatorView.show();

                     }
                     @Override
                     public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                         avLoadingIndicatorView.setVisibility(View.GONE);
                         avLoadingIndicatorView.hide();



                     }
                     @Override
                     public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                         avLoadingIndicatorView.setVisibility(View.GONE);
                         avLoadingIndicatorView.show();
                         photoView.setImageBitmap(loadedImage);

                     }
                     @Override
                     public void onLoadingCancelled(String imageUri, View view) {
                         avLoadingIndicatorView.setVisibility(View.GONE);
                         avLoadingIndicatorView.hide();

                     }
                 });

                 AlertDialog mDialog = mBuilder.create();
                 mDialog.show();
                 //mDialog.getWindow().setLayout(700, 900);

             }
         });
//         if(getArguments()!=null){
//             id=getArguments().getString("id");
//             name=getArguments().getString("name");
//             description=getArguments().getString("description");
//             price=getArguments().getString("price");
//             imagesList=getArguments().getStringArrayList("imagesList");
//             categoriesList=getArguments().getStringArrayList("categoriesList");
//
//             Log.v("shopping",id);
//             Log.v("shopping",name);
//             Log.v("shopping",description);
//             Log.v("shopping",price);
//             Log.v("shopping",categoriesList.toString());
//             Log.v("shopping",imagesList.toString());
//
//         }
        if(getActivity().getIntent().getExtras()!=null){
            id=getActivity().getIntent().getExtras().getString("id");
            name=getActivity().getIntent().getExtras().getString("name");
            description=getActivity().getIntent().getExtras().getString("description");
            price=getActivity().getIntent().getExtras().getString("price");
            imagesList=getActivity().getIntent().getExtras().getStringArrayList("imagesList");
            categoriesList=getActivity().getIntent().getExtras().getStringArrayList("categoriesList");

            Log.v("shopping",id);
            Log.v("shopping",name);
            Log.v("shopping",description);
            Log.v("shopping",price);
            Log.v("shopping",categoriesList.toString());
            Log.v("shopping",imagesList.toString());

           // Toast.makeText(getActivity(), ""+id, Toast.LENGTH_SHORT).show();

        }
        setImages(wizard_page_position,img,Integer.valueOf(id));
        return view;
    }

    private void loadImageRequest(ImageView img, String url) {
        Glide.with(getActivity())
                .load(url)
                .thumbnail(0.01f)
                .fitCenter()
                .into(img);

    }
    public void setImages(int position,ImageView img,int id) {
        String url;
//        switch (position) {
//            case 0:
//                url="https://www.oddity.pk/wp-content/uploads/2019/06/Extensible-Rack-Organizer-for-Kitchen-Bathroom-Bedroom-Living-room-03.jpg";
////                url=imagesList.get(0);
//                loadImageRequest(img,url);
//                break;
//            case 1:
//                url="https://www.oddity.pk/wp-content/uploads/2019/06/28-hole-hanger-02.jpg";
////                url=imagesList.get(1);
//                loadImageRequest(img,url);
//                break;
//            case 2:
//                url="https://www.oddity.pk/wp-content/uploads/2019/06/Creative-Kitchen-Hanging-Trash-Door-Hanging-Plastic-Waste-Bins-04-1.jpg";
////                url=imagesList.get(2);
//                loadImageRequest(img,url);
//                break;
//            case 3:
//                url="https://www.oddity.pk/wp-content/uploads/2019/06/Ice-Cube-Ball-Tray-Round-Maker-Mold-Sphere-Mould-04.jpg";
////                url=imagesList.get(3);
//                loadImageRequest(img,url);
//                break;
//        }
        for(int i=0;i<imagesList.size();i++){
            url=imagesList.get(position);
            Log.v("imgs",url);
            img.setId(id);
            loadImageRequest(img,url);
        }
//        int index = 0;
//        for(String s:imagesList){
//            Log.v("imgg",s);
//            url=imagesList.get(index);
//           loadImageRequest(img,url);
//           index++;
//        }
    }


}
