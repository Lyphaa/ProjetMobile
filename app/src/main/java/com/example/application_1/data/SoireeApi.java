package com.example.application_1.data;

import com.example.application_1.presentation.model.model.RestApiPersoResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SoireeApi {

        @GET("/ApiPerso.json")
        Call<RestApiPersoResponse> getSoireeResponse () ;
        //https://raw.githubusercontent.com/Lyphaa/Application_1/master/
}
