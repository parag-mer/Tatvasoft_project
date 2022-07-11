package com.example.book_e_sell;

import android.gesture.GestureLibraries;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class list_adapter extends FirebaseRecyclerAdapter<list_main_model,list_adapter.my_view_holder>
{
    FirebaseAuth mauth= FirebaseAuth.getInstance();
    FirebaseFirestore fstore=FirebaseFirestore.getInstance();
    public list_adapter(FirebaseRecyclerOptions<list_main_model> options)
    {
        super(options);
    }
    protected void onBindViewHolder(my_view_holder holder, int position, list_main_model model)
    {
        holder.category.setText(model.getCategory());
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
        Glide.with(holder.img.getContext())
                .load(model.getPic())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
        holder.add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> cart = new HashMap<>();
                cart.put("name", model.getName());
                cart.put("price", model.getPrice());
                cart.put("category", model.getCategory());
                cart.put("pic",model.getPic());

                fstore.collection("users").document(Objects.requireNonNull(mauth.getCurrentUser()).getUid()).collection("cart").add(cart).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(v.getContext(), "added to cart", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(v.getContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public my_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_model_main,parent,false);
        return new my_view_holder(view);
    }
    static class my_view_holder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView name,price,category;
        Button add_cart;

        public my_view_holder(View itemview)
        {
            super(itemview);
            category=itemview.findViewById(R.id.category);
            img=itemview.findViewById(R.id.img);
            name=itemview.findViewById(R.id.name);
            price=itemview.findViewById(R.id.price);
            add_cart=itemview.findViewById(R.id.cart_btn);
        }
    }
}
