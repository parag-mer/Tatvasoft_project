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

public class mp_main_adapter extends FirebaseRecyclerAdapter<mp_main_model,mp_main_adapter.my_view_holder>
{
    public mp_main_adapter(FirebaseRecyclerOptions<mp_main_model> options)
    {
        super(options);
    }
    protected void onBindViewHolder(my_view_holder holder, int position, mp_main_model model)
    {
        holder.book_nm.setText(model.getName());
        holder.book_price.setText(model.getPrice());
        Glide.with(holder.book_img.getContext())
                .load(model.getPic())
                .placeholder(com.google.firebase.firestore.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.book_img);
    }

    @NonNull
    @Override
    public mp_main_adapter.my_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mp_model_main,parent,false);
        return new my_view_holder(view);
    }
    static class my_view_holder extends RecyclerView.ViewHolder
    {
        ImageView book_img;
        TextView book_nm,book_price;

        public my_view_holder(View itemView)
        {
            super(itemView);
            book_img=itemView.findViewById(R.id.book_pic2);
            book_nm=itemView.findViewById(R.id.book_name2);
            book_price=itemView.findViewById(R.id.book_price2);
        }
    }
}