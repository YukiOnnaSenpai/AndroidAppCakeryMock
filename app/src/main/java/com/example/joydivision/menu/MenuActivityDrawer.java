package com.example.joydivision.menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.joydivision.R;
import com.example.joydivision.login.Common;
import com.example.joydivision.order.OrderActivity;
import com.example.joydivision.productMenu.Product;
import com.example.joydivision.productMenu.ProductActivity;

import java.util.ArrayList;
import java.util.List;

public class MenuActivityDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView textViewUser,textViewUserEmail;
    public static List<Product> orderList= new ArrayList<>();


    private ArrayList<ProductType> productTypes = new ArrayList<>();
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            // viewHolder.getItemId();
            // viewHolder.getItemViewType();
            // viewHolder.itemView;
            Intent intent = new Intent(MenuActivityDrawer.this, ProductActivity.class);
            intent.putExtra("position", position);
            MenuActivityDrawer.this.startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        //fab.setBackgroundColor(Integer.parseInt("#DAAB65"));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (orderList.size() == 0) {

                    Snackbar.make(view, "You haven't order any products yet", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else{

                    Intent intent = new Intent(MenuActivityDrawer.this, OrderActivity.class);
                    //intent.putExtra("position", position);
                    MenuActivityDrawer.this.startActivity(intent);

                }
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);




        View hView =  navigationView.getHeaderView(0);
        textViewUser = hView.findViewById(R.id.textViewCommonUser);
        textViewUser.setText(Common.currentUser.getName());

        textViewUserEmail=hView.findViewById(R.id.textViewEmailNavBar);
        textViewUserEmail.setText(Common.currentUser.getEmail());


        fillProductTypes();

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view_menu_drawer);
        MenuAdapter menuAdapter = new MenuAdapter(this,productTypes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(menuAdapter);

        //TODO: Step 1 of 4: Create and set OnItemClickListener to the adapter.
        menuAdapter.setOnItemClickListener(onItemClickListener);





    }


    private void fillProductTypes(){
        productTypes =new ArrayList<>();
        ProductType t1 = new ProductType("Cakes","https://s24597.pcdn.co/wp-content/uploads/2019/02/5D4B8111-What-The-Fudge-Katie-Higgins-Crazy-Ingredient-Chocolate-Cake-HIGH-RES.jpg");
        productTypes.add(t1);
        ProductType t2 = new ProductType("Cookies","https://stmed.net/sites/default/files/biscuit-wallpapers-28211-7819773.jpg");
        productTypes.add(t2);
        ProductType t3 = new ProductType("Muffins", "https://i.pinimg.com/originals/7c/e2/76/7ce27639987aa6e9570b4a7c7c14ce46.jpg");
        productTypes.add(t3);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

      if ( id == R.id.nav_order) {
            Intent intent = new Intent(MenuActivityDrawer.this, OrderActivity.class);
            MenuActivityDrawer.this.startActivity(intent);

        } else if (id == R.id.nav_social_facebook) {
          String url = "https://www.facebook.com/carlosbakery/";
          Intent i = new Intent(Intent.ACTION_VIEW);
          i.setData(Uri.parse(url));
          startActivity(i);

        } else if ( id == R.id.nav_social_instagram) {
          String url = "https://www.instagram.com/explore/tags/cake/?hl=en";
          Intent i = new Intent(Intent.ACTION_VIEW);
          i.setData(Uri.parse(url));
          startActivity(i);

        } else if (id == R.id.nav_social_youtube) {
          String url = "https://www.youtube.com/watch?v=SA4ulM0wKtM";
          Intent i = new Intent(Intent.ACTION_VIEW);
          i.setData(Uri.parse(url));
          startActivity(i);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
