package com.example.cricktingmaterial.api;

import com.example.cricktingmaterial.model.Cart;
import com.example.cricktingmaterial.model.Order;
import com.example.cricktingmaterial.model.Users;
import com.example.cricktingmaterial.model.UsersCUD;
import com.example.cricktingmaterial.serverresponse.CartResponse;
import com.example.cricktingmaterial.serverresponse.SignUpResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UsersAPI {

    //registering employees
    @POST("register/register_user")
    Call<Void>registerEmployee(@Body UsersCUD usersCUD);

    //for logging in
    @FormUrlEncoded
    @POST("register/login_user")
    Call<SignUpResponse>checkUser(@Field("email") String email, @Field("password") String password);

    @GET("register/me")
    Call<Users>getUserDetails(@Header("Authorization") String token);

    @POST("order/addorder")
    Call<Void> orderthing(@Body Order order);


    @POST("cart/addcart")
    Call<Void> addcart(@Body Cart cart);

    @FormUrlEncoded
    @POST("cart/checkcart")
    Call<CartResponse> checkcart(@Field("productid") String productid, @Field("userid") String userid);
}
