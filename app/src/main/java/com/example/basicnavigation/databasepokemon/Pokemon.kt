package com.example.basicnavigation.databasepokemon

class Pokemon(id:String,nombre:String,tipo:String,hp:String,velocidad:String,peso:String,ataque:String,defensa:String) {
    val id=id
    val nombre=nombre
    val tipo=tipo
    val hp=hp
    val velocidad=velocidad
    val peso=peso
    val ataque=ataque
    val defensa=defensa


}
fun Pokemon.toEntity() = PokemonEntity(
    id,nombre,tipo,hp,velocidad,peso,ataque,defensa
)