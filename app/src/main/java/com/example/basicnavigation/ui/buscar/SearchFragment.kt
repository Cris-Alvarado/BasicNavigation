package com.example.basicnavigation.ui.buscar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.basicnavigation.R
import com.example.basicnavigation.databasepokemon.Guardar
import com.example.basicnavigation.databasepokemon.Pokemon
import com.example.basicnavigation.databinding.FragmentSearchBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONObject

class SearchFragment : Fragment() {

    private lateinit var queve: RequestQueue
    private lateinit var binding: FragmentSearchBinding
    private lateinit var database: DatabaseReference
    private val guardar : Guardar by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container,false)
        queve = Volley.newRequestQueue(activity)

        val etPokemonName = binding.etPokemonToSearchFor

         binding.btnSearch.setOnClickListener {
            getPokemon(etPokemonName.text.toString())
             etPokemonName.text.clear()
         }
        binding.btnGuardarPokemon.setOnClickListener {
            guardarPokemon()
            IngrementarPokemon()
            val destination= SearchFragmentDirections.actionSearchFragmentToPokedexFragment()
            NavHostFragment.findNavController(this).navigate(destination)
        }


        return binding.root

    }

    fun getPokemon(pokemonName: String){
        val url= "https://pokeapi.co/api/v2/pokemon/${pokemonName.lowercase()}"
       // val pokemonInfo = binding.tvPokemonInfo

        //TODO hp, ataque, defemsa, velocidad, peso, tipo
        //TODO hp; attack, defense, speed, weight, type
        val jsonRequest = JsonObjectRequest(url,
            Response.Listener<JSONObject>{ response ->
                //val id = response.getInt("id")
                val id = response.getString("id")
                val nombre = response.getString("name")

                val tipo = response.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name")
                val ataque = response.getJSONArray("stats").getJSONObject(1).getInt("base_stat")
                val defensa = response.getJSONArray("stats").getJSONObject(2).getInt("base_stat")
                val velocidad = response.getJSONArray("stats").getJSONObject(5).getInt("base_stat")
                val peso = response.getInt("weight")
                val hp = response.getJSONArray("stats").getJSONObject(0).getInt("base_stat")

                val infoString = "${nombre.replaceFirstChar { it.uppercase() }} #$id"
                //pokemonInfo.setText(infoString)
                //Log.d("JSONRespose", "response: $response")

                binding.tvPokemonId.setText("ID: $id")
                binding.tvPokemonName.setText("Nombre: $nombre")
                binding.tvPokemonAtaque.setText("Ataque: $ataque")
                binding.tvPokemonDefensa.setText("Defensa: $defensa")
                binding.tvPokemonVelocidad.setText("Velocidad: $velocidad")
                binding.tvPokemonPeso.setText("Peso: $peso")
                binding.tvPokemonTipo.setText("Tipo: $tipo")
                binding.tvPokemonHp.setText("HP: $hp")
                binding.btnGuardarPokemon.isVisible=true


            },
            Response.ErrorListener { errorMessage ->
                //pokemonInfo.setText("404 Pokémon no Encontrado")
                binding.tvPokemonId.setText("404 Pokémon no Encontrado")
               // binding.busqueda.text.clear()
                binding.tvPokemonName.setText("")
                binding.tvPokemonAtaque.setText("")
                binding.tvPokemonDefensa.setText("")
                binding.tvPokemonVelocidad.setText("")
                binding.tvPokemonPeso.setText("")
                binding.tvPokemonTipo.setText("")
                binding.tvPokemonHp.setText("")
                binding.btnGuardarPokemon.isVisible=false
                Log.d("JSONResponse","error: $errorMessage")

            }
        )
        queve.add(jsonRequest)
    }
    fun guardarPokemon(){

        guardar.save(
            Pokemon(
                binding.tvPokemonId.text.toString(),
                binding.tvPokemonName.text.toString(),
                binding.tvPokemonTipo.text.toString(),
                binding.tvPokemonHp.text.toString(),
                binding.tvPokemonVelocidad.text.toString(),
                binding.tvPokemonPeso.text.toString(),
                binding.tvPokemonAtaque.text.toString(),
                binding.tvPokemonDefensa.text.toString()

        )
        )


        Log.d("GUARDADO","Se guardo pokemon ")

    }

    override fun onStop() {
        super.onStop()
        queve.cancelAll("stopped")
    }

    private fun IngrementarPokemon(){
        val myDB= FirebaseDatabase.getInstance()
        database=myDB.reference
        var pokemon:Int=0
        database.child("usuarios").child("001").get().addOnSuccessListener { record ->
                val json = JSONObject(record.value.toString())
                pokemon=json.getInt("numPokemon")
                val contar= hashMapOf<String, Any>(
                    "/usuarios/001/pokemon" to pokemon+1
                )
                database.updateChildren(contar)
        }
    }
}