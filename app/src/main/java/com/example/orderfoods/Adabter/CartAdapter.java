package com.example.orderfoods.Adabter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.orderfoods.Model.Order;
import com.example.orderfoods.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private List<Order> listData = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.cart_layout,parent,false);

        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        TextDrawable drawable=TextDrawable.builder()
                .buildRound("" +listData.get(position).getQuantity(), Color.RED);
        holder.ima_cart_count.setImageDrawable(drawable);
        Locale locale=new Locale("eg","US");
        NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
        int price=(Integer.parseInt(listData.get(position).getPrice())) * (Integer.parseInt(listData.get(position).getQuantity()));
        holder.txt_price.setText(fmt.format(price));
        holder.txt_cart.setText(listData.get(position).getProductId());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
