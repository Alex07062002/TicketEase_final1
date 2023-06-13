package com.example.ticketease.MVVM.Event.EnterParam

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ticketease.DataClasses.Event.EventDTO
import com.example.ticketease.DataClasses.Person.OrganizerWithoutPswd
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelEnterParam  @Inject constructor(
    private val prefs : SharedPreferences
) : ViewModel() {
    var state by mutableStateOf(EventDTO())
    var statePrice = mutableStateOf("")
    var stateDate = mutableStateOf("")


    fun enter(enter : EnterParamStateTextFields){
        when(enter){
            is EnterParamStateTextFields.Name -> state = state.copy(name = enter.value)
            is EnterParamStateTextFields.Cost -> statePrice.value = enter.value
            is EnterParamStateTextFields.Date -> stateDate.value = enter.value
            is EnterParamStateTextFields.Description -> state = state.copy(description = enter.value)
            is EnterParamStateTextFields.Genre -> state = state.copy(genre = enter.value)
            is EnterParamStateTextFields.NameGroup -> state = state.copy(nameGroup = enter.value)
            is EnterParamStateTextFields.Type -> state = state.copy(type = enter.value)
            is EnterParamStateTextFields.Enter ->{
                state.organizerId = Gson().fromJson(prefs.getString("organizer", null)!!,OrganizerWithoutPswd::class.java).id
                prefs.edit().putString("price", statePrice.value).apply()
                prefs.edit().putString("event", Gson().toJson(state)).apply()
            }
        }
    }
}
