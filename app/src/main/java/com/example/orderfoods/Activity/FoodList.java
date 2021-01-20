package com.example.orderfoods.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoods.Adabter.FoodAdapter;
import com.example.orderfoods.Model.Food;
import com.example.orderfoods.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FoodList extends AppCompatActivity {
    RecyclerView recyclerView;
    FoodAdapter adapter;
    private String categoryId = "";
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference foodlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);


        // طريقه اعرض بيها الداتا من الفايربيس


        database = FirebaseDatabase.getInstance();
        foodlist = database.getReference("Foods");

        recyclerView = findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        if (getIntent() != null) {
            categoryId = getIntent().getStringExtra("CategoryId");
        }



        if (categoryId != null && !categoryId.isEmpty()) {
            loadListFood(categoryId);
        }
    }


    private void loadListFood(String categoryId) {


        // بيجيب الداتا بمعلوميه بمعلوميه id

        Query food = foodlist
                .orderByChild("menuId").equalTo(categoryId);
        FirebaseRecyclerOptions<Food> options =
                new FirebaseRecyclerOptions.Builder<Food>().setLifecycleOwner(this)
                        .setQuery(food, Food.class).build();


        adapter = new FoodAdapter(options);
        recyclerView.setAdapter(adapter);


        //   كود غلط
/*
        FirebaseRecyclerOptions<Food> options
                = new FirebaseRecyclerOptions.Builder<Food>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Foods"), Food.class)
                .build();
        foodlist.orderByChild("menuId").equalTo(categoryId);

 */
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}

// طريقه تانيه اعرض بيها الداتا من الفايربيس
/*
        recyclerView = findViewById(R.id.recycler_food);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");

        if (!categoryId.isEmpty()) {

            FirebaseRecyclerOptions<Food> options
                    = new FirebaseRecyclerOptions.Builder<Food>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("Foods"), Food.class)
                    .build();


            adapter = new FoodAdapter(options);
            recyclerView.setAdapter(adapter);


        }


    }
*/





/*
        if (getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");

        if (!categoryId.isEmpty() && categoryId != null) {
/*
            FirebaseRecyclerOptions<Food> options
                    = new FirebaseRecyclerOptions.Builder<Food>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("Foods"), Food.class)
                    .build();


         //   adapter = new FoodAdapter(options);

 */