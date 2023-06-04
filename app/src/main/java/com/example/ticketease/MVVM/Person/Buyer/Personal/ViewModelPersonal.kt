package com.example.ticketease.MVVM.Person.Buyer.Personal

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ticketease.DataClasses.Person.BuyerResponse
import com.example.ticketease.DataClasses.Person.BuyerWithoutPswd
import com.example.ticketease.MVVM.Person.Buyer.Register.RegistResult
import com.example.ticketease.MVVM.Person.Buyer.Register.RegisterRepository
import com.google.gson.Gson
import com.google.gson.JsonParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelPersonal @Inject constructor(
    private val repository: PersonalRepository,
    private val prefs : SharedPreferences
) : ViewModel() {
    var city  = prefs.getString("city","Moscow")
    var state by mutableStateOf(Gson().fromJson(prefs.getString("buyer",null)!!,BuyerWithoutPswd::class.java))

     fun createCatalog() {
        viewModelScope.launch {
            val result = repository.getAllEvents()
            prefs.edit().putString("catalog",Gson().toJson(result)).apply()
        }
    }
    fun createPreference(){
        viewModelScope.launch {
            val listTicket = repository.selectEventByBuyer().size
           val listPrefs = if (listTicket<5){
                repository.getAllEvents()
            }else {
                repository.preferencesRoom()
            }
            prefs.edit().putString("preferences",Gson().toJson(listPrefs)).apply()
        }
    }

    fun CartPerson(){
        viewModelScope.launch {
           // val listTickets = repository
        }
    }

}


