package com.example.ticketease.MVVM.Event.getTime

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.PlaceTime.PlaceDTO
import com.example.ticketease.DataClasses.PlaceTime.PlaceId
import com.example.ticketease.DataClasses.PlaceTime.PlaceTimeDTO
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class ViewModelgetTimeRepository @Inject constructor(
    private val repository : getTimeRepository,
    private val prefs : SharedPreferences
) : ViewModel() {
    var place  = Gson().fromJson(prefs.getString("placeDTO",null)!!,PlaceDTO::class.java)

    private val _listTime = MutableLiveData<List<PlaceTimeDTO>>()
    val listTime: LiveData<List<PlaceTimeDTO>>
        get() = _listTime
    init {
        viewModelScope.launch {
            _listTime.value = repository.getTime(PlaceId(place.id))
        }
    }


}