package com.example.application_1.data;

import com.example.application_1.presentation.model.model.Pokemon;
import com.example.application_1.presentation.model.model.RegionPkmnResponse;

import java.util.List;

public interface PokeCallBack {
        void onSucces(List<Pokemon> response);
        void onFailed();

}
