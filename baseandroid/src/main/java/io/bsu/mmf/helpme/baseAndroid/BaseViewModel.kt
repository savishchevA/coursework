package io.bsu.mmf.helpme.baseAndroid

import androidx.lifecycle.*
import io.bsu.mmf.helpme.data.ResultNetwork
import kotlinx.coroutines.launch
import timber.log.Timber

open class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error


    fun <T> execute(
        call: suspend () -> ResultNetwork<T>,
        result: MutableLiveData<T>,
        onSuccess: () -> Unit = {}
    ) {
        _loading.value = true
        viewModelScope.launch {
            call().fold(
                onSuccess = {
                    result.value = it
                    onSuccess()
                },
                onFailure = {
                    Timber.e("Current error is ${it.errorMessage}")
                    _error.value = it.errorMessage
                }
            )

            _loading.value = false
        }
    }


}