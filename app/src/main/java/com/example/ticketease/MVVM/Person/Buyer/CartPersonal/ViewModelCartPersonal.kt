package com.example.ticketease.MVVM.Person.Buyer.CartPersonal

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.Catalog
import com.example.ticketease.DataClasses.Event.EventId
import com.example.ticketease.DataClasses.Person.BuyerWithoutPswd
import com.example.ticketease.DataClasses.Ticket.TicketUpdate
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelCartPersonal @Inject constructor(
    private val repository: CartPersonalRepository,
    private val prefs : SharedPreferences
) : ViewModel(){

    private val _cart = MutableLiveData<List<Catalog>>()

    val cart: LiveData<List<Catalog>>
        get() = _cart

        private val eventTickets = mutableListOf<Pair<Long,Long>>()

    init {
        viewModelScope.launch {
            if (prefs.contains("cart")) {
                val cartList =
                    Gson().fromJson(prefs.getString("cart", null)!!, Array<Catalog>::class.java)
                        .toList()
                cartList.forEach {
                    eventTickets.add(
                        Pair(
                            it.eventId,
                            repository.countSoldTicket(EventId(it.eventId))
                        )
                    )
                }
                _cart.value = cartList
            }
        }
    }

    fun updateTicket(){
            eventTickets.forEach {
                val event = it.first
                viewModelScope.launch {
                    repository.updateBuyerId(TicketUpdate(Gson().fromJson(prefs.getString("buyer", null)!!,
                        BuyerWithoutPswd::class.java).id!!,event))
            }
        }
        prefs.edit().remove("cart").apply()
    }
}


