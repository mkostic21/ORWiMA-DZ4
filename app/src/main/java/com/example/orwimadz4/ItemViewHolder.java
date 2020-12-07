package com.example.orwimadz4;

import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Locale;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView tvDescription;
    private TextView tvRating;
    private TextView tvPrice;
    private TextView tvName;
    private ImageView imgProduct;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        initUI();
    }

    private void initUI() {
        tvName = itemView.findViewById(R.id.tvName);
        tvPrice = itemView.findViewById(R.id.tvPrice);
        tvRating = itemView.findViewById(R.id.tvRating);
        tvDescription = itemView.findViewById(R.id.tvDescription);
        imgProduct = itemView.findViewById(R.id.imgProduct);

    }


    public void displayProduct(Product product) {
        Resources res = itemView.getResources();

        tvName.setText(String.format(res.getString(R.string.product_name), product.getName()));
        tvPrice.setText(String.format(res.getString(R.string.product_price), String.format(Locale.getDefault(), "%.2f", product.getPrice())));
        tvRating.setText(String.format(res.getString(R.string.product_rating), String.format(Locale.getDefault(), "%.1f", product.getRating())));
        tvDescription.setText(product.getDescription());
        Glide.with(itemView)
                .load(product.getImage_link())
                .into(imgProduct);
    }




}
