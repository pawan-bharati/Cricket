package com.example.cricktingmaterial.loginhandler;

import com.example.cricktingmaterial.api.UsersAPI;
import com.example.cricktingmaterial.serverresponse.SignUpResponse;
import com.example.cricktingmaterial.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginHandler {

    boolean isSuccess = false;

    public boolean checkUser(String email, String password) {
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);

        Call<SignUpResponse> usersCall = usersAPI.checkUser(email, password);

        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {
                Url.token += loginResponse.body().getToken();

                isSuccess = true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

}



