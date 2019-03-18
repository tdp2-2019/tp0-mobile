package bookazon.tdpii.fiuba.com.bookazon.services;

import android.content.Context;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //private final static String API_BASE_URL = "http://127.0.0.1:8080";
    private final static String API_BASE_URL = "https://bookazon.herokuapp.com";


    private static ApiClient instance;
    private Retrofit retrofit;



    private ApiClient(Context applicationContext) {
        buildRetrofit(applicationContext);
    }

    public synchronized static ApiClient getInstance(Context applicationContext) {
        if (instance == null)
            instance = new ApiClient(applicationContext);
        return instance;
    }

    private void buildRetrofit(Context applicationContext) {


        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(applicationContext.getCacheDir(), cacheSize);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().cache(cache);
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder
                .client(httpClient.build())
                .build();
    }

    public CoreAPI getCoreAPI() {
        return retrofit.create(CoreAPI.class);
    }
}