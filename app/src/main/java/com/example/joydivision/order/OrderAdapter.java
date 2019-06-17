package com.example.joydivision.order;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joydivision.R;
import com.example.joydivision.productMenu.Product;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private View.OnClickListener mOnItemClickListener;
    List<Product>orderList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
       // ImageView imageViewProduct;

        MyViewHolder(View itemView) {
            super(itemView);


            productName = itemView.findViewById(R.id.textViewOrder);
           // imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }

    }


    public OrderAdapter(List<Product> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order, parent, false);

        return new OrderAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyViewHolder holder, int position) {
        String s =orderList.get(position).getName() + "   " + String.valueOf(orderList.get(position).getPrice());
        holder.productName.setText(s);
       // Picasso.get().load(products.get(position).getImage()).resize(1400,850).into(holder.imageViewProduct);

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

}


