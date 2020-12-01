package com.example.spinnerretrifit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicioProvincias {
    @GET("ConsultaProvincia")
    Call<ClaseRaizProvincia> pedirProvincias();
}
