package com.example.book_e_sell;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
public class na_main_adapter extends FirebaseRecyclerAdapter<na_main_model,na_main_adapter.my_view_holder>
{
    public na_main_adapter(@NonNull FirebaseRecyclerOptions<na_main_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull my_view_holder holder, int position, @NonNull na_main_model model)
    {
        holder.book_price.setText(model.getprice());
        holder.book_nm.setText(model.getname());
        Glide.with(holder.book_img.getContext())
                .load(model.getpic())
                .placeholder(com.google.firebase.firestore.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.book_img);
    }

    @NonNull
    @Override
    public my_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.na_model_main,parent,false);
        return new my_view_holder(view);
    }

    static class my_view_holder extends RecyclerView.ViewHolder
    {
        ImageView book_img;
        TextView book_nm,book_price;

        public my_view_holder(@NonNull View itemView) {
            super(itemView);
            book_img=(ImageView) itemView.findViewById(R.id.book_pic);
            book_nm=(TextView) itemView.findViewById(R.id.book_name);
            book_price=(TextView) itemView.findViewById(R.id.book_price);
        }
    }
}
