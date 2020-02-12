package com.example.cricketwear;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UsersAPI {



    //for logging in
    @FormUrlEncoded
    @POST("register/login_user")
    Call<SignUpResponse>checkUser(@Field("email") String email, @Field("password") String password);


}
