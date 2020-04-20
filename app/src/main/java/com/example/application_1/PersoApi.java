package com.example.application_1;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PersoApi {
    @GET("/ApiPerso.json")
    Call<RestPokemonResponse> getPokemonResponse () ;
    ///*@Query("cle") String valeur*/


}
