package com.example.ticketease.MVVM.Event.Catalog

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.Catalog
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelCatalog @Inject constructor(
    private val repository: CatalogRepository,
    private val prefs : SharedPreferences
) : ViewModel() {

    private val _catalog = MutableLiveData<List<Catalog>>()
    val catalog: LiveData<List<Catalog>>
        get() = _catalog

    init {
        viewModelScope.launch {
                _catalog.value = repository.getAllEvents()
            }
    }

    fun checkCartList(eventCatalog : Catalog){
        if (prefs.contains("cart")){
            val cartList = Gson().fromJson(prefs.getString("cart",null)!!,Array<Catalog>::class.java).toMutableList()
            if (!cartList.contains(eventCatalog)) cartList.add(eventCatalog)
            prefs.edit().putString("cart",Gson().toJson(cartList.toTypedArray(),Array<Catalog>::class.java)).apply()
        } else{
            val cartList = mutableListOf<Catalog>()
            cartList.add(eventCatalog)
            prefs.edit().putString("cart",Gson().toJson(cartList.toTypedArray(),Array<Catalog>::class.java)).apply()
        }
    }


}


