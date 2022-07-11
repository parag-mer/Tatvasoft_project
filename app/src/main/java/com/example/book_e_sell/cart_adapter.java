package com.example.book_e_sell;

import android.annotation.SuppressLint;
import android.util.Log;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class cart_adapter extends RecyclerView.Adapter<cart_adapter.myviewholder>
{
    ArrayList<cart_item_model> datalist;
    FirebaseAuth mauth= FirebaseAuth.getInstance();
    FirebaseFirestore fstore=FirebaseFirestore.getInstance();
    public cart_adapter(ArrayList<cart_item_model> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position) {

        Log.d("TAG", "onBindViewHolder: " + datalist.get(position).getpic());
        holder.category.setText(datalist.get(position).getCategory());
        holder.book_nm.setText(datalist.get(position).getname());
        holder.book_price.setText(datalist.get(position).getprice());
        Glide.with(holder.book_img.getContext())
                .load(datalist.get(position).getpic())
                .placeholder(R.drawable.logo2)
                .error(R.drawable.logo2)
                .into(holder.book_img);
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fstore.collection("users").document(mauth.getCurrentUser().getUid()).collection("cart").document(datalist.get(position).getDocid())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    datalist.remove(datalist.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(v.getContext(), "Removed", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(v.getContext(), "error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    static class myviewholder extends RecyclerView.ViewHolder {
        ImageView book_img;
        TextView book_nm,book_price,category;
        Button remove;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            category=itemView.findViewById(R.id.c_category);
            book_img=itemView.findViewById(R.id.c_img);
            book_nm=itemView.findViewById(R.id.c_name);
            book_price=itemView.findViewById(R.id.c_price);
            remove=itemView.findViewById(R.id.c_btn);
        }
    }

}
