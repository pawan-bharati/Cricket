package com.example.cricktingmaterial.url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {

   // public static  String base_url="http://10.0.2.2:4000/";
    public static  String base_url="http://192.168.1.105:4000/";

    public static  String imagepath = base_url + "uploads/";
    public static String token="Bearer ";

    public static Retrofit getInstance()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
