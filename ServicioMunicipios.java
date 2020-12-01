package com.example.spinnerretrifit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServicioMunicipios {
    @GET("ConsultaMunicipio")
    Call<ClaseRaizMunicipio> pedirMunicipios(@Query("Provincia") String provincia, @Query("Municipio") String municipio);
}
