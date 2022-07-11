package com.example.book_e_sell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class categories extends AppCompatActivity {
    ImageView adventure,classic,educational,detective,fantacy,history,thriller,horror,science_fiction,superheros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        adventure=findViewById(R.id.i1);
        classic=findViewById(R.id.i2);
        educational=findViewById(R.id.i3);
        detective=findViewById(R.id.i4);
        fantacy=findViewById(R.id.i5);
        history=findViewById(R.id.i6);
        thriller=findViewById(R.id.i7);
        horror=findViewById(R.id.i8);
        science_fiction=findViewById(R.id.i9);
        superheros=findViewById(R.id.i10);

        adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(categories.this,product_lister.class);
                a.putExtra("receive","adventure");
                startActivity(a);
            }
        });
        classic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(categories.this,product_lister.class);
                a.putExtra("receive","classic");
                startActivity(a);
            }
        });
        educational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(categories.this,product_lister.class);
                a.putExtra("receive","educational");
                startActivity(a);
            }
        });
        detective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(categories.this,product_lister.class);
                a.putExtra("receive","detective");
                startActivity(a);
            }
        });
        fantacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(categories.this,product_lister.class);
                a.putExtra("receive","fantacy");
                startActivity(a);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(categories.this,product_lister.class);
                a.putExtra("receive","history");
                startActivity(a);
            }
        });
        thriller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(categories.this,product_lister.class);
                a.putExtra("receive","thriller");
                startActivity(a);
            }
        });
        horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(categories.this,product_lister.class);
                a.putExtra("receive","horror");
                startActivity(a);
            }
        });
        science_fiction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(categories.this,product_lister.class);
                a.putExtra("receive","science_fiction");
                startActivity(a);
            }
        });
        superheros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(categories.this,product_lister.class);
                a.putExtra("receive","superheros");
                startActivity(a);
            }
        });
    }
}