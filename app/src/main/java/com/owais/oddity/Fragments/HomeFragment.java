package com.owais.oddity.Fragments;


import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.owais.oddity.Adapters.SliderAdapter;
import com.owais.oddity.R;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {


    public HomeFragment() {
        // Required empty public constructor
    }
    SliderView sliderView;
    CardView cardViewHomeLiving,cardViewHomeDecor,cardViewMenFashion,cardViewOddityDeals,cardViewOddityHomemade,cardViewWomenFashion;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        cardViewHomeLiving = view.findViewById(R.id.card_homeliving);
        cardViewHomeDecor = view.findViewById(R.id.card_homedecoration);
        cardViewMenFashion = view.findViewById(R.id.card_menfashion);
        cardViewOddityDeals = view.findViewById(R.id.card_oddityDeals);
        cardViewWomenFashion = view.findViewById(R.id.card_womenfashion);
        cardViewOddityHomemade = view.findViewById(R.id.card_homemade);



      //  sliderView = view.findViewById(R.id.imageSlider);
        cardViewHomeLiving.setOnClickListener(this);
        cardViewHomeDecor.setOnClickListener(this);
        cardViewMenFashion.setOnClickListener(this);
        cardViewOddityDeals.setOnClickListener(this);
        cardViewWomenFashion.setOnClickListener(this);
        cardViewOddityHomemade.setOnClickListener(this);
        getActivity().setTitle("");

        final SliderAdapter adapter = new SliderAdapter(getContext());
        //adapter.setCount(2);
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

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.card_homeliving:
                goToFragment(new HomeLivingFragment());
                break;
            case R.id.card_homedecoration:
                goToFragment(new HomeDecorFragment());

                break;
            case R.id.card_menfashion:
                goToFragment(new MenFashionFragment());

                break;
            case R.id.card_oddityDeals:
                goToFragment(new OddityDealsFragment());

                break;
            case R.id.card_womenfashion:
                goToFragment(new WomenFashionFragment());

                break;
            case R.id.card_homemade:
                goToFragment(new HomeDecorFragment());

                break;


        }
    }
    private void goToFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, fragment).addToBackStack(null).commit();
    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
//    }
}
