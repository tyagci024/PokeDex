package com.example.pokedex.service

import com.example.pokedex.model.Pokemon
import io.reactivex.Single
import retrofit2.http.GET

interface PokeAPI {
    //https://raw.githubusercontent.com/tyagci024/pharmacyapi/main/poke.json
    // https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json

    @GET("tyagci024/pharmacyapi/main/poke.json")
    fun getAll():Single<List<Pokemon>>
}