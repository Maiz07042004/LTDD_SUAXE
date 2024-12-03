package com.example.ltdd_suaxe.API;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApp {
    private static Retrofit retrofit;

  //private static final String BASE_URL = "http://192.168.220.1:3000/api/v1/";


//    private static final String BASE_URL = "http://192.168.1." +
//            "149:3000/api/v1/";

    private static final String BASE_URL = "http://192.168.1.18:3000/api/v1/";

   // private static final String BASE_URL = "http://192.168.1.149:3000/api/v1/";


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
