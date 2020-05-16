package com.example.application_1.data;

import com.example.application_1.presentation.model.model.RegionPkmnResponse;
import com.example.application_1.presentation.model.model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApi {
 /* @GET("/api/v2/region")
    Call<RegionPkmnResponse> getRegionResponse () ;
    /*@Query("cle") String valeur*/

    @GET("/api/v2/region")
    Call<RestPokemonResponse> getPokemonResponse () ;

//"https://pokeapi.co/api/v2/"
}


