package com.owais.oddity.Fragments;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
public class WomenFashionFragment extends Fragment {

    final private static int parent=541;
    private Context context;
    List<productsByCategoryResponse> listing;
    ProductAdapter productAdapter;
    RecyclerView recyclerView;
//    AVLoadingIndicatorView avLoadingIndicatorView;
    ShimmerFrameLayout mShimmerViewContainer;

    public WomenFashionFragment() {
        // Required empty public constructor
    }
    ProgressBar progressBar;
    TestAdapter testAdapter;
    GridLayoutManager mLayoutManager;

    private int page_number=0;
    private int item_count=10;
    private boolean isLoading=true;
    private int pastVisibleItems,visibleItemCount,totalItemCount,previous_total=0;
    private int view_threshold=10;
    private int offset_page=0;
    LinearLayout layoutNoInternet;
    RelativeLayout layoutHomeLiving;
    private Button buttonTryAgain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_women_fashion, container, false);
        getActivity().setTitle("Women Fashion");
        listing=new ArrayList<>();
        progressBar=view.findViewById(R.id.progressbar_womenFashion);
        recyclerView = view.findViewById(R.id.recyclerview_womenFashion);
        mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        context=getActivity();
//        avLoadingIndicatorView=view.findViewById(R.id.loadingIndicator_womenFashion);
        productAdapter = new ProductAdapter(listing,context,this);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_womenFashion);
        layoutNoInternet =  view.findViewById(R.id.layout_noconnection_womenFashion);
        layoutHomeLiving =  view.findViewById(R.id.layout_womenFashion);
        buttonTryAgain=view.findViewById(R.id.noConnection_tryAgainBtn);
//        avLoadingIndicatorView.setVisibility(View.VISIBLE);
//        startLoading();

        buttonTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutNoInternet.setVisibility(View.GONE);
                layoutHomeLiving.setVisibility(View.VISIBLE);
                mShimmerViewContainer.setVisibility(View.VISIBLE);
                startShimmer();

                if(!isConnected(context)){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Start main activity
                            stopShimmer();
                            mShimmerViewContainer.setVisibility(View.GONE);
                            layoutNoInternet.setVisibility(View.VISIBLE);
                            // Toast.makeText(context, "No internet", Toast.LENGTH_SHORT).show();
                            // hideLayout();
                        }
                    }, 1500);

                }else{
                    showLayout();
                    getProducts(page_number,parent);
                    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                        @Override
                        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);

                            visibleItemCount=mLayoutManager.getChildCount();
                            totalItemCount=mLayoutManager.getItemCount();
                            pastVisibleItems=mLayoutManager.findFirstVisibleItemPosition();

                            if(dy>0){
                                if(isLoading){
                                    if(totalItemCount>previous_total){
                                        isLoading=false;
                                        previous_total=totalItemCount;
                                    }
                                }
                                if(!isLoading&&(totalItemCount-visibleItemCount)<=(pastVisibleItems+view_threshold)){
                                    page_number=page_number+10;
                                    performPagination();
                                    isLoading=true;
                                }
                            }
                        }
                    });

                }
            }
        });

        if(!isConnected(context)){
            startShimmer();
            mShimmerViewContainer.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Start main activity
                    stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
//                    hideLayout();
                    layoutNoInternet.setVisibility(View.VISIBLE);

                }
            }, 1250);

        }else{
            showLayout();
            getProducts(page_number,parent);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    visibleItemCount=mLayoutManager.getChildCount();
                    totalItemCount=mLayoutManager.getItemCount();
                    pastVisibleItems=mLayoutManager.findFirstVisibleItemPosition();

                    if(dy>0){
                        if(isLoading){
                            if(totalItemCount>previous_total){
                                isLoading=false;
                                previous_total=totalItemCount;
                            }
                        }
                        if(!isLoading&&(totalItemCount-visibleItemCount)<=(pastVisibleItems+view_threshold)){
                            page_number=page_number+10;
                            performPagination();
                            isLoading=true;
                        }
                    }
                }
            });

        }
        return view;
    }
    private void getProducts(int offset,int category){
        startShimmer();
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        Call<productsByCategoryResponse> call= RetrofitClient.getInstance().getApi().getProductsByCategory(offset,category);
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

                stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);
            }
        });
    }
    public void performPagination(){
        progressBar.setVisibility(View.VISIBLE);
        Call<productsByCategoryResponse> call= RetrofitClient.getInstance().getApi().getProductsByCategory(page_number,parent);
        call.enqueue(new Callback<productsByCategoryResponse>() {
            @Override
            public void onResponse(Call<productsByCategoryResponse> call, Response<productsByCategoryResponse> response) {

                if(response.body().getSuccess()!=0){
                    List<Result> results=response.body().getMessage().getResult();

                    testAdapter.addResult(results);
                    Toast.makeText(context, "Page "+((page_number/10)+1)+" is loaded..", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(context, "No more data ", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<productsByCategoryResponse> call, Throwable t) {
                Log.v("result1err",t.getMessage());
                progressBar.setVisibility(View.GONE);
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

    public final static boolean isConnected( Context context )
    {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService( Context.CONNECTIVITY_SERVICE );
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
    private void showLayout(){
        layoutNoInternet.setVisibility(View.GONE);
        layoutHomeLiving.setVisibility(View.VISIBLE);
    }
    private void hideLayout(){
        layoutHomeLiving.setVisibility(View.GONE);
        layoutNoInternet.setVisibility(View.VISIBLE);

    }
}
