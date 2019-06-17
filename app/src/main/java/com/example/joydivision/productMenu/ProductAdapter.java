package com.example.joydivision.productMenu;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joydivision.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>  {

    List<Product> products;

    private View.OnClickListener mOnItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
         TextView productName,productPrice;
         ImageView imageViewProduct;

         MyViewHolder(View itemView) {
            super(itemView);

            productPrice = itemView.findViewById(R.id.text_view_product_price);
            productName = itemView.findViewById(R.id.text_view_product);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }

    }


    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product, parent, false);

        return new ProductAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.productName.setText(products.get(position).getName());
        holder.productPrice.setText(String.valueOf(products.get(position).getPrice()));
        Picasso.get().load(products.get(position).getImage()).resize(1400,850).into(holder.imageViewProduct);

    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

}
