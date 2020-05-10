package com.example.application_1.presentation.model.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.application_1.Constants;
import com.example.application_1.R;
import com.example.application_1.data.PokeApi;
import com.example.application_1.presentation.model.controller.MainController;
import com.example.application_1.presentation.model.model.Pokemon;
import com.example.application_1.presentation.model.model.RestPokemonResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List_adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        controller = new MainController(
                this,
                new GsonBuilder()
                        .setLenient()
                        .create(),
                getSharedPreferences("projet_mobile", Context.MODE_PRIVATE)
        );

        controller.onStart();


    }



    public void showList(List<Pokemon> pokemonList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

       /* List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Nom " + i);
        }*/
        // define an adapter
        mAdapter = new List_adapter(pokemonList);
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_SHORT).show();

    }
}
