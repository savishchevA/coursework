package io.bsu.mmf.helpme.domain

sealed class ResultNetwork<out R> {
    data class Success<T>(val data: T) : ResultNetwork<T>()
    sealed class Error : ResultNetwork<Nothing>() {
        class NetworkError(val errorMessage: Any) : Error()
        class OtherError(val errorMessage: String) : Error()
    }

    //Used to get result status
    val isFailure: Boolean
        get() = this is Error
    val isSuccess: Boolean
        get() = this is Success

    inline fun fold(
            onSuccess: (data: R) -> Unit = {},
            onFailure: (error: Error) -> Unit = {}
    ) {

        when (this) {
            is Success<R> -> {
                onSuccess(data)
            }
            is Error -> {
                onFailure(this)
            }
        }
    }
}