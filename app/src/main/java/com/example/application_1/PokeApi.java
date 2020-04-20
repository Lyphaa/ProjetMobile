package com.example.application_1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeApi {
    @GET("/api/v2/pokemon/")
    Call<RestPokemonResponse> getPokemonResponse () ;
    ///*@Query("cle") String valeur*/

}
