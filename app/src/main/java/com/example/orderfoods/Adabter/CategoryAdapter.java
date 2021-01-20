package com.example.orderfoods.Adabter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoods.Activity.FoodList;
import com.example.orderfoods.Model.Category;
import com.example.orderfoods.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class CategoryAdapter extends FirebaseRecyclerAdapter<Category, CategoryAdapter.MyViewHolder> {
    private FirebaseRecyclerOptions<Category> options;

    public CategoryAdapter(FirebaseRecyclerOptions<Category> options) {
        super(options);
        this.options = options;

    }


    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Category model) {
        holder.txtMenuName.setText(model.getName());
        Picasso.get().load(model.getImage())
                .into(holder.imageMenu);

        CategoryAdapter adapter;
        adapter = new CategoryAdapter(options);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent foodList = new Intent(v.getContext(), FoodList.class);
                foodList.putExtra("CategoryId", adapter.getRef(position).getKey());
                holder.itemView.getContext().startActivity(foodList);


            }
        });
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);
        return new CategoryAdapter.MyViewHolder(itemView);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtMenuName;
        public ImageView imageMenu;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMenuName = itemView.findViewById(R.id.menuName);
            imageMenu = itemView.findViewById(R.id.image_menu);


        }//constructor
    }// end MyViewHolder


}//end class

