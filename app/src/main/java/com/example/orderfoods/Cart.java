package com.example.orderfoods;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoods.Adabter.CartAdapter;
import com.example.orderfoods.Database.Database;
import com.example.orderfoods.Model.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference request;

    TextView texTotalPrice;
    Button btn_Place;

    List<Order>cart=new ArrayList<>();
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        // Fire base
        database=FirebaseDatabase.getInstance();
        request=database.getReference("Requests");

        //Init
        recyclerView =findViewById(R.id.list_cart);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        texTotalPrice=findViewById(R.id.total);
        btn_Place=findViewById(R.id.btn_place_order);

        loadListFood();

    }

    private void loadListFood() {

        cart=new Database(this).getCarts();
        adapter= new CartAdapter(cart,this);
        recyclerView.setAdapter(adapter);


        //      Calculate total price

        int total=0;
        for (Order order:cart)
            total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));

        Locale locale=new Locale("eg","US");
        NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);

        texTotalPrice.setText(fmt.format(total));

    }
}