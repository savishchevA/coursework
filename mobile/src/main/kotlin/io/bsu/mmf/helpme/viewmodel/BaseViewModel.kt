package io.bsu.mmf.helpme.viewmodel

import androidx.lifecycle.*

class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading


    suspend fun execute(

    ) {

    }

}