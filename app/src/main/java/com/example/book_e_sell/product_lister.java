package com.example.book_e_sell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class product_lister extends AppCompatActivity {

    RecyclerView list_recycle;
    list_adapter la;
    DatabaseReference datalist;
    TextView c_name;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_lister);
        c_name = findViewById(R.id.category_name);
        Intent i = getIntent();
        name = i.getStringExtra("receive");
        c_name.setText(name);
        list_recycle = findViewById(R.id.rv);
        list_recycle.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<list_main_model> options =
                new FirebaseRecyclerOptions.Builder<list_main_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("global_search").orderByChild("category").equalTo(name), list_main_model.class)
                        .build();

        la = new list_adapter(options);
        list_recycle.setAdapter(la);
    }

    @Override
    protected void onStart() {
        super.onStart();
        la.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        la.stopListening();
    }
}