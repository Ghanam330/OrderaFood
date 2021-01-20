package com.example.orderfoods.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoods.Adabter.CategoryAdapter;
import com.example.orderfoods.Model.Category;
import com.example.orderfoods.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    CategoryAdapter adapter;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.recycler_menu);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        FirebaseRecyclerOptions<Category> options
                = new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("categories"), Category.class)
                .build();


        adapter = new CategoryAdapter(options);
        recyclerView.setAdapter(adapter);


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



