package com.example.ticketease.MVVM.Event.SelectTime

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.Event.EventDTO
import com.example.ticketease.DataClasses.PlaceTime.PlaceDTO
import com.example.ticketease.DataClasses.PlaceTime.PlaceTimeDTO
import com.example.ticketease.DataClasses.PlaceTime.StatusPlaceTime
import com.example.ticketease.DataClasses.Ticket.TicketCountWithPrice
import com.example.ticketease.MVVM.Event.SelectPlace.SelectPlace
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class SelectTimeViewModel  @Inject constructor(
    private val repository : CreateEvent,
    private val prefs : SharedPreferences
) : ViewModel(){
    var placeTime by mutableStateOf(PlaceTimeDTO())

    fun place(place : SelectTime){
        when(place){
            is SelectTime.selectTime -> putCity()
        }
    }

    private fun putCity() {
        placeTime.status = StatusPlaceTime.BUSY
        viewModelScope.launch {
              repository.placeTimeUpdate(placeTime)
        }
            val event = Gson().fromJson(prefs.getString("event", null)!!, EventDTO::class.java)
            event.placeTimeId = placeTime.id
        viewModelScope.launch {
             prefs.edit().putString("event",Gson().toJson(repository.eventCreate(event),EventDTO::class.java)).apply()
        }

    }
}