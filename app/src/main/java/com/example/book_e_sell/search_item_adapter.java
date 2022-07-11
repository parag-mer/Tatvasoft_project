package com.example.book_e_sell;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class search_item_adapter extends FirebaseRecyclerAdapter<search_item_model,search_item_adapter.my_view_holder>
{
    private static final String TAG = "TAG";
    FirebaseAuth mauth= FirebaseAuth.getInstance();
    FirebaseFirestore fstore=FirebaseFirestore.getInstance();

    public search_item_adapter(FirebaseRecyclerOptions<search_item_model> options)
    {
        super(options);
    }
    protected void onBindViewHolder(my_view_holder holder,int position, search_item_model model)
    {
        holder.category.setText(model.getCategory());
        holder.book_nm.setText(model.getname());
        holder.book_price.setText(model.getprice());
        Glide.with(holder.book_img.getContext())
                .load(model.getpic())
                .placeholder(R.drawable.logo2)
                .error(R.drawable.logo2)
                .into(holder.book_img);
        holder.add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> cart = new HashMap<>();
                cart.put("name", model.getname());
                cart.put("price", model.getprice());
                cart.put("category", model.getCategory());
                cart.put("pic",model.getpic());

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
    public search_item_adapter.my_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        return new my_view_holder(view);
    }
    class my_view_holder extends RecyclerView.ViewHolder
    {
        ImageView book_img;
        TextView book_nm,book_price,category;
        Button add_cart;

        public my_view_holder(View itemview)
        {
            super(itemview);
            category=itemview.findViewById(R.id.category);
            book_img=itemview.findViewById(R.id.img);
            book_nm=itemview.findViewById(R.id.name);
            book_price=itemview.findViewById(R.id.price);
            add_cart=itemview.findViewById(R.id.cart_btn);

        }
    }
}
