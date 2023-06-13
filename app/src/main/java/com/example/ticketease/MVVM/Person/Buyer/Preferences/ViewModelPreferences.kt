package com.example.ticketease.MVVM.Event.Preferences

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.Catalog
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelPreferences @Inject constructor(
 private val repository: PreferencesRepository,
 private val prefs : SharedPreferences
) : ViewModel() {

 private val _preferences = MutableLiveData<List<Catalog>>() // TODO State(isLoading,List<Catalog>)
 val preferences: LiveData<List<Catalog>>
  get() = _preferences
 init {
    viewModelScope.launch {
      val listTicket = repository.selectEventCountByBuyer()
      if (!prefs.contains("buyer") || listTicket<5){
          _preferences.value = repository.getAllEvents()
      }else {
          _preferences.value = repository.preferencesRoom()
      }
  }
 }

    fun checkCartList(eventPreferences : Catalog){
        if (prefs.contains("cart")){
            val cartList = Gson().fromJson(prefs.getString("cart",null)!!,Array<Catalog>::class.java).toMutableList()
            if (!cartList.contains(eventPreferences)) cartList.add(eventPreferences)
            prefs.edit().putString("cart", Gson().toJson(cartList.toTypedArray(),Array<Catalog>::class.java)).apply()
        }else{
            val cartList = mutableListOf<Catalog>()
            cartList.add(eventPreferences)
            prefs.edit().putString("cart",Gson().toJson(cartList.toTypedArray(),Array<Catalog>::class.java)).apply()
        }
    }
}