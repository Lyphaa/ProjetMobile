package com.example.application_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeApiCall();
    }

    private void showList(List<ApiPerso> SoireeList ) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

       /* création fausse liste
       List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Nom " + i);
        }
        */

        // define an adapter
        mAdapter = new List_adapter(SoireeList);
        recyclerView.setAdapter(mAdapter);
    }


    private static final String BASE_URL = "https://raw.githubusercontent.com/Lyphaa/Application_1/master/";
    private void makeApiCall() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        SoireeApi soireeApi = retrofit.create(SoireeApi.class);

       Call<RestApiPersoResponse> call = soireeApi.getSoireeResponse () ;

        call.enqueue(new Callback<RestApiPersoResponse>() {
            @Override
            public void onResponse(Call<RestApiPersoResponse> call, Response<RestApiPersoResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<ApiPerso> SoireeList = response.body().getResult();
                    showList(SoireeList);
                }else {
                    showError();
                }
            }  /* */

            @Override
            public void onFailure(Call<RestApiPersoResponse> call, Throwable t) {
                showError();
        
            }
        });

    }
       /* Call<RegionPkmnResponse> call = pokeApi.getRegionResponse();
        call.enqueue(new Callback<RegionPkmnResponse>() {
            @Override
            public void onResponse(Call<RegionPkmnResponse> call, Response<RegionPkmnResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<RegionPkmn> RegionList = response.body().getResult();
                    showList(RegionList);
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RegionPkmnResponse> call, Throwable t) {
                showError();
            }
        });

    }*/
    private void showError() {
        Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_SHORT).show();
    }
}

