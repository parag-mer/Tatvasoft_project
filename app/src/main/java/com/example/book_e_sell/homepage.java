package com.example.book_e_sell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView NA_recycle,MP_recycle;
    ImageView plus,adv,cls,edu,Nav_menu;
    na_main_adapter na;
    mp_main_adapter mp;

    DatabaseReference NA_data,MP_data;
    DrawerLayout drawer;
    RelativeLayout searchbar;
    NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        NA_recycle=(RecyclerView)findViewById(R.id.NA_recycler);
        NA_recycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        MP_recycle=(RecyclerView)findViewById(R.id.MP_recycler);
        MP_recycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        plus= findViewById(R.id.more);
        adv=findViewById(R.id.ad);
        cls=findViewById(R.id.cl);
        edu=findViewById(R.id.ed);
        searchbar=findViewById(R.id.search);
        drawer= findViewById(R.id.drawer);
        navigation=findViewById(R.id.navigation);
        Nav_menu=findViewById(R.id.nav_menu);
        navigationdrawer();


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this, categories.class));
            }
        });

        searchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this, global_search.class));
            }
        });

        adv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(homepage.this,product_lister.class);
                a.putExtra("receive","adventure");
                startActivity(a);
            }
        });
        cls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(homepage.this,product_lister.class);
                a.putExtra("receive","classic");
                startActivity(a);
            }
        });
        edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(homepage.this,product_lister.class);
                a.putExtra("receive","educational");
                startActivity(a);
            }
        });
        //code of recycler view of newly added
        NA_data= FirebaseDatabase.getInstance().getReference().child("newly_added");
        FirebaseRecyclerOptions<na_main_model> options =
                new FirebaseRecyclerOptions.Builder<na_main_model>()
                        .setQuery(NA_data, na_main_model.class)
                        .build();
        na=new na_main_adapter(options);
        NA_recycle.setAdapter(na);

        //code of recycler view of most purchased
        MP_data= FirebaseDatabase.getInstance().getReference().child("most_purchased");
        FirebaseRecyclerOptions<mp_main_model> options2 =
                new FirebaseRecyclerOptions.Builder<mp_main_model>()
                        .setQuery(MP_data, mp_main_model.class)
                        .build();
        mp=new mp_main_adapter(options2);
        MP_recycle.setAdapter(mp);
    }

    private void navigationdrawer()
    {
        navigation.bringToFront();
        navigation.setNavigationItemSelectedListener(this);
        navigation.setCheckedItem(R.id.nav_home);


        Nav_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawer.isDrawerVisible(GravityCompat.START))
                {
                    drawer.closeDrawer(GravityCompat.START);
                }
                else
                {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        if(drawer.isDrawerVisible(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }

    }

    protected void onStart() {
        super.onStart();
        NA_recycle.getRecycledViewPool().clear();
        na.notifyDataSetChanged();
        na.startListening();
        MP_recycle.getRecycledViewPool().clear();
        mp.notifyDataSetChanged();
        mp.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        na.stopListening();
        mp.stopListening();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {

            case R.id.nav_categories:
            {
                startActivity(new Intent(homepage.this,categories.class));
                navigation.setCheckedItem(R.id.nav_home);
                break;

            }
            case R.id.nav_logout:
            {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(homepage.this,login.class));
                finish();
                break;
            }
            case R.id. nav_cart:
            {
                startActivity(new Intent(homepage.this,cart.class));
                navigation.setCheckedItem(R.id.nav_home);
                break;
            }
            case R.id.nav_search:
            {
                startActivity(new Intent(homepage.this,global_search.class));
                navigation.setCheckedItem(R.id.nav_home);
                break;
            }
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}