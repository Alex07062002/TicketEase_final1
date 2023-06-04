package com.example.ticketease.MVVM.Event.Catalog

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.Catalog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import javax.inject.Inject

@HiltViewModel
class ViewModelCatalog @Inject constructor(
    private val repository: CatalogRepository,
    private val prefs: SharedPreferences
) : ViewModel() {
    var state = mutableStateOf(Gson().fromJson(prefs.getString("catalog",null)!!,
            Array<Catalog>::class.java).toList())

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
    }


