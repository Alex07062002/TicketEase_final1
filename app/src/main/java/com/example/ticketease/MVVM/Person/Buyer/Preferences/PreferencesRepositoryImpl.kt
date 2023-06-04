package com.example.ticketease.MVVM.Event.Preferences

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.Catalog
import com.example.ticketease.DataClasses.Person.City
import com.example.ticketease.MVVM.Person.Buyer.BuyerRetrofitAPI

class PreferencesRepositoryImpl(
    private val api : BuyerRetrofitAPI,
    private val prefs : SharedPreferences
) : PreferencesRepository{
    override suspend fun getAllEvents(): List<Catalog> =
        api.getAllEvents(City(prefs.getString("city", null)!!))
}