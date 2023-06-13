package com.example.ticketease.MVVM.Event.SelectTime

import com.example.ticketease.MVVM.Event.SelectPlace.SelectPlace

sealed class SelectTime {
    object selectTime : SelectTime()
}