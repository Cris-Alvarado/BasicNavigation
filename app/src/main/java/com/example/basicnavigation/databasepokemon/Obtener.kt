package com.example.basicnavigation.databasepokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class Obtener: ViewModel()  {
    val savedPokemon= MutableLiveData<List<Pokemon>>()
    fun getPokemon(){
        viewModelScope.launch {
            val pokemonDao = DatabaseManager.intance.database.pokemonDao()
            savedPokemon.value = MyAppDataSource(pokemonDao).getPokemon().value

        }
    }
    fun delete(pokemon: Pokemon){
        viewModelScope.launch {
            val pokemonDao = DatabaseManager.intance.database.pokemonDao()
            MyAppDataSource(pokemonDao).delete(pokemon)
            getPokemon()
        }

    }
}