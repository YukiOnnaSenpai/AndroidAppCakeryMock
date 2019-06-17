package com.example.joydivision.productMenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.joydivision.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.joydivision.menu.MenuActivityDrawer.orderList;

public class ProductActivity extends AppCompatActivity {

    private List<Product> allProducts = new ArrayList<>();
    private List<Product> shownProducts = new ArrayList<>();

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
           //Product product = allProducts.get(position);
            orderList.add(shownProducts.get(position));
            Toast.makeText(ProductActivity.this, "You have ordered: " + shownProducts.get(position).getName() , Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view_product);

        fillProducts();
        setProducts();

        ProductAdapter productAdapter = new ProductAdapter(shownProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);
        //TODO: Step 1 of 4: Create and set OnItemClickListener to the adapter.
        productAdapter.setOnItemClickListener(onItemClickListener);

    }
    private void fillProducts(){

        Product t1 = new Product(0,"Chocolate cake","https://food-images.files.bbci.co.uk/food/recipes/salted_dark_chocolate_16338_16x9.jpg",
                600);
        allProducts.add(t1);
        Product t2 = new Product(0,"Strawberry-cream cake","https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/strawberry-short-cake-1528726234.jpg",
                400);
        allProducts.add(t2);
        Product t3 = new Product(0,"Caramel cake","https://realfood.tesco.com/media/images/TescoWinter40-6PopcornCake3-1400x919-182ce1e2-138c-43c0-b4e2-38b3c3ba2d17-0-1400x919.jpg",
                200);
        allProducts.add(t3);
        Product t4 = new Product(0,"Tiramisu cake","https://timeincsecure-a.akamaihd.net/rtmp_uds/429048911/201812/1047/429048911_5981682079001_5981678555001-vs.jpg?pubId=429048911&videoId=5981678555001",
                400);
        allProducts.add(t4);


        Product t5 = new Product(1,"Chocolate cookies","https://wallpapercave.com/wp/wp1966936.jpg",100);
        allProducts.add(t5);
        Product t6 = new Product(1,"M&M cookies","https://fortheloveofbakin.files.wordpress.com/2015/09/c6.jpg",150);
        allProducts.add(t6);
        Product t7 = new Product(1,"Christmas cookies","http://images6.fanpop.com/image/photos/32700000/Christmas-Cookies-food-32709943-2560-1600.jpg",400);
        allProducts.add(t7);

        Product t8 = new Product(2, "Chocolate muffin","https://hips.hearstapps.com/vidthumb/images/delish-keto-double-chocolate-muffins-still002-1533069371.jpg",200);
        allProducts.add(t8);
        Product t9 = new Product(2, "Blueberry muffin ","https://assets.epicurious.com/photos/55a52f282165ea177986d1c3/master/pass/56389811_blueberry-muffins_6x4.jpg",200);
        allProducts.add(t9);
        Product t10 = new Product(2, "Strawberry-cream muffin","https://food-images.files.bbci.co.uk/food/recipes/raspberry_and_cream_89607_16x9.jpg",250);
        allProducts.add(t10);

    }

    private void setProducts(){
        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);


        for(int i = 0; i < allProducts.size(); i++) {
            if(allProducts.get(i).getId() == position){
              shownProducts.add(allProducts.get(i));
            }
        }



    }
}
