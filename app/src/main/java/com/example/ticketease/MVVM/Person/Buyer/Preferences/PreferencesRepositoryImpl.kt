package com.example.ticketease.MVVM.Event.Preferences

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.Catalog
import com.example.ticketease.DataClasses.Person.BuyerId
import com.example.ticketease.DataClasses.Person.BuyerUpdateCity
import com.example.ticketease.DataClasses.Person.BuyerWithoutPswd
import com.example.ticketease.DataClasses.Person.City
import com.example.ticketease.MVVM.RetrofitAPI
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PreferencesRepositoryImpl(
    private val api : RetrofitAPI,
    private val prefs : SharedPreferences
) : PreferencesRepository {
    override suspend fun getAllEvents(): List<Catalog> = withContext(Dispatchers.IO) {
        api.getAllEvents(City(prefs.getString("city", null)!!))
    }

    override suspend fun preferencesRoom(): List<Catalog>  = withContext(Dispatchers.IO) {
        api.preferencesRoom(
            BuyerUpdateCity(
                prefs.getString("token", null)!!,
                prefs.getString("city", null)!!
            )
        )
    }
    override suspend fun selectEventCountByBuyer(): Long = withContext(Dispatchers.IO) {
        api.selectEventCountByBuyer(
            BuyerId(
                Gson().fromJson(
                    prefs.getString("buyer", null)!!,
                    BuyerWithoutPswd::class.java
                ).id!!
            )
        )
    }
    }

