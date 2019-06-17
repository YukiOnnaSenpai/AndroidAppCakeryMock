package com.example.joydivision.order;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joydivision.R;
import com.example.joydivision.productMenu.Product;
import com.example.joydivision.productMenu.ProductActivity;
import com.example.joydivision.productMenu.ProductAdapter;
import com.ornach.nobobutton.NoboButton;

import static com.example.joydivision.menu.MenuActivityDrawer.orderList;

public class OrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OrderAdapter orderAdapter;
    TextView priceText;
    NoboButton btnOrder;
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            String removedProduct=orderList.get(position).getName();
            orderList.remove(orderList.get(position));
            orderAdapter.notifyDataSetChanged();
            Toast.makeText(OrderActivity.this, "Obrisali ste iz korpe: " + removedProduct, Toast.LENGTH_SHORT).show();
            priceText.setText((calculatePrice()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        priceText = findViewById(R.id.price);
        btnOrder = findViewById(R.id.buttonOrder);
        priceText.setText(String.valueOf(calculatePrice()));



        recyclerView = findViewById(R.id.recycleViewOrder);
        orderAdapter = new OrderAdapter(orderList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderAdapter);
        //TODO: Step 1 of 4: Create and set OnItemClickListener to the adapter.
        orderAdapter.setOnItemClickListener(onItemClickListener);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orderList.size()==0){

                    Toast.makeText(OrderActivity.this, "You haven't ordered yet!", Toast.LENGTH_SHORT).show();
                }else {
                    new AlertDialog.Builder(OrderActivity.this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Price is " + priceText.getText())
                            .setMessage("Do you accept the order?")
                            .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                    Toast.makeText(OrderActivity.this, "Order successfully sent!", Toast.LENGTH_SHORT).show();
                                    finish();
                                    orderList.clear();
                                }
                            })
                            .setPositiveButton("No", null)
                            .show();

                }






            }

        });

    }

    private String calculatePrice(){

        int price = 0;
        for(Product product : orderList){

             price += product.getPrice();
        }
        String s = String.valueOf(price);

        return s + " rsd.";
    }
}
