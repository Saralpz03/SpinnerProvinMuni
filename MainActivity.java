package com.example.spinnerretrifit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {
    Spinner spn_provincias;
    Spinner spn_municipios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn_provincias=findViewById(R.id.spn_provincias);
        spn_municipios=findViewById(R.id.spn_municipios);

        cargarProvincias();

        //Spinners
        spn_provincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cargarMunicipios(spn_provincias.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    //Cargar Municipios
    private void cargarMunicipios(String nombreProvincia) {
        String base = "http://ovc.catastro.meh.es/ovcservweb/ovcswlocalizacionrc/ovccallejero.asmx/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base).addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        ServicioMunicipios servicio = retrofit.create(ServicioMunicipios.class);
        Call<ClaseRaizMunicipio> llamada = servicio.pedirMunicipios(nombreProvincia, "");
        llamada.enqueue(new Callback<ClaseRaizMunicipio>() {
            @Override
            public void onResponse(Call<ClaseRaizMunicipio> call, Response<ClaseRaizMunicipio> response) {
                ClaseRaizMunicipio crm = response.body();
                if (crm != null) {
                    List<Municipio> listaMunicipios = crm.getMunicipiero();

                    ArrayAdapter municipioAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listaMunicipios);
                    spn_municipios.setAdapter(municipioAdapter);
                }
            }

            @Override
            public void onFailure(Call<ClaseRaizMunicipio> call, Throwable t) {
                //Si hay un error que ponga esto...
                System.out.println("Error: " + t.getMessage());
            }
        });
    }


    //Cargar Provincias
    private void cargarProvincias(){
        String base="http://ovc.catastro.meh.es/ovcservweb/ovcswlocalizacionrc/ovccallejero.asmx/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base).addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        ServicioProvincias servicio = retrofit.create(ServicioProvincias.class);
        Call<ClaseRaizProvincia> llamada = servicio.pedirProvincias();
        llamada.enqueue(new Callback<ClaseRaizProvincia>() {
            @Override
            public void onResponse(Call<ClaseRaizProvincia> call, Response<ClaseRaizProvincia> response) {
                //System.out.println(response.body());
                ClaseRaizProvincia cr=response.body();
                if (cr != null){
                    List<Provincia> listaProvincias=cr.getProvinciero();
                    ArrayAdapter provinciaAdapater = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,listaProvincias);
                    spn_provincias.setAdapter(provinciaAdapater);
                }

            }

            @Override
            public void onFailure(Call<ClaseRaizProvincia> call, Throwable t) {

            }
        });
    }
}