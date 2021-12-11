package com.example.basicnavigation.databasepokemon

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_POKEMON)
data class PokemonEntity(
    @PrimaryKey val id:String,
    val nombre:String,
    val tipo:String,
    val hp:String,
    val velocidad:String,
    val peso:String,
    val ataque:String,
    val defensa:String,

)

fun PokemonEntity.toPokemon() = Pokemon(
    id,nombre,tipo,hp,velocidad,peso,ataque,defensa
)