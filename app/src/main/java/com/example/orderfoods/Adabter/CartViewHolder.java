package com.example.orderfoods.Adabter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoods.Interface.ItemClickListener;
import com.example.orderfoods.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txt_cart, txt_price;
    public ImageView ima_cart_count;

    private ItemClickListener itemClickListener;

    public void setTxt_cart(TextView txt_cart) {
        this.txt_cart = txt_cart;
    }



    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_cart = itemView.findViewById(R.id.cart_item_name);
        txt_price = itemView.findViewById(R.id.cart_item_price);
        ima_cart_count = itemView.findViewById(R.id.cart_item_count);
    }


    @Override
    public void onClick(View v) {

    }
}
