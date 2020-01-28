package com.example.cricktingmaterial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cricktingmaterial.R;
import com.example.cricktingmaterial.model.Products;

import java.util.List;

public class Product_Adapter extends RecyclerView.Adapter<Product_Adapter.productholder> {
    List<Products> productsList;
    Context context;

    public Product_Adapter(List<Products> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    @NonNull
    @Override
    public productholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_product, parent, false);
        productholder Productholder = new productholder(view);
        return Productholder;
    }

    @Override
    public void onBindViewHolder(@NonNull productholder holder, int position) {
        Products products = productsList.get(position);
        holder.tvPrice.setText(products.getPrice());
        holder.tvProductName.setText(products.getName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class productholder extends RecyclerView.ViewHolder {
        TextView tvPrice, tvProductName;
        ImageView productImage;

        public productholder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            productImage = itemView.findViewById(R.id.productImage);


        }
    }
}
