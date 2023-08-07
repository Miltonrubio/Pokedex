package com.example.pruebacrud2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.pruebacrud2.Models.PokeRespuesta;
import com.example.pruebacrud2.Models.Pokemon;
import com.example.pruebacrud2.pokeapi.PokeApiService;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "POKEDEX";

    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView=findViewById(R.id.recyclerView);
        listaPokemonAdapter=new ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);


        GridLayoutManager layoutManager= new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatos();



    }

    private void obtenerDatos(){

       PokeApiService service = retrofit.create(PokeApiService.class);


        Call<PokeRespuesta> pokeRespuestaCall = service.obtenerPokemon(0,151);
        pokeRespuestaCall.enqueue(new Callback<PokeRespuesta>() {
            @Override
            public void onResponse(Call<PokeRespuesta> call, Response<PokeRespuesta> response) {
                if (response.isSuccessful()){
                    PokeRespuesta respuestaPokemon = response.body();
                    ArrayList<Pokemon> listaPokemon= respuestaPokemon.getResults();

                    listaPokemonAdapter.adicionarListaPokemon(listaPokemon);

                    for (int i =0; i <listaPokemon.size(); i++){
                        Pokemon p= listaPokemon.get(i);
                        Log.i(TAG, "Pokemon: "+ p.getName());
                    }

                }else{
                    Log.e(TAG, "onResponse: " +response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokeRespuesta> call, Throwable t) {

                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });



    }

}

