package com.example.ticketease.MVVM.Event.getEvents

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.Person.BuyerWithoutPswd
import com.example.ticketease.DataClasses.PlaceTime.PlaceDTO
import com.example.ticketease.DataClasses.PlaceTime.PlaceType
import com.google.gson.Gson
import com.ticketEase.backend.DataClasses.Place.TypeOfPlace
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel

class ViewModelRepositoryGetEvents @Inject constructor(
    private val repository : getEventsRepository,
    private val prefs : SharedPreferences
) : ViewModel() {
    var city  = prefs.getString("city",null)!!

    private val _listPlaces = MutableLiveData<List<PlaceDTO>>()
    val listPlaces: LiveData<List<PlaceDTO>>
        get() = _listPlaces
    init {
        viewModelScope.launch {
        _listPlaces.value = repository.get(PlaceType(TypeOfPlace.WITHOUT,city))
            }
        }
}