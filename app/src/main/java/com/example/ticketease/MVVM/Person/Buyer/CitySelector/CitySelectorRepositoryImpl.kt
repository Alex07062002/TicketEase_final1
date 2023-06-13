package com.example.ticketease.MVVM.Person.Buyer.CitySelector

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.Person.BuyerUpdateCity
import com.example.ticketease.MVVM.RetrofitAPI

class CitySelectorRepositoryImpl (
    private val api : RetrofitAPI,
    private val prefs : SharedPreferences
) : CitySelectorRepository {
    override suspend fun update(buyer: BuyerUpdateCity?): Boolean {

        return api.buyerUpdateCity(buyer)
    }
}