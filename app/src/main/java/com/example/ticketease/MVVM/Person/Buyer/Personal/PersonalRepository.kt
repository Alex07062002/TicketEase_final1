package com.example.ticketease.MVVM.Person.Buyer.Personal

import com.example.ticketease.DataClasses.Catalog
import com.example.ticketease.DataClasses.Person.BuyerResponse
import com.example.ticketease.DataClasses.Person.BuyerWithoutPswd

interface PersonalRepository {
    suspend fun getByToken() : BuyerWithoutPswd

}