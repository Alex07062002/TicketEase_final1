package com.example.ticketease.MVVM.Person.Buyer.UpdateBuyer

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.Person.Buyer
import com.example.ticketease.DataClasses.Person.BuyerWithoutPswd
import com.example.ticketease.DataClasses.Person.Cities
import com.example.ticketease.MVVM.Person.Buyer.Register.RegistResult
import com.example.ticketease.MVVM.Person.Buyer.Register.RegisterRepository
import com.example.ticketease.MVVM.Person.Buyer.Register.RegisterStateTextFields
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelUpdateBuyer @Inject constructor(
    private val repository : UpdateBuyerRepository,
    private val prefs : SharedPreferences
) : ViewModel() {
    var flag : Boolean = false
    var state by mutableStateOf(Gson().fromJson(prefs.getString("buyer",null)!!,BuyerWithoutPswd::class.java))
    private val resultChannel = Channel<UpdateBuyerResult<Unit>>()
    val Results = resultChannel.receiveAsFlow()


    fun update(update : UpdateBuyerStateTextFields){
        when(update){
            is UpdateBuyerStateTextFields.Name -> state = state.copy(name = update.value)
            is UpdateBuyerStateTextFields.Surname -> state =state.copy(surname = update.value)

            is UpdateBuyerStateTextFields.Email -> state = state.copy(email = update.value)
            is UpdateBuyerStateTextFields.Mobile -> state = state.copy(mobile = update.value)

            is UpdateBuyerStateTextFields.UpdateBuyer ->{
                upd()
            }
        }
    }

    private fun upd(){
        viewModelScope.launch {
            flag = true
            val result = repository.UpdateBuyer(state)
            resultChannel.send(result)
        }
    }
}
