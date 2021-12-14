package com.example.basicnavigation.ui.pokedex

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicnavigation.databasepokemon.Pokemon
import com.example.basicnavigation.databinding.ItemUserBinding

class PokedexAdapter (private val pokemon : List<Pokemon>): RecyclerView.Adapter<PokedexAdapter.PokemonHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PokemonHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.render(pokemon[position]) //pasa 1x1
    }

    override fun getItemCount(): Int = pokemon.size


    class PokemonHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){

        fun render (pokemon: Pokemon){
            binding.tvMiPokemon.setText("Nombre: ${pokemon.nombre} Tipo: ${pokemon.tipo}")


            binding.btnEliminar.setOnClickListener {
                Log.d("Eliminado", "${pokemon.nombre}")

            }
        }


    }
}