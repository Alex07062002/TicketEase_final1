package com.example.ticketease.MVVM.Person.Buyer.CitySelector

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.Person.BuyerUpdateCity
import com.example.ticketease.DataClasses.Person.Cities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelCitySelector@Inject constructor(
    private val prefs : SharedPreferences,
    private val repository : CitySelectorRepository
) : ViewModel(){
    var cityState = mutableStateOf("")
    var state by mutableStateOf(BuyerUpdateCity())
    var bool=false

    fun city(city : CitySelector){
        when(city){
            is CitySelector.City -> cityState.value = city.value.toString()
            is CitySelector.SaveCity -> putCity()
            is CitySelector.citySelector->{
                state.city=cityState.value
                state.token= prefs.getString("token", null).toString()
                upd()
            }
        }
    }

    private fun putCity() {
        prefs.edit().putString("city", cityState.value)!!.apply()
    }
    private fun upd(){
        viewModelScope.launch {
            bool=repository.update(state)

        }
    }

     fun cityByString(cityString:String): Cities {
        if( cityString=="Moscow"){
            return Cities.Moscow
        }else if(cityString=="Voronezh"){
            return Cities.Voronezh
        }else if(cityString=="SaintPetersburg"){
            return Cities.SaintPetersburg

        }
        return Cities.Moscow
    }
}