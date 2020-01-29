package com.example.cricktingmaterial.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cricktingmaterial.R;
import com.example.cricktingmaterial.adapter.Product_Adapter;
import com.example.cricktingmaterial.api.Products_Api;
import com.example.cricktingmaterial.model.Products;
import com.example.cricktingmaterial.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerVieww;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerVieww=root.findViewById(R.id.res1);
        getProduct();
        
        return root;
    }
    public void getProduct(){
        Products_Api retrofitProducts_Api = Url.getInstance().create(Products_Api.class);
      Call<List<Products>> ProductsCall = retrofitProducts_Api.getallProduct();
        ProductsCall.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                Product_Adapter recyclerviewAdapter = new Product_Adapter(response.body(), getActivity());
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
                recyclerVieww.setLayoutManager(layoutManager);
                recyclerVieww.setHasFixedSize(true);
                recyclerVieww.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });

    }
}