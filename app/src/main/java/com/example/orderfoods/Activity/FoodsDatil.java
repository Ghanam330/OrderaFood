package com.example.orderfoods.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.orderfoods.Database.Database;
import com.example.orderfoods.Model.Food;
import com.example.orderfoods.Model.Order;
import com.example.orderfoods.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodsDatil extends AppCompatActivity {
    TextView food_name, food_price,food_description;
    ImageView food_Image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String foodId = "";
    FirebaseDatabase database;
    DatabaseReference foods;

    Food currentFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_datil);

        database=FirebaseDatabase.getInstance();
        foods=database.getReference("Foods");

        // Init view
        numberButton=findViewById(R.id.number_button);
        btnCart=findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        foodId,
                        currentFood.getName(),
                        numberButton.getNumber(),
                        currentFood.getPrice(),
                        currentFood.getDescription()
                        
                ));

                Toast.makeText(FoodsDatil.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });

        food_description=findViewById(R.id.food_description);
        food_name=findViewById(R.id.food_name);
        food_price=findViewById(R.id.food_price);
        food_Image=findViewById(R.id.image_food1);

        collapsingToolbarLayout=findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandeAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        // get food Id from Intent
        if (getIntent() != null)
            foodId = getIntent().getStringExtra("FoodId");
        if (!foodId.isEmpty())
        {

            getDetailFood(foodId);
        }

    }

    private void getDetailFood(String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentFood= snapshot.getValue(Food.class);

                // set Image

                Picasso.get().load(currentFood.getImage())
                        .into(food_Image);

                collapsingToolbarLayout.setTitle(currentFood.getName());

                food_price.setText(currentFood.getPrice());

                food_name.setText(currentFood.getName());

                food_description.setText(currentFood.getDescription());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}