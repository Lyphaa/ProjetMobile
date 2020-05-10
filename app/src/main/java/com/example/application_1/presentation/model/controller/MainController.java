package com.example.application_1.presentation.model.controller;

import android.content.SharedPreferences;

import com.example.application_1.Constants;
import com.example.application_1.Injection;
import com.example.application_1.presentation.model.model.Pokemon;
import com.example.application_1.presentation.model.model.RestPokemonResponse;
import com.example.application_1.presentation.model.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences){
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){

        List<Pokemon> pokemonList = getDatafromCache();
        if(pokemonList != null){
            view.showList(pokemonList);

        }else {
            makeApiCall();
        }
    }
    private void makeApiCall(){



        Call<RestPokemonResponse> call = Injection.getPokeApi().getPokemonResponse();
        call.enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Pokemon> pokemonList = response.body().getResult();
                    saveList(pokemonList);
                    view.showList(pokemonList);
                }else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                view.showError();

            }
        });

    }

    private void saveList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_POKEMON_LIST, jsonString)
                .apply();
            }

    private List<Pokemon> getDatafromCache() {
        String jsonPokemon = sharedPreferences.getString(Constants.KEY_POKEMON_LIST, null);

        //pour récupérer une liste
        if(jsonPokemon == null){
            return null;
        }else {
            Type listType = new TypeToken<List<Pokemon>>() {
            }.getType();
            return gson.fromJson(jsonPokemon, listType);
        }
    }

    public void onItemClick(Pokemon pokemon){
        view.navigateToDetails(pokemon);
    }
}
