package com.example.basicnavigation.ui.leftdestination

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicnavigation.database.DatabaseManager
import com.example.basicnavigation.database.MyAppDataSource
import com.example.basicnavigation.database.User
import kotlinx.coroutines.launch

class DestinationViewModel: ViewModel() {
    val savedUsers = MutableLiveData<List<User>>()
    fun getUsers(){
        viewModelScope.launch {
            val userDao = DatabaseManager.intance.database.userDao()
            savedUsers.value = MyAppDataSource(userDao).getUsers().value




           /* if (!obtainedList.isNullOrEmpty()){
                for (singleuser in obtainedList){
                    Log.d("obtainedusers", "user: ${singleuser.username}")
                }
            }else{
                Log.d("obtainedusers", "null or empty")
            }*/
        }
    }
}