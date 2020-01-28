package com.example.cricktingmaterial.api;

import com.example.cricktingmaterial.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Products_Api {
    @GET("product//getproduct")
    Call<List<Products>> getallProduct();
}
