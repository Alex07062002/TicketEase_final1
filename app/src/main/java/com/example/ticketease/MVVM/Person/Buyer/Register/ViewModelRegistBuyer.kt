package com.example.ticketease.MVVM.Person.Buyer.Register

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.Person.Buyer
import com.example.ticketease.DataClasses.Person.Cities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelRegistBuyer @Inject constructor(
    private val repository : RegisterRepository,
    private val prefs : SharedPreferences
) : ViewModel() {
    var flag : Boolean = false
    var state by mutableStateOf(Buyer())
    var repeatPassword = mutableStateOf("")

    private val resultChannel = Channel<RegistResult<Unit>>()
    val registerResults = resultChannel.receiveAsFlow()


    fun register(regist : RegisterStateTextFields){
        when(regist){
            is RegisterStateTextFields.Name -> state = state.copy(name = regist.value)
            is RegisterStateTextFields.Surname -> state =state.copy(surname = regist.value)
            is RegisterStateTextFields.Login -> state = state.copy(login = regist.value)
            is RegisterStateTextFields.Email -> state = state.copy(email = regist.value)
            is RegisterStateTextFields.Mobile -> state = state.copy(mobile = regist.value)
            is RegisterStateTextFields.Password -> state = state.copy(password = regist.value)
            is RegisterStateTextFields.RepeatPassword -> repeatPassword.value = regist.value
            is RegisterStateTextFields.RegisterBuyer ->{
                state = state.copy(city =Cities.valueOf(prefs.getString("city","Voronezh")!!))
                signIn()
            }
        }
    }


    private fun signIn(){
        viewModelScope.launch {
            flag = true
            val result = repository.signIn(state)
            resultChannel.send(result)
        }
    }
}
