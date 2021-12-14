package com.example.basicnavigation.ui.pokedex

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.basicnavigation.R
import com.example.basicnavigation.databasepokemon.DatabaseManager
import com.example.basicnavigation.databasepokemon.Obtener
import com.example.basicnavigation.databasepokemon.Pokemon
import com.example.basicnavigation.databasepokemon.PokemonEntity
import com.example.basicnavigation.databinding.FragmentPokedexBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONObject

class PokedexFragment : Fragment() {
    private lateinit var binding: FragmentPokedexBinding
    private lateinit var database: DatabaseReference
    private val obtener: Obtener by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokedexBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//      val receivedPokemon = arguments?.getString("idpokemon")

        binding.rvPokemonEntries.layoutManager = LinearLayoutManager(view?.context)
        val adapter = PokedexAdapter(pokemonlist())
        binding.rvPokemonEntries.adapter = adapter

        obtener.getPokemon()
       /* obtener.savedPokemon.observe(viewLifecycleOwner, {pokemonlist->
            if(!pokemonlist.isNullOrEmpty()){
                val adapter = PokedexAdapter(pokemonlist, this)
                binding.rvPokemonEntries.adapter = adapter
                for (savepokemon in pokemonlist){
                }
            }else
            {
            }
        })
    }*/

    }
    fun eliminar (){
        Log.d("mensaje", "ENTRE")
        //obtener.delete(pokemon)
        actualizarPerfil()
    }

    private fun pokemonlist(): List<Pokemon> {
        var pokemons = mutableListOf<Pokemon>()

        return pokemons

    }

    private fun actualizarPerfil(){
        val myDB= FirebaseDatabase.getInstance()
        database=myDB.reference
        var pokemon:Int=0
        database.child("usuarios").child("001").get().addOnSuccessListener { record ->

                val json = JSONObject(record.value.toString())
                pokemon=json.getInt("pokemon")
                if(pokemon > 0){
                    val actualizarPok = hashMapOf<String, Any>(
                        "/usuarios/001/pokemon" to pokemon-1
                    )
                    Log.d("pokemos", "${pokemon}")
                    database.updateChildren(actualizarPok)
                }
        }

    }

    }


