package com.example.ticketease.MVVM.Event.SelectPlace

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ticketease.DataClasses.PlaceTime.PlaceDTO
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class SelectPlaceViewModel @Inject constructor(
    private val prefs : SharedPreferences
) : ViewModel(){
    var placeState by  mutableStateOf(PlaceDTO())
    fun place(place : SelectPlace){
        when(place){
            is SelectPlace.selectPlace -> putCity()
        }
    }

    private fun putCity() {
        prefs.edit().putString("placeDTO", Gson().toJson(placeState,PlaceDTO::class.java)).apply()
    }

}