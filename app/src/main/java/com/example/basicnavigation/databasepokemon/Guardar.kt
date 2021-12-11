package com.example.basicnavigation.databasepokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class Guardar: ViewModel() {
    fun save(pokemon: Pokemon){
        viewModelScope.launch {
            val pokemonDao = DatabaseManager.intance.database.pokemonDao()
            MyAppDataSource(pokemonDao).save(pokemon)
        }
    }
}