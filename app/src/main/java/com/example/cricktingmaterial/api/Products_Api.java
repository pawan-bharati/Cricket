package com.example.cricktingmaterial.api;

import com.example.cricktingmaterial.model.Cart;
import com.example.cricktingmaterial.model.Products;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Products_Api {
    @GET("product/getproduct")
    Call<List<Products>> getallProduct();
    @GET("cart/check/{userid}")
    Call<List<Cart>> getcart(@Path("userid") String userid);
}
