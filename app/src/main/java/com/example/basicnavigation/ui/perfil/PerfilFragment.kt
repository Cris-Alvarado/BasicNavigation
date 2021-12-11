package com.example.basicnavigation.ui.perfil

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.basicnavigation.databinding.FragmentPerfilBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONObject


class PerfilFragment : Fragment() {
    private lateinit var binding: FragmentPerfilBinding
    private lateinit var database: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPerfilBinding.inflate(inflater, container,false)

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myDB = FirebaseDatabase.getInstance()
        database = myDB.reference

        val etIdUsuario = binding.etIdUser

        binding.btnBuscarId.setOnClickListener {
            getUser(binding.etIdUser.text.toString())
            //etIdUsuario.text.clear()
        }
       // writeNewUser("003","Guadalupe","Lupita003","30",0)
        //getUser("001")


    }

    fun getUser(usuario: String){
        database.child("usuarios").child(usuario).get().addOnSuccessListener { record ->
            val json = JSONObject(record.value.toString())

            binding.tvNombre.setText("Nombre: ${json.getString("nombre")}")
            binding.tvUsuario.setText("Usuario: ${json.getString("usuario")}")
            binding.tvNivel.setText("Nivel: ${json.getString("nivel")}")
            binding.tvNumPokemons.setText("Pokemons: ${json.getString("numPokemon")}")

            Log.d("ValoresFirebase","Got value ${record.value}")
            Log.d("ValoresFirebase","nombre ${json.getString("nombre")}")

        }.addOnFailureListener { record ->
            binding.tvNombre.setText("No se encontro usuario")
            Log.d("ValoresFirebase", "Error: ${record}")
        }
    }

    fun writeNewUser(userId: String, name: String, user: String, level: String, pokemons: Int){
        val user = User(name, user, level, pokemons)
        database.child("usuarios").child(userId).setValue(user)
    }

}

