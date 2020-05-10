package com.example.application_1.presentation.model.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application_1.Injection;
import com.example.application_1.R;
import com.example.application_1.presentation.model.controller.MainController;
import com.example.application_1.presentation.model.model.Pokemon;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private TextView textDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seconde_activity);

        textDetails = findViewById(R.id.second_txt);
        Intent intent = getIntent();
        String pokemonJson = intent.getStringExtra("pokemonKey");
        Pokemon pokemon = Injection.getGson().fromJson(pokemonJson, Pokemon.class);
        showDetails(pokemon);
    }

    private void showDetails(Pokemon pokemon) {
        textDetails.setText(pokemon.getName());
    }


}