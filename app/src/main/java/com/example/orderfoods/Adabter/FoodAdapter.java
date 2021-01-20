package com.example.orderfoods.Adabter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoods.Activity.FoodsDatil;
import com.example.orderfoods.Model.Food;
import com.example.orderfoods.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class FoodAdapter extends FirebaseRecyclerAdapter<Food,FoodAdapter.MyViewHolder> {
    private FirebaseRecyclerOptions<Food> options;


    public FoodAdapter(@NonNull FirebaseRecyclerOptions<Food> options) {
        super(options);
        this.options = options;


    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Food model) {

        holder.food_name.setText(model.getName());
        Picasso.get().load(model.getImage())
                .into(holder.food_image);

        FoodAdapter adapter;
        adapter = new FoodAdapter(options);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foodDetail =new Intent(v.getContext(), FoodsDatil.class);
                foodDetail.putExtra("FoodId",adapter.getRef(position).getKey());
                holder.itemView.getContext().startActivity(foodDetail);

              //  Toast.makeText(v.getContext(), "" + local.getName(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item, parent, false);
        return new FoodAdapter.MyViewHolder(view);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView food_name;
        public ImageView food_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            food_name = itemView.findViewById(R.id.food_Name);
            food_image = itemView.findViewById(R.id.food_image);


        }
    }// end MyViewHolder

}
