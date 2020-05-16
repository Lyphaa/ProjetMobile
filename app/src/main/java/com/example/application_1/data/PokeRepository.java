package com.example.application_1.data;

import android.content.SharedPreferences;

import com.example.application_1.Constants;
import com.example.application_1.presentation.model.model.Pokemon;
import com.example.application_1.presentation.model.model.RegionPkmnResponse;
import com.example.application_1.presentation.model.model.RestPokemonResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokeRepository {
    private PokeApi pokeApi;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public PokeRepository(PokeApi pokeApi, SharedPreferences sharedPreferences, Gson gson) {
        this.pokeApi = pokeApi;
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;

    }

    public void getPokemonResponse(final PokeCallBack callBack){
        List<Pokemon> list = getDatafromCache();
        if (list!= null){
            callBack.onSucces(list);
        }else{
            pokeApi.getPokemonResponse().enqueue(new Callback<RestPokemonResponse>() {
                @Override
                public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                    if(response.isSuccessful() && response.body() != null){
                        callBack.onSucces(response.body().getResult());
                    }else{
                        callBack.onFailed();
                    }
                }

                @Override
                public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                    callBack.onFailed();
                }
            });
        }

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

    private void saveList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_POKEMON_LIST, jsonString)
                .apply();
    }

}
