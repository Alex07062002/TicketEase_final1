package com.example.ticketease.MVVM.Event.Preferences

import com.example.ticketease.DataClasses.Catalog

interface PreferencesRepository {

    suspend fun getAllEvents(): List<Catalog>

    suspend fun preferencesRoom() : List<Catalog>

    suspend fun selectEventCountByBuyer() : Long


}