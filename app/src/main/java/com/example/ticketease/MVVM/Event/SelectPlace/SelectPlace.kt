package com.example.ticketease.MVVM.Event.SelectPlace

import com.example.ticketease.DataClasses.Person.Cities
import com.example.ticketease.MVVM.HelloPages.HelloPage7ChooseCity

sealed class SelectPlace {
    object selectPlace : SelectPlace()
}