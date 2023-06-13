package com.example.ticketease.MVVM.Event.SuccessfulEvent

import com.example.ticketease.DataClasses.Ticket.TicketCountWithPrice

interface SuccessfulEventRepository {
    suspend fun createTicket(ticketWithPrice : TicketCountWithPrice)

}