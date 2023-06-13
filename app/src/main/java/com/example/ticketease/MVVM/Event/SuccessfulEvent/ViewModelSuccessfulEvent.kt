package com.example.ticketease.MVVM.Event.SuccessfulEvent

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.Event.EventDTO
import com.example.ticketease.DataClasses.PlaceTime.PlaceDTO
import com.example.ticketease.DataClasses.PlaceTime.StatusPlaceTime
import com.example.ticketease.DataClasses.Ticket.TicketCountWithPrice
import com.example.ticketease.MVVM.Event.SelectTime.CreateEvent
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ViewModelSuccessfulEvent  @Inject constructor(
    private val repository : SuccessfulEventRepository,
    private val prefs : SharedPreferences
) : ViewModel(){

     fun createTickets() {
         val event = Gson().fromJson(prefs.getString("event", null)!!, EventDTO::class.java)
         val place =  Gson().fromJson(prefs.getString("placeDTO", null)!!, PlaceDTO::class.java)
         viewModelScope.launch {
         repository.createTicket(
                 TicketCountWithPrice(event.id!!
                 ,place.capacity!!,
                 price = prefs.getString("price", null)!!.toDouble())
             )
         }
    }

}