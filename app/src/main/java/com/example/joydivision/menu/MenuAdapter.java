package com.example.joydivision.menu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joydivision.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private List<ProductType> productTypesList;
    //private Context context;
    private View.OnClickListener mOnItemClickListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView productTypeName;
        public ImageView imageView;



        public MyViewHolder(View itemView) {
            super(itemView);
            productTypeName = itemView.findViewById(R.id.text_view_product_type);
            imageView = itemView.findViewById(R.id.imageViewProductType);


            //TODO: Step 3 of 4: setTag() as current view holder along with
            // setOnClickListener() as your local View.OnClickListener variable.
            // You can set the same mOnItemClickListener on multiple views if required
            // and later differentiate those clicks using view's id.
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    public MenuAdapter(Context context, List<ProductType>productTypesList) {
        this.productTypesList = productTypesList;
        this.inflater=LayoutInflater.from(context);
        //this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.row_menu, parent, false);
        MenuAdapter.MyViewHolder vHolder = new MenuAdapter.MyViewHolder(itemView);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.productTypeName.setText(productTypesList.get(position).getName());
        Picasso.get().load(productTypesList.get(position).getImage()).resize(1400,850).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return productTypesList.size();
    }

    //TODO: Step 2 of 4: Assign itemClickListener to your local View.OnClickListener variable
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }


}

