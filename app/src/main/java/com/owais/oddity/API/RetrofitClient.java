package com.owais.oddity.API;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;

import com.ncornette.cache.OkCacheControl;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class RetrofitClient {
    //private static final String BASE_URL="http://192.168.10.7/oddityAPI/API2.php/";
    private static final String BASE_URL="https://www.oddity.pk/API/API.php/";
    //private static final String BASE_URL="http://www.mocky.io/v2/";

    //singleton instance
    private static RetrofitClient mInstance;

    private Retrofit retrofit;

//    Context context;
//
//    int cacheSize = 10 * 1024 * 1024; // 10 MB
//    Cache cache = new Cache(context.getCacheDir(), cacheSize);


    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();


//    OkHttpClient okHttpClient2 = new OkHttpClient.Builder()
//            // .addInterceptor(provideHttpLoggingInterceptor()) // For HTTP request & Response data logging
//            .addInterceptor(offlineInterceptor)
//            .addNetworkInterceptor(onlineInterceptor)
//            .cache(cache)
//            .build();
//
//    static Interceptor onlineInterceptor = new Interceptor() {
//        @Override
//        public okhttp3.Response intercept(Chain chain) throws IOException {
//            okhttp3.Response response = chain.proceed(chain.request());
//            int maxAge = 60; // read from cache for 60 seconds even if there is internet connection
//            return response.newBuilder()
//                    .header("Cache-Control", "public, max-age=" + maxAge)
//                    .removeHeader("Pragma")
//                    .build();
//        }
//    };
//    static Interceptor offlineInterceptor= new Interceptor() {
//        @Override
//        public okhttp3.Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            if (!isInternetAvailable()) {
//                int maxStale = 60 * 60 * 24 * 30; // Offline cache available for 30 days
//                request = request.newBuilder()
//                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                        .removeHeader("Pragma")
//                        .build();
//            }
//            return chain.proceed(request);
//        }
//    };
//
//    private static boolean isInternetAvailable() {
//      return false;
//    }


    //synchronized method to get the singleton instance of retrofitclient class
    public static synchronized RetrofitClient getInstance() { //it is synchronized because we want single instance only.
        if (mInstance == null) {
            mInstance = new RetrofitClient();

        }
        return mInstance;
    }
    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    //method to get Api
    public API getApi() {
        return retrofit.create(API.class);
    }
}
