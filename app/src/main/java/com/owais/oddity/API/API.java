package com.owais.oddity.API;

import com.owais.oddity.API.Responses.ProductsByCategory.productsByCategoryResponse;
import com.owais.oddity.API.Responses.Categories.categoriesResponse;
import com.owais.oddity.API.Responses.orderCreatedResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("?fx=getProductCategories")
    Call<categoriesResponse> getProductCategories(@Field("parent") int parent);

    @FormUrlEncoded
    @POST("?fx=getProductsByCategory")
    Call<productsByCategoryResponse> getProductsByCategory(@Field("offset") int offset,@Field("category") int category);

    @FormUrlEncoded
    @POST("?fx=createOrder2")
    Call<orderCreatedResponse> createOrder(@Field("first_name") String first_name,
                                           @Field("last_name") String last_name,
                                           @Field("address_1") String address_1,
                                           @Field("city") String city,
                                           @Field("country") String country,
                                           @Field("state") String state,
                                           @Field("email") String email,
                                           @Field("phone") String phone,
                                           @Field("payment_method") String payment_method,
                                           @Field("product_id") int product_id,
                                           @Field("quantity") int quantity
    );

    @FormUrlEncoded
    @POST("?fx=createOrder")
    Call<orderCreatedResponse> createOrder2(@Field("first_name") String first_name,
                                           @Field("last_name") String last_name,
                                           @Field("address_1") String address_1,
                                           @Field("city") String city,
                                           @Field("country") String country,
                                           @Field("state") String state,
                                           @Field("email") String email,
                                           @Field("phone") String phone,
                                            @Field("customer_note") String customer_note,
                                            @Field("payment_method_id") String payment_method_id,
                                           @Field("payment_method") String payment_method,
                                            @Field("product_id[]") List<Integer> product_id,
                                           @Field("quantity[]") List<Integer> quantity
    );
}
