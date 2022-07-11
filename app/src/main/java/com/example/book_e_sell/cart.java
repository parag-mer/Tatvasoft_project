package com.example.book_e_sell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class cart extends AppCompatActivity {

    RecyclerView cart_recycle;
    cart_adapter ca;
    FirebaseAuth mauth= FirebaseAuth.getInstance();
    FirebaseFirestore fstore=FirebaseFirestore.getInstance();
    ArrayList<cart_item_model> datalist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ca = new cart_adapter(datalist);
        cart_recycle= findViewById(R.id.cart_rv);
        cart_recycle.setLayoutManager(new LinearLayoutManager(this));
        cart_recycle.setAdapter(ca);
        fstore.collection("users").document(Objects.requireNonNull(mauth.getCurrentUser()).getUid()).collection("cart").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list)
                        {
                            String docid = d.getId();
                            cart_item_model cim = d.toObject((cart_item_model.class));
                            cim.setDocid(docid);
                            datalist.add(cim);
                        }
                        ca.notifyDataSetChanged();
                    }
                });
    }
}