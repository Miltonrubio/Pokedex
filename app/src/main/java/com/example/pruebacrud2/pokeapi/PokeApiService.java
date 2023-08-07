package com.example.pruebacrud2.pokeapi;


import com.example.pruebacrud2.Models.PokeRespuesta;
import com.example.pruebacrud2.Models.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface PokeApiService {


 @GET("pokemon/{pokemonNumber}")
 Call<Pokemon> getPokemon(@Path("pokemonNumber") int pokemonNumber);

 @GET("pokemon")
 Call<PokeRespuesta> obtenerPokemon(@Query("offset") int offset, @Query("limit") int limit);


 @GET
 Call<Pokemon> getPokemonByUrl(@Url String url);


}




