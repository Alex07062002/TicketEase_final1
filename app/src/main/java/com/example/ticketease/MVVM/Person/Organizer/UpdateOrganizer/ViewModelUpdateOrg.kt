package com.example.ticketease.MVVM.Person.Organizer.UpdateOrganizer

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.Person.BuyerWithoutPswd
import com.example.ticketease.DataClasses.Person.OrganizerWithoutPswd
import com.example.ticketease.MVVM.Person.Buyer.UpdateBuyer.UpdateBuyerRepository
import com.example.ticketease.MVVM.Person.Buyer.UpdateBuyer.UpdateBuyerResult
import com.example.ticketease.MVVM.Person.Buyer.UpdateBuyer.UpdateBuyerStateTextFields
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelUpdateOrg @Inject constructor(
    private val repository : UpdateOrgRepository,
    private val prefs : SharedPreferences
) : ViewModel() {
    var flag : Boolean = false
    var state by mutableStateOf(Gson().fromJson(prefs.getString("organizer",null)!!, OrganizerWithoutPswd::class.java))
    private val resultChannel = Channel<UpdateOrgResult<Unit>>()
    val Results = resultChannel.receiveAsFlow()


    fun update(update : UpdateOrgStateTextFields){
        when(update){
            is UpdateOrgStateTextFields.Name -> state = state.copy(name = update.value)
            is UpdateOrgStateTextFields.Surname -> state =state.copy(surname = update.value)

            is UpdateOrgStateTextFields.Email -> state = state.copy(email = update.value)
            is UpdateOrgStateTextFields.Mobile -> state = state.copy(mobile = update.value)

            is UpdateOrgStateTextFields.Update ->{
                upd()
            }
        }
    }

    private fun upd(){
        viewModelScope.launch {
            flag = true
            val result = repository.UpdateOrg(state)
            resultChannel.send(result)
        }
    }
}
