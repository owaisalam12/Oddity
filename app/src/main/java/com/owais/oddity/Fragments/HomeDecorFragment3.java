package com.owais.oddity.Fragments;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.owais.oddity.API.Responses.ProductsByCategory.Result;
import com.owais.oddity.API.Responses.ProductsByCategory.productsByCategoryResponse;
import com.owais.oddity.API.RetrofitClient;
import com.owais.oddity.Adapters.ProductAdapter;
import com.owais.oddity.Adapters.TestAdapter;
import com.owais.oddity.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */

public class HomeDecorFragment3 extends Fragment {

   final private static int parent=545;
    private Context context;
    List<Result> listing;
   // ProductAdapter productAdapter;
    TestAdapter testAdapter;
    RecyclerView recyclerView;
    AVLoadingIndicatorView avLoadingIndicatorView;
    ShimmerFrameLayout mShimmerViewContainer;
    GridLayoutManager mLayoutManager;
    ProgressBar progressBar;
   // RecyclerView.LayoutManager mLayoutManager;

    private int page_number=0;
    private int item_count=10;
    private boolean isLoading=true;
    private int pastVisibleItems,visibleItemCount,totalItemCount,previous_total=0;
    private int view_threshold=10;
    private int offset_page=0;

    public HomeDecorFragment3() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home_decor, container, false);
        getActivity().setTitle("Home Decoration");
        listing=new ArrayList<>();
        context=getActivity();
        recyclerView = view.findViewById(R.id.recyclerview_homeDecor);
        mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        avLoadingIndicatorView=view.findViewById(R.id.loadingIndicator_homeDecor);
        progressBar=view.findViewById(R.id.progressbar_homeDecor);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_homeDecor);


        startShimmer();
        mShimmerViewContainer.setVisibility(View.VISIBLE);

        getProducts(offset_page,parent);

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                visibleItemCount=mLayoutManager.getChildCount();
//                totalItemCount=mLayoutManager.getItemCount();
//                pastVisibleItems=mLayoutManager.findFirstVisibleItemPosition();
//
//                if(dy>0){
//                    if(isLoading){
//                        if(totalItemCount>previous_total){
//                            isLoading=false;
//                            previous_total=totalItemCount;
//                        }
//                    }
//
//                    if(!isLoading&&(totalItemCount-visibleItemCount)<=(pastVisibleItems+view_threshold)){
//                        offset_page+=10;
//                        performPagination(offset_page);
//                        isLoading=true;
//                    }
//
//                }
//
//            }
//        });



        return view;
    }

    public void performPagination(int offset){
        Call<productsByCategoryResponse> call= RetrofitClient.getInstance().getApi().getProductsByCategory(offset,parent);
        call.enqueue(new Callback<productsByCategoryResponse>() {
            @Override
            public void onResponse(Call<productsByCategoryResponse> call, Response<productsByCategoryResponse> response) {
                productsByCategoryResponse productsByCategoryResponse=response.body();
                //  Log.v("resultResp",productsByCategoryResponse.toString());
                Log.v("result1Pages2",productsByCategoryResponse.getMessage().getTotalPages().toString());
                //totalPage=Integer.parseInt(productsByCategoryResponse.getMessage().getTotalPages());
                Log.v("result1Posts",productsByCategoryResponse.getMessage().getTotalPosts().toString());
                for(Result result:productsByCategoryResponse.getMessage().getResult()){
                 //   itemCount++;
                    Log.v("result12",result.getId().toString());
                    Log.v("result12",result.getName());
                    Log.v("result12",result.getDescription());
                    Log.v("result12",result.getPrice());
//                    Log.v("result12",result.getCategories().toString());
//                    Log.v("result12",result.getImages().toString());
                    //listing.clear();
                  //  listing.add(productsByCategoryResponse);
                }

//                stopShimmer();
//                mShimmerViewContainer.setVisibility(View.GONE);
               // productAdapter.addAllItems(listing2);


                //     productAdapter.notifyDataSetChanged();
                  //productAdapter.addAllItems(listing2);

            }

            @Override
            public void onFailure(Call<productsByCategoryResponse> call, Throwable t) {
                Log.v("result12err",t.getMessage());
                stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);
            }
        });
    }
    public void loadNextDataFromApi(int offset) {
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`

        getProducts(offset,parent);


    }
    private void getProducts(int offset,int category){
        Call<productsByCategoryResponse> call= RetrofitClient.getInstance().getApi().getProductsByCategory(offset,category);
      //  Log.v("resultPro","");
        call.enqueue(new Callback<productsByCategoryResponse>() {
            @Override
            public void onResponse(Call<productsByCategoryResponse> call, Response<productsByCategoryResponse> response) {

                List<Result> results=response.body().getMessage().getResult();
                testAdapter = new TestAdapter(results,context);
                recyclerView.setAdapter(testAdapter);


                stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<productsByCategoryResponse> call, Throwable t) {
                Log.v("result1err",t.getMessage());
//                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
              //   avLoadingIndicatorView.setVisibility(View.GONE);
              //  stopLoading();

                stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);
            }
        });
    }
//    void startLoading(){
//        avLoadingIndicatorView.show();
//        // or avi.smoothToShow();
//    }
//
//    void stopLoading(){
//        avLoadingIndicatorView.hide();
//        // or avi.smoothToHide();
//    }
    void startShimmer(){
        mShimmerViewContainer.startShimmer();
    }

    void stopShimmer(){
        mShimmerViewContainer.stopShimmer();
    }
    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    public abstract class PaginationListener extends RecyclerView.OnScrollListener {

        public static final int PAGE_START = 0;

        @NonNull
        private LinearLayoutManager layoutManager;

        /**
         * Set scrolling threshold here (for now i'm assuming 10 item in one page)
         */
        private static final int PAGE_SIZE = 10;

        /**
         * Supporting only LinearLayoutManager for now.
         */
        public PaginationListener(@NonNull LinearLayoutManager layoutManager) {
            this.layoutManager = layoutManager;
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!isLoading() && !isLastPage()) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        &&  firstVisibleItemPosition >= 0
                        &&  totalItemCount >= PAGE_SIZE) {
                    loadMoreItems();
                }
            }
        }

        protected abstract void loadMoreItems();

        public abstract boolean isLastPage();

        public abstract boolean isLoading();
    }
}


