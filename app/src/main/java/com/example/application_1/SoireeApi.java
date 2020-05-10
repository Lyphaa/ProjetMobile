package com.example.application_1;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SoireeApi {

        @GET("/ApiPerso.json")
        Call<RestApiPersoResponse> getSoireeResponse () ;
        //https://raw.githubusercontent.com/Lyphaa/Application_1/master/
}
