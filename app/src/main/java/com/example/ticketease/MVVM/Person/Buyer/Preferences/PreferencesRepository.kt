package com.example.ticketease.MVVM.Event.Preferences

import com.example.ticketease.DataClasses.Catalog

interface PreferencesRepository {

    suspend fun getAllEvents(): List<Catalog>

    //TODO add getSoldTicket

}