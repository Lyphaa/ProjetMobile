package com.example.application_1.presentation.model.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.application_1.R;
import com.example.application_1.Injection;
import com.example.application_1.presentation.model.controller.MainController;
import com.example.application_1.presentation.model.model.Pokemon;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List_adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController controller = new MainController(
            this,
            Injection.getGson(),
            Injection.getSharedPreferences(getApplicationContext())
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller.onStart();
    }

    public void showList(List<Pokemon> pokemonList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

       /* List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Nom " + i);
        }*/
        // define an adapter
        mAdapter = new List_adapter(pokemonList, new List_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pokemon item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_SHORT).show();

    }

    public void navigateToDetails(Pokemon pokemon) {
        Intent myIntent = new Intent(MainActivity.this, SecondActivity.class);
        myIntent.putExtra("pokemonKey", Injection.getGson().toJson(pokemon));
        //myIntent.putExtra("pokemonKeyUrl", pokemon.getUrl());
        MainActivity.this.startActivity(myIntent);
    }
}
