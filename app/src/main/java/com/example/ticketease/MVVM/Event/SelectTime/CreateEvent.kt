package com.example.ticketease.MVVM.Event.SelectTime

import com.example.ticketease.DataClasses.Event.EventDTO
import com.example.ticketease.DataClasses.PlaceTime.PlaceTimeDTO
import com.example.ticketease.DataClasses.Ticket.TicketCountWithPrice
import retrofit2.http.Body
import retrofit2.http.POST


interface CreateEvent {

    suspend fun placeTimeUpdate(placeTimeDTO: PlaceTimeDTO)

    suspend fun eventCreate(eventDTO: EventDTO) : EventDTO
}