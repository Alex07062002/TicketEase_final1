package com.example.ticketease.MVVM.Person.Organizer.Register

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.Person.Cities
import com.example.ticketease.DataClasses.Person.Organizer
import com.example.ticketease.DataClasses.Person.StatusOrganizer

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelRegistOrg @Inject constructor(
    private val repository : RegisterRepositoryOrg,
    private val prefs : SharedPreferences
) : ViewModel() {
    var flag : Boolean = false
    var state by mutableStateOf(Organizer())
    var repeatPassword = mutableStateOf("")

    private val resultChannel = Channel<RegistResultOrg<Unit>>()
    val registerResults = resultChannel.receiveAsFlow()


    fun register(regist : RegisterStateTextFieldsOrg){
        when(regist){
            is RegisterStateTextFieldsOrg.Name -> state = state.copy(name = regist.value)
            is RegisterStateTextFieldsOrg.Surname -> state =state.copy(surname = regist.value)
            is RegisterStateTextFieldsOrg.Login -> state = state.copy(login = regist.value)
            is RegisterStateTextFieldsOrg.Email -> state = state.copy(email = regist.value)
            is RegisterStateTextFieldsOrg.Mobile -> state = state.copy(mobile = regist.value)
            is RegisterStateTextFieldsOrg.Password -> state = state.copy(password = regist.value)
            is RegisterStateTextFieldsOrg.RepeatPassword -> repeatPassword.value = regist.value
            is RegisterStateTextFieldsOrg.RegisterOrganizer ->{
                state = state.copy(city = Cities.valueOf(prefs.getString("city","Voronezh")!!))
                state = state.copy(status = StatusOrganizer.CONSIDERATION)
                signIn()
            }
        }
    }

    private fun signIn(){
        viewModelScope.launch {
            flag = true
            val result = repository.singInOrg(state)
            resultChannel.send(result)
        }
    }
}
