package com.example.ticketease.MVVM.Event.Catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.Catalog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelCatalog @Inject constructor(
    private val repository: CatalogRepository,
) : ViewModel() {

    private val _catalog = MutableLiveData<List<Catalog>>()
    val catalog: LiveData<List<Catalog>>
        get() = _catalog

    init {
        viewModelScope.launch {
                _catalog.value = repository.getAllEvents()
            }
    }
}


