package com.example.cricktingmaterial.loginhandler;

import com.example.cricktingmaterial.api.UsersAPI;
import com.example.cricktingmaterial.serverresponse.CartResponse;
import com.example.cricktingmaterial.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class CartHandler {
    boolean isSuccess = false;

    public boolean checkcart(String userid, String productid) {
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<CartResponse> cartcall = usersAPI.checkcart(productid,userid);
        try {
            Response<CartResponse> cartresponse = cartcall.execute();
            if (cartresponse.isSuccessful() &&
                    cartresponse.body().getStatus().equals("addhere"))
            {
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
