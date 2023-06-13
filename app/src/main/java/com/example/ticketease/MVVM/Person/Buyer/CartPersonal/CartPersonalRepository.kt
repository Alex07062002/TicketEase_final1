package com.example.ticketease.MVVM.Person.Buyer.CartPersonal

import com.example.ticketease.DataClasses.Event.EventId
import com.example.ticketease.DataClasses.Ticket.TicketDTO
import com.example.ticketease.DataClasses.Ticket.TicketUpdate

interface CartPersonalRepository {

    suspend fun countSoldTicket(eventId: EventId) : Long

    suspend fun updateBuyerId(ticketUpdate: TicketUpdate)
}