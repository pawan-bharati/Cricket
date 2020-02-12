package com.example.cricktingmaterial.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cricktingmaterial.ProductDetail_Activity;
import com.example.cricktingmaterial.R;
import com.example.cricktingmaterial.model.Products;
import com.example.cricktingmaterial.url.Url;
import com.squareup.picasso.Picasso;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_layout, parent, false);
        productholder Productholder = new productholder(view);
        return Productholder;
    }

    @Override
    public void onBindViewHolder(@NonNull productholder holder, int position) {
        final Products products = productsList.get(position);
        holder.tvPrice.setText(products.getPrice());
        holder.tvProductName.setText(products.getName());
        String imgPath = Url.imagepath+products.getImage();

        Toast.makeText(context, ""+imgPath, Toast.LENGTH_SHORT).show();
        Picasso.get()
                .load(imgPath)
                .placeholder(R.drawable.cricketlogo)
                .resize(220,220)
                .centerCrop()
                .into(holder.productImage);

        holder.productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetail_Activity.class);
                intent.putExtra("_id",products.get_id());
                intent.putExtra("image",products.getImage());
                intent.putExtra("name",products.getName());
                intent.putExtra("price",products.getPrice());
                intent.putExtra("description",products.getDescription());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productsList.size();
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
